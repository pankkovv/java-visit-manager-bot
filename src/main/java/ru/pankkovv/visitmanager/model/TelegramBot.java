package ru.pankkovv.visitmanager.model;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pankkovv.visitmanager.config.BotConfig;
import ru.pankkovv.visitmanager.service.BotService;
import ru.pankkovv.visitmanager.utils.Utils;

import java.io.File;

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

        try {
            if (cbq != null) {
                Object answer = service.parseCommand(chatId, userName, cbq);

                if (answer.getClass().equals(SendPhoto.class)) {
                    execute((SendPhoto) answer);
                }

            } else if (msg.getText() != null) {
                Object answer = service.parseCommand(chatId, userName, msg.getText());

            } else if (msg.getPhoto() != null) {
                Object answer = service.parseCommand(chatId, userName, msg.getText(), new File("/"));

            }

        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }
    }
}
