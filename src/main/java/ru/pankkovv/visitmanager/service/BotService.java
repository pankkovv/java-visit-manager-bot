package ru.pankkovv.visitmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.pankkovv.visitmanager.message.ButtonData;
import ru.pankkovv.visitmanager.message.CommandMessage;
import ru.pankkovv.visitmanager.message.ExceptionMessage;
import ru.pankkovv.visitmanager.model.Button;
import ru.pankkovv.visitmanager.model.Form;

import java.io.File;

@Service
@AllArgsConstructor
public class BotService {

    @Autowired
    private final FormService formService;

    public Object parseCommand(Long chatId, String userName, String text) {
        SendPhoto sendPhoto = new SendPhoto();

        String[] parameters = text
                .replaceAll("/", "")
                .split("&");

        switch (parameters[0]) {
//            Базовые команды
            case "start":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setCaption(CommandMessage.START.label);
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "help":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

                if (formService.containsForm(userName)) {
                    sendPhoto.setCaption(CommandMessage.HELP_ADMIN.label);
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                } else {
                    sendPhoto.setCaption(CommandMessage.HELP_COMMON.label);
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                }

                break;

//                Анкета (раздел о себе)
            case "создать-анкету":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                if (parameters.length >= 2) {
                    Form newForm = Form.builder()
                            .username(userName)
                            .description(parameters[1])
                            .build();

                    sendPhoto.setCaption(formService.create(newForm).toString());
                } else {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                }

                break;

            case "редактировать-анкету":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                if (parameters.length >= 2) {
                    Form updateForm = formService.getByUsername(userName);

                    updateForm.setDescription(parameters[1]);

                    sendPhoto.setCaption(formService.update(updateForm).toString());
                } else {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                }

                break;

            case "удалить-анкету":
                formService.deleteById(Long.parseLong(parameters[1]));

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setCaption(CommandMessage.DELETE_FORM_COMMAND.label);
                sendPhoto.setReplyMarkup(Button.getStartButton());
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
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setCaption(CommandMessage.START.label);
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "help_btn":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

                if (formService.containsForm(userName)) {
                    sendPhoto.setCaption(CommandMessage.HELP_ADMIN.label);
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                } else {
                    sendPhoto.setCaption(CommandMessage.HELP_COMMON.label);
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                }

                break;

            case "about_me_btn":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                if(formService.containsForm(userName)){
                    sendPhoto.setCaption(formService.getByUsername(userName).toString());
                } else {
                    sendPhoto.setCaption(formService.getByUsername(userName).toStringDto());
                }
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;
        }

        return  sendPhoto;
    }


}
