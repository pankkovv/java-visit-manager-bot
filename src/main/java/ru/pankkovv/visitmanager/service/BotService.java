package ru.pankkovv.visitmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.pankkovv.visitmanager.message.ButtonData;
import ru.pankkovv.visitmanager.message.CommandMessage;
import ru.pankkovv.visitmanager.model.Button;

import java.io.File;

@Service
@AllArgsConstructor
public class BotService {

    @Autowired
    private final UserService userService;

    public Object parseCommand(Long chatId, String userName, String text) {
        SendPhoto sendPhoto = new SendPhoto();

        String[] parameters = text
                .replaceAll("/", "")
                .split("&");

        switch (parameters[0]) {
//            Базовые команды
            case "start":
                break;

            case "help":
                break;

//                Анкета (раздел о себе)
            case "создать-анкету":
                break;

            case "редактировать-анкету":
                break;

            case "удалить-анкету":
                break;

//                Прайслист (полка с товарами)
            case "создать-товар":
                break;

            case "редактировать-товар":
                break;

            case "удалить-товар":
                break;

//                Обратная связь (отзывы)
            case "создать-отзыв":
                break;

            case "редактировать-отзыв":
                break;

            case "удалить-отзыв":
                break;

        }

        return sendPhoto;
    }

    public Object parseCommand(Long chatId, String userName, String text, File photo) {
        SendPhoto sendPhoto = new SendPhoto();

        String[] parameters = text
                .replaceAll("/", "")
                .split("&");

        switch (parameters[0]) {
//                Анкета (раздел о себе)
            case "создать-анкету":
                break;

            case "редактировать-анкету":
                break;

//                Прайслист (полка с товарами)
            case "создать-товар":
                break;

            case "редактировать-товар":
                break;

//                Обратная связь (отзывы)
            case "создать-отзыв":
                break;

            case "редактировать-отзыв":
                break;

        }

        return sendPhoto;
    }

    public Object parseCommand(Long chatId, String userName, CallbackQuery cbq) {
        SendPhoto sendPhoto = new SendPhoto();

        String button = ButtonData.valueOf(cbq.getData().toUpperCase()).label;

        switch (button) {
            case "start_btn":
                break;

            case "help_btn":
                break;
        }

        return new SendPhoto();
    }


}
