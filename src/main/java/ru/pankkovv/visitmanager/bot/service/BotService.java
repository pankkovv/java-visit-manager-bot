package ru.pankkovv.visitmanager.bot.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.pankkovv.visitmanager.bot.mapper.ProductMapper;
import ru.pankkovv.visitmanager.bot.message.ButtonData;
import ru.pankkovv.visitmanager.bot.message.CommandMessage;
import ru.pankkovv.visitmanager.bot.message.ExceptionMessage;
import ru.pankkovv.visitmanager.bot.model.Button;
import ru.pankkovv.visitmanager.product.model.Product;
import ru.pankkovv.visitmanager.product.model.Type;
import ru.pankkovv.visitmanager.product.service.ProductService;
import ru.pankkovv.visitmanager.profile.model.Profile;
import ru.pankkovv.visitmanager.profile.service.ProfileService;
import ru.pankkovv.visitmanager.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static ru.pankkovv.visitmanager.bot.model.Button.pagination;
import static ru.pankkovv.visitmanager.bot.model.Button.sizeListProductOrder;

@Service
@AllArgsConstructor
public class BotService {

    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ProductMapper productMapper;

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

                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setCaption(profileService.create(newProfile).toString());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } else {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

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
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                break;

//                Прайслист (полка с товарами)
            case "создать-товар":
                try {
                    Product product = productMapper.mapToProduct(text, userName);

                    sendPhoto.setCaption(productService.create(product).toString());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());

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
                try {
                    Product product = productMapper.mapToProduct(text, userName, photo.getPath());

                    sendPhoto.setCaption(productService.create(product).toString());
                    sendPhoto.setPhoto(new InputFile(new File(product.getPathFile())));
                } catch (RuntimeException e) {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
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

            case "view_products_btn":
                sizeListProductOrder = productService.getByType(Type.ORDER, Utils.paged(0, Integer.MAX_VALUE)).size();
                sendPhoto = pagedProduct(chatId, userName, 0);
                break;

            //Пагинация
            case "next":
                int count = sizeListProductOrder / 7;

                if (pagination == count) {

                    if (pagination * 7 < sizeListProductOrder) {
                        ++pagination;
                    }

                    EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                    editMessageReplyMarkup.setMessageId(cbq.getMessage().getMessageId());
                    editMessageReplyMarkup.setChatId(String.valueOf(chatId));
                    editMessageReplyMarkup.setReplyMarkup(Button.getNumberButton(pagination * 7));
                    return editMessageReplyMarkup;
                }

                if (pagination < count) {
                    ++pagination;
                    EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                    editMessageReplyMarkup.setMessageId(cbq.getMessage().getMessageId());
                    editMessageReplyMarkup.setChatId(String.valueOf(chatId));
                    editMessageReplyMarkup.setReplyMarkup(Button.getNumberButton(pagination * 7));
                    return editMessageReplyMarkup;
                }

                break;

            case "prev":
                EditMessageReplyMarkup editMessageReplyMarkupPrev = new EditMessageReplyMarkup();
                editMessageReplyMarkupPrev.setMessageId(cbq.getMessage().getMessageId());
                editMessageReplyMarkupPrev.setChatId(String.valueOf(chatId));

                if (pagination == 2) {
                    --pagination;
                    editMessageReplyMarkupPrev.setReplyMarkup(Button.getNumberButton(7));
                    return editMessageReplyMarkupPrev;
                } else if (pagination < 2) {
                    editMessageReplyMarkupPrev.setReplyMarkup(Button.getNumberButton(7));
                    return editMessageReplyMarkupPrev;
                } else {
                    --pagination;
                    editMessageReplyMarkupPrev.setReplyMarkup(Button.getNumberButton(pagination * 7));
                    return editMessageReplyMarkupPrev;
                }

            case "one":
                sendPhoto = pagedProduct(chatId, userName, 0);
                break;

            case "two":
                sendPhoto = pagedProduct(chatId, userName, 1);
                break;

            case "three":
                sendPhoto = pagedProduct(chatId, userName, 2);
                break;

            case "four":
                sendPhoto = pagedProduct(chatId, userName, 3);
                break;

            case "five":
                sendPhoto = pagedProduct(chatId, userName, 4);
                break;

            case "six":
                sendPhoto = pagedProduct(chatId, userName, 5);
                break;

            case "seven":
                sendPhoto = pagedProduct(chatId, userName, 6);
                break;

        }

        return sendPhoto;
    }

    private SendPhoto pagedProduct(Long chatId, String userName, int from) {
        SendPhoto sendPhoto = new SendPhoto();
        Product product;
        sendPhoto.setChatId(String.valueOf(chatId));

        try {
            if (pagination >= 2) {
                Integer value = Integer.valueOf(String.valueOf(pagination * 7 - 7 + from));
                product = productService.getByType(Type.ORDER, Utils.paged(value, 1)).get(0);
            } else {
                product = productService.getByType(Type.ORDER, Utils.paged(from, 1)).get(0);
            }

            if (profileService.containsProfile(userName)) {
                sendPhoto.setCaption(product.toString());
            } else {
                sendPhoto.setCaption(product.toStringDto());
            }

            if (product.getPathFile() != null) {
                sendPhoto.setPhoto(new InputFile(new File(product.getPathFile())));
            } else {
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
            }

            if (pagination == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberButton(pagination * 7));
            }

        } catch (IndexOutOfBoundsException e) {
            sendPhoto.setCaption("Извините, похоже данный товар не получилось найти :(");
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

            if (pagination == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberButton(pagination * 7));
            }
        }

        return sendPhoto;
    }

}
