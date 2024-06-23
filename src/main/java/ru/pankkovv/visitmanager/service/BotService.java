package ru.pankkovv.visitmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.pankkovv.visitmanager.message.CommandMessage;
import ru.pankkovv.visitmanager.model.Button;
import ru.pankkovv.visitmanager.model.User;

import java.io.File;

@Service
@AllArgsConstructor
public class BotService {

    @Autowired
    private final UserService userService;

    public Object parseCommand(Long chatId, String userName, String text) {
        SendPhoto sendPhoto = new SendPhoto();

        String[] parameters = text.replaceAll("-", "")
                .replaceAll("/", "")
                .replaceAll(", ", ",")
                .replaceAll(" ", ",")
                .split(",");

        String par = "";

        if (parameters.length == 1) {
            par = parameters[0];
        } else {
            par = text.substring(0, text.indexOf(" "));
        }

        switch (par) {
            case "/start":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.START.label);
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "помощь":
                if (userService.containsUser(userName)) {
                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setCaption(CommandMessage.HELP_ADMIN.label);
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                } else {
                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setCaption(CommandMessage.HELP_COMMON.label);
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                }

                break;

            case "регистрация":
                if (text.equals("regVisitManagerBotReg14")) {
                    User user = userService.create(userName);
                } else {
                    throw new RuntimeException();
                }

                break;

            case "создать-анкету":
                break;

            case "создать-товар":
                break;

            case "создать-отзыв":
                break;

            case "редактировать-анкету":
                break;

            case "редактировать-товар":
                break;

            case "редактировать-отзыв":
                break;

        }

        return sendPhoto;
    }

    public Object parseCommand(Long chatId, String userName, CallbackQuery cbq) {
        return new SendPhoto();
    }

    public Object parseCommand(Long chatId, String userName, String text, File photo) {
        return new SendPhoto();
    }
}
