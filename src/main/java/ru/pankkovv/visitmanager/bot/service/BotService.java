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
import ru.pankkovv.visitmanager.category.model.Category;
import ru.pankkovv.visitmanager.category.service.CategoryService;
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

import static ru.pankkovv.visitmanager.bot.model.Button.*;

@Service
@AllArgsConstructor
public class BotService {

    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ProductMapper productMapper;

    @Autowired
    private final CategoryService categoryService;

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
                try {
                    Long id = Long.parseLong(parameters[1]);
                    Product product = productService.getById(id);

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
                        }
                    }

                    sendPhoto.setCaption(productService.update(product).toString());

                    if (product.getPathFile() != null) {
                        sendPhoto.setPhoto(new InputFile(new File(product.getPathFile())));
                    } else {
                        sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    }

                } catch (RuntimeException e) {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());
                break;

            case "удалить-товар":
                try {
                    Long id = Long.parseLong(parameters[1]);
                    productService.getById(id);
                    productService.delete(id);

                    sendPhoto.setCaption("Товар успешно удален!");
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                } catch (RuntimeException e) {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());

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
                try {
                    Long id = Long.parseLong(parameters[1]);
                    Product product = productService.getById(id);

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
                        }
                    }

                    product.setPathFile(photo.getPath());

                    sendPhoto.setCaption(productService.update(product).toString());

                    if (product.getPathFile() != null) {
                        sendPhoto.setPhoto(new InputFile(new File(product.getPathFile())));
                    } else {
                        sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                    }

                } catch (RuntimeException e) {
                    sendPhoto.setCaption(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                    sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                }

                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setReplyMarkup(Button.getStartButton());

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
            case "back":
                sendPhoto.setChatId(String.valueOf(chatId));
                sendPhoto.setCaption("Я помогу вам сделать выбор при оформлении заказа.\n" +
                        "Можно нажать \"Прайслист\" и вы увидите, описание работ, выполняемых на индивидуальных условиях \n" +
                        "Кнопка \"Товары в наличии\" покажет, какие уже готовые работы вы можете заказать прямо сейчас");
                sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));
                sendPhoto.setReplyMarkup(Button.getProductButton());

                break;

            // Просмотр прайслиста
            case "view_products_order_btn":
                sizeListProductOrder = productService.getByType(Type.ORDER, Utils.paged(0, Integer.MAX_VALUE)).size();
                sendPhoto = pagedProductOrder(chatId, userName, 0);
                break;

            //Пагинация
            case "next_order":
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
                sizeListProductStock = productService.getByType(Type.STOCK, Utils.paged(0, Integer.MAX_VALUE)).size();
                sendPhoto = pagedProductStock(chatId, userName, 0);
                break;

            //Пагинация
            case "next_stock":
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
        }

        return sendPhoto;
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
            sendPhoto.setCaption("Извините, похоже данный товар не получилось найти :(");
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

            if (paginationOrder == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberOrderButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberOrderButton(paginationOrder * 7));
            }
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
            sendPhoto.setCaption("Извините, похоже данный товар не получилось найти :(");
            sendPhoto.setPhoto(new InputFile(new File("img/start.jpg")));

            if (paginationStock == 1) {
                sendPhoto.setReplyMarkup(Button.getNumberStockButton(7));
            } else {
                sendPhoto.setReplyMarkup(Button.getNumberStockButton(paginationStock * 7));
            }
        }

        return sendPhoto;
    }

}
