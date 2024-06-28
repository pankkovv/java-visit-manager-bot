package ru.pankkovv.visitmanager.bot.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import ru.pankkovv.visitmanager.bot.message.ButtonData;
import ru.pankkovv.visitmanager.bot.message.CommandMessage;
import ru.pankkovv.visitmanager.bot.message.ExceptionMessage;
import ru.pankkovv.visitmanager.bot.model.Button;
import ru.pankkovv.visitmanager.profile.model.Profile;
import ru.pankkovv.visitmanager.profile.service.ProfileService;
import ru.pankkovv.visitmanager.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@AllArgsConstructor
public class BotService {

    @Autowired
    private final ProfileService profileService;

    public Object parseCommand(Long chatId, String userName, String text) {
        SendPhoto sendPhoto = new SendPhoto();
        String[] parameters = Utils.getParameters(text);

        switch (parameters[0]) {
            case "start":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setCaption(CommandMessage.START.label);
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "help":
                if (profileService.containsProfile(userName)) {
                    sendPhoto.setCaption(CommandMessage.HELP_ADMIN.label);
                } else {
                    sendPhoto.setCaption(CommandMessage.HELP_COMMON.label);
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            case "создать-анкету":
                if (parameters.length == 2) {
                    Profile newProfile = Profile.builder()
                            .username(userName)
                            .description(parameters[1])
                            .build();

                    sendPhoto.setCaption(profileService.create(newProfile).toString());
                } else {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            case "редактировать-анкету":
                if (parameters.length == 2) {
                    Profile updateProfile = profileService.getByUsername(userName);

                    updateProfile.setDescription(parameters[1]);

                    sendPhoto.setCaption(profileService.update(updateProfile).toString());
                } else {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            case "удалить-анкету":
                try {
                    Profile oldProfile = profileService.getByUsername(userName);

                    profileService.deleteById(oldProfile.getId());
                    Files.deleteIfExists(Path.of(oldProfile.getPathFile()));

                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setCaption(CommandMessage.DELETE_FORM_COMMAND.label);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

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
        String[] parameters = Utils.getParameters(text);

        switch (parameters[0]) {
            case "создать-анкету":
                Profile newProfile;

                if (parameters.length == 2) {
                    newProfile = Profile.builder()
                            .username(userName)
                            .description(parameters[1])
                            .pathFile(photo.getPath())
                            .build();

                } else {
                    newProfile = Profile.builder()
                            .username(userName)
                            .pathFile(photo.getPath())
                            .build();

                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(profileService.create(newProfile).toString());
                sendPhoto.setPhoto(new InputFile(new File(newProfile.getPathFile())));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            case "редактировать-анкету":
                Profile updateProfile = profileService.getByUsername(userName);

                if (parameters.length == 2) {
                    updateProfile.setDescription(parameters[1]);
                    updateProfile.setPathFile(photo.getPath());
                } else {
                    updateProfile.setPathFile(photo.getPath());
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(profileService.update(updateProfile).toString());
                sendPhoto.setPhoto(new InputFile(new File(updateProfile.getPathFile())));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            case "создать-товар":
                break;

            case "редактировать-товар":
                break;

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
                if (profileService.containsProfile(userName)) {
                    sendPhoto.setCaption(CommandMessage.HELP_ADMIN.label);
                } else {
                    sendPhoto.setCaption(CommandMessage.HELP_COMMON.label);
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            case "about_me_btn":
                if (profileService.containsProfile(userName)) {
                    sendPhoto.setCaption(profileService.getByUsername(userName).toString());
                    sendPhoto.setPhoto(new InputFile(new File(profileService.getByUsername(userName).getPathFile())));
                } else {
                    sendPhoto.setCaption(profileService.getByUsername(userName).toStringDto());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;
        }

        return sendPhoto;
    }


}
