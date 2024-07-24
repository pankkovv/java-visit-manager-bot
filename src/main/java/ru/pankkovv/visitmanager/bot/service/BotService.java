package ru.pankkovv.visitmanager.bot.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.pankkovv.visitmanager.bot.message.ButtonData;
import ru.pankkovv.visitmanager.bot.message.CommandMessage;
import ru.pankkovv.visitmanager.bot.model.Button;
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.category.service.CategoryService;
import ru.pankkovv.visitmanager.exception.EntityNotFoundException;
import ru.pankkovv.visitmanager.exception.ExceptionMessage;
import ru.pankkovv.visitmanager.feedback.model.Feedback;
import ru.pankkovv.visitmanager.feedback.service.FeedbackService;
import ru.pankkovv.visitmanager.product.model.Product;
import ru.pankkovv.visitmanager.product.model.Type;
import ru.pankkovv.visitmanager.product.service.ProductService;
import ru.pankkovv.visitmanager.profile.model.Profile;
import ru.pankkovv.visitmanager.profile.service.ProfileService;
import ru.pankkovv.visitmanager.queue.model.UserQueue;
import ru.pankkovv.visitmanager.queue.service.QueueService;
import ru.pankkovv.visitmanager.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static ru.pankkovv.visitmanager.bot.model.Button.*;

@Log4j
@Service
@AllArgsConstructor
public class BotService {
    @Autowired
    private final ProfileService profileService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final FeedbackService feedbackService;
    @Autowired
    private final QueueService queueService;

