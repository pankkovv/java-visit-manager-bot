package ru.pankkovv.visitmanager.bot.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pankkovv.visitmanager.bot.config.BotConfig;
import ru.pankkovv.visitmanager.bot.service.BotService;
import ru.pankkovv.visitmanager.exception.ExceptionMessage;
import ru.pankkovv.visitmanager.utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final BotService service;

    public TelegramBot(BotConfig config, BotService service) {
        this.config = config;
        this.service = service;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        CallbackQuery cbq = update.getCallbackQuery();
        Message msg = update.getMessage();
        String userName = Utils.getUserName(update);
        Long chatId = Utils.getChatId(update);
        Object answer;
        DeleteMessage deleteMessage = new DeleteMessage();

        try {
            deleteMessage.setChatId(String.valueOf(chatId));

            if (msg != null) {
                deleteMessage.setMessageId(msg.getMessageId());
            } else {
                deleteMessage.setMessageId(cbq.getMessage().getMessageId());
            }

            if (cbq != null) {
                answer = service.parseCommand(chatId, userName, cbq);

            } else if (msg.getPhoto() != null) {
                List<PhotoSize> photos = msg.getPhoto();
                GetFile getFile = new GetFile(photos.get(photos.size() - 1).getFileId());
                org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);
                String[] par = Utils.getParameters(msg.getCaption());
                File photo = new File("");

                switch (par[0]) {
                    case "создать-анкету":
                    case "редактировать-анкету":
                        photo = downloadFile(file, new File("download/profile" + new Random().nextInt() + ".jpg"));
                        break;

                    case "создать-товар":
                    case "редактировать-товар":
                        photo = downloadFile(file, new File("download/product" + new Random().nextInt() + ".jpg"));
                        break;

                    case "создать-отзыв":
                    case "редактировать-отзыв":
                        photo = downloadFile(file, new File("download/feedback" + new Random().nextInt() + ".jpg"));
                        break;
                }

                answer = service.parseCommand(chatId, userName, msg.getCaption(), photo);

            } else if (msg.getText() != null) {
                answer = service.parseCommand(chatId, userName, msg.getText());

            } else {
                answer = new SendMessage();
                ((SendMessage) answer).setChatId(String.valueOf(chatId));
                ((SendMessage) answer).setText(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                ((SendMessage) answer).setReplyMarkup(Button.getStartButton());

            }

            if (answer.getClass().equals(SendPhoto.class)) {
                execute((SendPhoto) answer);
                execute(deleteMessage);

                if (msg != null) {
                    deleteMessage.setMessageId(msg.getMessageId() - 1);
                } else {
                    deleteMessage.setMessageId(cbq.getMessage().getMessageId() - 1);
                }

                execute(deleteMessage);

            } else if (answer.getClass().equals(SendMessage.class)) {
                execute((SendMessage) answer);
                execute(deleteMessage);

                if (msg != null) {
                    deleteMessage.setMessageId(msg.getMessageId() - 1);
                } else {
                    deleteMessage.setMessageId(cbq.getMessage().getMessageId() - 1);
                }

                execute(deleteMessage);
            } else if (answer.getClass().equals(EditMessageReplyMarkup.class)) {
                execute((EditMessageReplyMarkup) answer);
            }

        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());

            if (e.getMessage().equals("ChatId parameter can't be empty")) {
                SendPhoto errorMessage = new SendPhoto();
                errorMessage.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                errorMessage.setPhoto(new InputFile(new File("img/error.jpg")));
                errorMessage.setChatId(String.valueOf(chatId));
                errorMessage.setReplyMarkup(Button.getStartButton());

                try {
                    execute(errorMessage);
                    execute(deleteMessage);

                    if (msg != null) {
                        deleteMessage.setMessageId(msg.getMessageId() - 1);
                    } else {
                        deleteMessage.setMessageId(cbq.getMessage().getMessageId() - 1);
                    }

                    execute(deleteMessage);
                } catch (TelegramApiException ex) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }
}