    public Object parseCommand(Long chatId, String userName, String text) {
        SendPhoto sendPhoto = new SendPhoto();
        String[] parameters = Utils.getParameters(text);

        switch (parameters[0]) {
            case "start":
                log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setCaption(CommandMessage.START.label);
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "help":
                log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

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
                log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

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
                log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

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
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Profile oldProfile = profileService.getByUsername(userName);

                    profileService.deleteById(oldProfile.getId());
                    Files.deleteIfExists(Path.of(oldProfile.getPathFile()));

                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setCaption(CommandMessage.DELETE_FORM_COMMAND.label);

                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (IOException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                break;

//                Товар под заказ (полка с товарами)
            case "создать-товар":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Profile profile = profileService.getByUsername(userName);
                    Category category = categoryService.getByName(parameters[4]);
                    Product product = productService.mapToProduct(text, category, profile);

                    sendPhoto.setCaption(productService.create(product).toString());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "редактировать-товар":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Long id = Long.parseLong(parameters[1]);
                    Product product = updateProduct(productService.getById(id), parameters);

                    sendPhoto.setCaption(productService.update(product).toString());

                    if (product.getPathFile() != null) {
                        sendPhoto.setPhoto(new InputFile(new File(product.getPathFile())));
                    } else {
                        sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    }

                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "удалить-товар":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Long id = Long.parseLong(parameters[1]);
                    productService.getById(id);
                    productService.deleteById(id);

                    sendPhoto.setCaption("Товар успешно удален!");
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            //   Обратная связь (отзывы)
            case "создать-отзыв":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Profile profile = profileService.getByUsername(userName);
                    Feedback newFeedback = feedbackService.mapToFeedback(text, profile);

                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setCaption(feedbackService.create(newFeedback).toString());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "редактировать-отзыв":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Long id = Long.parseLong(parameters[1]);
                    Feedback feedback = feedbackService.getById(id);

                    for (String newPar : parameters) {
                        if (newPar.toLowerCase().contains("описание:")) {
                            String description = Utils.getParametersUpdate(newPar);
                            feedback.setDescription(description);
                        }
                    }

                    sendPhoto.setCaption(feedbackService.update(feedback).toString());

                    if (feedback.getPathFile() != null) {
                        sendPhoto.setPhoto(new InputFile(new File(feedback.getPathFile())));
                    } else {
                        sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    }

                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "удалить-отзыв":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Long id = Long.parseLong(parameters[1]);
                    feedbackService.getById(id);
                    feedbackService.deleteById(id);

                    sendPhoto.setCaption("Отзыв успешно удален!");
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            //  Категория
            case "создать-категорию":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Category newCategory = categoryService.mapToCategory(text);

                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setCaption(categoryService.create(newCategory).toString());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "посмотреть-категории":
                log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(categoryService.mapToListCategoryDto(categoryService.getAll().toString()));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "удалить-категорию":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Long id = Long.parseLong(parameters[1]);
                    categoryService.getById(id);
                    categoryService.deleteById(id);

                    sendPhoto.setCaption("Категория успешно удален!");
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            //Очередь
            case "удалить-очередь":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    String username = parameters[1];

                    UserQueue userQueue = queueService.getByUsername(username);
                    queueService.delete(userQueue.getId());

                    sendPhoto.setCaption("Пользователь успешно удален из очереди!");
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;
        }

        return sendPhoto;
    }

    public Object parseCommand(Long chatId, String userName, String text, File photo) {
        SendPhoto sendPhoto = new SendPhoto();
        String[] parameters = Utils.getParameters(text);

        switch (parameters[0]) {
            case "создать-анкету":
                log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

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
                log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

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
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Profile profile = profileService.getByUsername(userName);
                    Category category = categoryService.getByName(parameters[4]);
                    Product product = productService.mapToProduct(text, category, profile, photo.getPath());

                    sendPhoto.setCaption(productService.create(product).toString());
                    sendPhoto.setPhoto(new InputFile(new File(product.getPathFile())));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            case "редактировать-товар":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Long id = Long.parseLong(parameters[1]);
                    Product product = updateProduct(productService.getById(id), parameters);
                    product.setPathFile(photo.getPath());

                    sendPhoto.setCaption(productService.update(product).toString());
                    sendPhoto.setPhoto(new InputFile(new File(product.getPathFile())));

                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());

                break;

            //   Обратная связь (отзывы)
            case "создать-отзыв":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Profile profile = profileService.getByUsername(userName);
                    Feedback newFeedback = feedbackService.mapToFeedback(text, profile, photo.getPath());

                    sendPhoto.setChatId(String.valueOf(chatId));
                    sendPhoto.setCaption(feedbackService.create(newFeedback).toString());
                    sendPhoto.setPhoto(new InputFile(new File(newFeedback.getPathFile())));
                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "редактировать-отзыв":
                try {
                    log.debug("Пользователь '" + userName + "' пытается выполнить действие '" + parameters[0] + "'");

                    Long id = Long.parseLong(parameters[1]);
                    Feedback feedback = feedbackService.getById(id);

                    for (String newPar : parameters) {
                        if (newPar.toLowerCase().contains("описание:")) {
                            String description = Utils.getParametersUpdate(newPar);
                            feedback.setDescription(description);
                        }
                    }

                    feedback.setPathFile(photo.getPath());

                    sendPhoto.setCaption(feedbackService.update(feedback).toString());
                    sendPhoto.setPhoto(new InputFile(new File(feedback.getPathFile())));

                } catch (EntityNotFoundException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    log.error("Error occurred: " + e.getMessage());

                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;
        }

        return sendPhoto;
    }

    public Object parseCommand(Long chatId, String userName, CallbackQuery cbq) {
        SendPhoto sendPhoto = new SendPhoto();
        String button = ButtonData.valueOf(cbq.getData().toUpperCase()).label;

        switch (button) {
            case "start_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setCaption(CommandMessage.START.label);
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "help_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                if (profileService.containsProfile(userName)) {
                    sendPhoto.setCaption(CommandMessage.HELP_ADMIN.label);
                    sendPhoto.setReplyMarkup(Button.getHelpAdminButton());
                } else {
                    sendPhoto.setCaption(CommandMessage.HELP_COMMON.label);
                    sendPhoto.setReplyMarkup(Button.getStartButton());
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

                break;
            case "command_profile_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.HELP_ADMIN_COMMAND_PROFILE.label);
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getBackAllCommandButton());
                break;

            case "command_product_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.HELP_ADMIN_COMMAND_PRODUCT.label);
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getBackAllCommandButton());
                break;

            case "command_feedback_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.HELP_ADMIN_COMMAND_FEEDBACK.label);
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getBackAllCommandButton());
                break;

            case "command_category_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.HELP_ADMIN_COMMAND_CATEGORY.label);
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getBackAllCommandButton());
                break;

            case "back_all_command_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                cbq.setData("help_btn");
                return parseCommand(chatId, userName, cbq);

            case "about_me_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

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

            case "queue_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.QUEUE.label);
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

                if (queueService.contains(userName)) {
                    sendPhoto.setReplyMarkup(Button.getJoinQueueButton());
                } else {
                    sendPhoto.setReplyMarkup(Button.getStartQueueButton());
                }

                break;

            case "start_queue_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                try {
                    UserQueue userQueue = UserQueue.builder()
                            .chatId(chatId)
                            .username(userName)
                            .build();

                    queueService.create(userQueue);

                    sendPhoto.setCaption(CommandMessage.START_QUEUE.label + userQueue);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setReplyMarkup(Button.getJoinQueueButton());

                } catch (RuntimeException e) {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setReplyMarkup(Button.getStartQueueButton());
                }

                sendPhoto.setChatId(String.valueOf(chatId));

                break;

            case "view_queue_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                try {
                    UserQueue userSearch = queueService.getByUsername(userName);

                    sendPhoto.setCaption(String.format(CommandMessage.VIEW_QUEUE.label,
                            queueService.getQueues().indexOf(userSearch) + 1));
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setReplyMarkup(Button.getJoinQueueButton());
                } catch (EntityNotFoundException e) {
                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setReplyMarkup(Button.getJoinQueueButton());
                }

                sendPhoto.setChatId(String.valueOf(chatId));


                break;

            case "join_queue_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                try {
                    UserQueue userQueue = queueService.getByUsername(userName);

                    queueService.delete(userQueue.getId());

                    sendPhoto.setCaption(CommandMessage.JOIN_QUEUE.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setReplyMarkup(Button.getStartQueueButton());

                } catch (EntityNotFoundException e) {
                    sendPhoto.setCaption(e.getMessage());
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    sendPhoto.setReplyMarkup(Button.getProductButton());
                }

                sendPhoto.setChatId(String.valueOf(chatId));

                break;


            case "view_products_btn":
            case "back":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.VIEW_PRODUCTS.label);
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getProductButton());

                break;

            //Сделать заказ
            case "make_order_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption(CommandMessage.MAKE_ORDER.label);
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getProductButton());
                break;

            // Просмотр товаров под заказ
            case "view_products_order_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sizeListProductOrder = productService.getByType(Type.ORDER, Utils.paged(0, Integer.MAX_VALUE)).size();
                sendPhoto = pagedProductOrder(chatId, userName, 0);
                break;

            //Пагинация
            case "next_order":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                int countOrder = sizeListProductOrder / 7;
                EditMessageReplyMarkup editMessageReplyMarkupOrder = new EditMessageReplyMarkup();
                editMessageReplyMarkupOrder.setMessageId(cbq.getMessage().getMessageId());
                editMessageReplyMarkupOrder.setChatId(String.valueOf(chatId));

                if (paginationOrder == countOrder) {

                    if (paginationOrder * 7 < sizeListProductOrder) {
                        ++paginationOrder;
                    }

                    editMessageReplyMarkupOrder.setReplyMarkup(Button.getNumberOrderButton(paginationOrder * 7));
                } else if (paginationOrder < countOrder) {
                    ++paginationOrder;
                    editMessageReplyMarkupOrder.setReplyMarkup(Button.getNumberOrderButton(paginationOrder * 7));
                } else {
                    editMessageReplyMarkupOrder.setReplyMarkup(cbq.getMessage().getReplyMarkup());
                }

                return editMessageReplyMarkupOrder;

            case "prev_order":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                EditMessageReplyMarkup editMessageReplyMarkupPrevOrder = new EditMessageReplyMarkup();
                editMessageReplyMarkupPrevOrder.setMessageId(cbq.getMessage().getMessageId());
                editMessageReplyMarkupPrevOrder.setChatId(String.valueOf(chatId));

                if (paginationOrder == 2) {
                    --paginationOrder;
                    editMessageReplyMarkupPrevOrder.setReplyMarkup(Button.getNumberOrderButton(7));
                } else if (paginationOrder < 2) {
                    editMessageReplyMarkupPrevOrder.setReplyMarkup(Button.getNumberOrderButton(7));
                } else {
                    --paginationOrder;
                    editMessageReplyMarkupPrevOrder.setReplyMarkup(Button.getNumberOrderButton(paginationOrder * 7));
                }

                return editMessageReplyMarkupPrevOrder;


            case "one_order":
                sendPhoto = pagedProductOrder(chatId, userName, 0);
                break;

            case "two_order":
                sendPhoto = pagedProductOrder(chatId, userName, 1);
                break;

            case "three_order":
                sendPhoto = pagedProductOrder(chatId, userName, 2);
                break;

            case "four_order":
                sendPhoto = pagedProductOrder(chatId, userName, 3);
                break;

            case "five_order":
                sendPhoto = pagedProductOrder(chatId, userName, 4);
                break;

            case "six_order":
                sendPhoto = pagedProductOrder(chatId, userName, 5);
                break;

            case "seven_order":
                sendPhoto = pagedProductOrder(chatId, userName, 6);
                break;


            //Просмотр товаров в наличии
            case "view_products_stock_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sizeListProductStock = productService.getByType(Type.STOCK, Utils.paged(0, Integer.MAX_VALUE)).size();
                sendPhoto = pagedProductStock(chatId, userName, 0);
                break;

            //Пагинация
            case "next_stock":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                int countStock = sizeListProductStock / 7;
                EditMessageReplyMarkup editMessageReplyMarkupStock = new EditMessageReplyMarkup();
                editMessageReplyMarkupStock.setMessageId(cbq.getMessage().getMessageId());
                editMessageReplyMarkupStock.setChatId(String.valueOf(chatId));

                if (paginationStock == countStock) {

                    if (paginationStock * 7 < sizeListProductStock) {
                        ++paginationStock;
                    }

                    editMessageReplyMarkupStock.setReplyMarkup(Button.getNumberStockButton(paginationStock * 7));
                } else if (paginationStock < countStock) {
                    ++paginationStock;

                    editMessageReplyMarkupStock.setReplyMarkup(Button.getNumberStockButton(paginationStock * 7));
                } else {
                    editMessageReplyMarkupStock.setReplyMarkup(cbq.getMessage().getReplyMarkup());
                }

                return editMessageReplyMarkupStock;

            case "prev_stock":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                EditMessageReplyMarkup editMessageReplyMarkupStockPrev = new EditMessageReplyMarkup();
                editMessageReplyMarkupStockPrev.setMessageId(cbq.getMessage().getMessageId());
                editMessageReplyMarkupStockPrev.setChatId(String.valueOf(chatId));

                if (paginationStock == 2) {
                    --paginationStock;
                    editMessageReplyMarkupStockPrev.setReplyMarkup(Button.getNumberStockButton(7));
                } else if (paginationStock < 2) {
                    editMessageReplyMarkupStockPrev.setReplyMarkup(Button.getNumberStockButton(7));
                } else {
                    --paginationStock;
                    editMessageReplyMarkupStockPrev.setReplyMarkup(Button.getNumberStockButton(paginationStock * 7));
                }

                return editMessageReplyMarkupStockPrev;


            case "one_stock":
                sendPhoto = pagedProductStock(chatId, userName, 0);
                break;

            case "two_stock":
                sendPhoto = pagedProductStock(chatId, userName, 1);
                break;

            case "three_stock":
                sendPhoto = pagedProductStock(chatId, userName, 2);
                break;

            case "four_stock":
                sendPhoto = pagedProductStock(chatId, userName, 3);
                break;

            case "five_stock":
                sendPhoto = pagedProductStock(chatId, userName, 4);
                break;

            case "six_stock":
                sendPhoto = pagedProductStock(chatId, userName, 5);
                break;

            case "seven_stock":
                sendPhoto = pagedProductStock(chatId, userName, 6);
                break;

            //Просмотр отзывов
            case "view_feedbacks_btn":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                sizeListProductFeedback = feedbackService.getAll(Utils.paged(0, Integer.MAX_VALUE)).size();
                sendPhoto = pagedFeedback(chatId, userName, 0);
                break;

            //Пагинация
            case "next_feedback":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                int countFeedback = sizeListProductFeedback / 7;
                EditMessageReplyMarkup editMessageReplyMarkupFeedback = new EditMessageReplyMarkup();
                editMessageReplyMarkupFeedback.setMessageId(cbq.getMessage().getMessageId());
                editMessageReplyMarkupFeedback.setChatId(String.valueOf(chatId));

                if (paginationFeedback == countFeedback) {

                    if (paginationFeedback * 7 < sizeListProductFeedback) {
                        ++paginationFeedback;
                    }

                    editMessageReplyMarkupFeedback.setReplyMarkup(Button.getNumberFeedbackButton(paginationFeedback * 7));
                } else if (paginationFeedback < countFeedback) {
                    ++paginationFeedback;

                    editMessageReplyMarkupFeedback.setReplyMarkup(Button.getNumberFeedbackButton(paginationFeedback * 7));
                } else {
                    editMessageReplyMarkupFeedback.setReplyMarkup(cbq.getMessage().getReplyMarkup());
                }

                return editMessageReplyMarkupFeedback;

            case "prev_feedback":
                log.debug("Пользователь '" + userName + "' нажал кнопку '" + button + "'");

                EditMessageReplyMarkup editMessageReplyMarkupStockFeedback = new EditMessageReplyMarkup();
                editMessageReplyMarkupStockFeedback.setMessageId(cbq.getMessage().getMessageId());
                editMessageReplyMarkupStockFeedback.setChatId(String.valueOf(chatId));

                if (paginationFeedback == 2) {
                    --paginationFeedback;
                    editMessageReplyMarkupStockFeedback.setReplyMarkup(Button.getNumberFeedbackButton(7));
                } else if (paginationFeedback < 2) {
                    editMessageReplyMarkupStockFeedback.setReplyMarkup(Button.getNumberFeedbackButton(7));
                } else {
                    --paginationFeedback;
                    editMessageReplyMarkupStockFeedback.setReplyMarkup(Button.getNumberFeedbackButton(paginationFeedback * 7));
                }

                return editMessageReplyMarkupStockFeedback;


            case "one_feedback":
                sendPhoto = pagedFeedback(chatId, userName, 0);
                break;

            case "two_feedback":
                sendPhoto = pagedFeedback(chatId, userName, 1);
                break;

            case "three_feedback":
                sendPhoto = pagedFeedback(chatId, userName, 2);
                break;

            case "four_feedback":
                sendPhoto = pagedFeedback(chatId, userName, 3);
                break;

            case "five_feedback":
                sendPhoto = pagedFeedback(chatId, userName, 4);
                break;

            case "six_feedback":
                sendPhoto = pagedFeedback(chatId, userName, 5);
                break;

            case "seven_feedback":
                sendPhoto = pagedFeedback(chatId, userName, 6);
                break;
        }


        return sendPhoto;
    }

    private Product updateProduct(Product product, String[] parameters) {
        for (String newPar : parameters) {
            if (newPar.toLowerCase().contains("имя:")) {
                String name = Utils.getParametersUpdate(newPar);
                product.setName(name);
            } else if (newPar.toLowerCase().contains("описание:")) {
                String description = Utils.getParametersUpdate(newPar);
                product.setDescription(description);
            } else if (newPar.toLowerCase().contains("цена:")) {
                Long price = Long.parseLong(Utils.getParametersUpdate(newPar));
                product.setPrice(price);
            } else if (newPar.toLowerCase().contains("категория:")) {
                String catName = Utils.getParametersUpdate(newPar);
                Category category = categoryService.getByName(catName.toLowerCase());
                product.setCategory(category);
            } else if (newPar.toLowerCase().contains("тип:")) {
                String typeName = Utils.getParametersUpdate(newPar);
                Type[] types = Type.values();

                for (Type newType : types) {
                    if (newType.label.equals(typeName)) {
                        product.setType(newType);
                    }
                }
            }
        }

        return product;
    }

    private SendPhoto pagedProductOrder(Long chatId, String userName, int from) {
        SendPhoto sendPhoto = new SendPhoto();
        Product product;
        sendPhoto.setChatId(String.valueOf(chatId));

        try {
            if (paginationOrder >= 2) {
                Integer value = Integer.valueOf(String.valueOf(paginationOrder * 7 - 7 + from));
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

            if (paginationOrder == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberOrderButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberOrderButton(paginationOrder * 7));
            }

        } catch (IndexOutOfBoundsException e) {
            log.error("Error occurred: " + e.getMessage());

            sendPhoto.setCaption(ExceptionMessage.PRODUCT_NOT_FOUND.label);
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

            if (paginationOrder == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberOrderButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberOrderButton(paginationOrder * 7));
            }
        } catch (RuntimeException e) {
            log.error("Error occurred: " + e.getMessage());

            sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
        }

        return sendPhoto;
    }

    private SendPhoto pagedProductStock(Long chatId, String userName, int from) {
        SendPhoto sendPhoto = new SendPhoto();
        Product product;
        sendPhoto.setChatId(String.valueOf(chatId));

        try {
            if (paginationStock >= 2) {
                Integer value = Integer.valueOf(String.valueOf(paginationStock * 7 - 7 + from));
                product = productService.getByType(Type.STOCK, Utils.paged(value, 1)).get(0);
            } else {
                product = productService.getByType(Type.STOCK, Utils.paged(from, 1)).get(0);
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

            if (paginationStock == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberStockButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberStockButton(paginationStock * 7));
            }

        } catch (IndexOutOfBoundsException e) {
            log.error("Error occurred: " + e.getMessage());

            sendPhoto.setCaption(ExceptionMessage.PRODUCT_NOT_FOUND.label);
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

            if (paginationStock == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberStockButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberStockButton(paginationStock * 7));
            }
        } catch (RuntimeException e) {
            log.error("Error occurred: " + e.getMessage());

            sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
        }

        return sendPhoto;
    }

    private SendPhoto pagedFeedback(Long chatId, String userName, int from) {
        SendPhoto sendPhoto = new SendPhoto();
        Feedback feedback;
        sendPhoto.setChatId(String.valueOf(chatId));

        try {
            if (paginationFeedback >= 2) {
                Integer value = Integer.valueOf(String.valueOf(paginationFeedback * 7 - 7 + from));
                feedback = feedbackService.getAll(Utils.paged(value, 1)).get(0);
            } else {
                feedback = feedbackService.getAll(Utils.paged(from, 1)).get(0);
            }

            if (profileService.containsProfile(userName)) {
                sendPhoto.setCaption(feedback.toString());
            } else {
                sendPhoto.setCaption(feedback.toStringDto());
            }

            if (feedback.getPathFile() != null) {
                sendPhoto.setPhoto(new InputFile(new File(feedback.getPathFile())));
            } else {
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
            }

            if (paginationFeedback == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberFeedbackButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberFeedbackButton(paginationFeedback * 7));
            }

        } catch (IndexOutOfBoundsException e) {
            log.error("Error occurred: " + e.getMessage());

            sendPhoto.setCaption(ExceptionMessage.FEEDBACK_NOT_FOUND.label);
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

            if (paginationFeedback == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberFeedbackButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberFeedbackButton(paginationFeedback * 7));
            }
        } catch (RuntimeException e) {
            log.error("Error occurred: " + e.getMessage());

            sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
        }

        return sendPhoto;
    }

}
