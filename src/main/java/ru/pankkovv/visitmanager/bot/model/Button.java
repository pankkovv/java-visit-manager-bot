package ru.pankkovv.visitmanager.bot.model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.pankkovv.visitmanager.bot.message.ButtonData;
import ru.pankkovv.visitmanager.bot.message.ButtonMessage;

import java.util.ArrayList;
import java.util.List;

public class Button {
    public static int paginationOrder = 1;
    public static int paginationStock = 1;
    public static int sizeListProductOrder = 0;
    public static int sizeListProductStock = 0;

    public static InlineKeyboardMarkup getStartButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton aboutMeButton = new InlineKeyboardButton();
        aboutMeButton.setText(ButtonMessage.ABOUT_ME.label);
        aboutMeButton.setCallbackData(ButtonData.ABOUT_ME_BTN.label);

        InlineKeyboardButton viewProducts = new InlineKeyboardButton();
        viewProducts.setText(ButtonMessage.VIEW_PRODUCTS.label);
        viewProducts.setCallbackData(ButtonData.VIEW_PRODUCTS_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(helpButton);
        rowInLineThree.add(aboutMeButton);
        rowInLineFour.add(viewProducts);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getProductButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();

        InlineKeyboardButton productOrderButton = new InlineKeyboardButton();
        productOrderButton.setText(ButtonMessage.VIEW_PRODUCTS_ORDER.label);
        productOrderButton.setCallbackData(ButtonData.VIEW_PRODUCTS_ORDER_BTN.label);

        InlineKeyboardButton productStockButton = new InlineKeyboardButton();
        productStockButton.setText(ButtonMessage.VIEW_PRODUCTS_STOCK.label);
        productStockButton.setCallbackData(ButtonData.VIEW_PRODUCTS_STOCK_BTN.label);

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);


        rowInLineOne.add(productOrderButton);
        rowInLineTwo.add(productStockButton);
        rowInLineThree.add(startButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getNumberOrderButton(int number) {
        int c = 1;
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();

        InlineKeyboardButton oneButton = new InlineKeyboardButton();
        oneButton.setCallbackData(ButtonData.ONE_ORDER.label);

        InlineKeyboardButton twoButton = new InlineKeyboardButton();
        twoButton.setCallbackData(ButtonData.TWO_ORDER.label);

        InlineKeyboardButton threeButton = new InlineKeyboardButton();
        threeButton.setCallbackData(ButtonData.THREE_ORDER.label);

        InlineKeyboardButton fourButton = new InlineKeyboardButton();
        fourButton.setCallbackData(ButtonData.FOUR_ORDER.label);

        InlineKeyboardButton fiveButton = new InlineKeyboardButton();
        fiveButton.setCallbackData(ButtonData.FIVE_ORDER.label);

        InlineKeyboardButton sixButton = new InlineKeyboardButton();
        sixButton.setCallbackData(ButtonData.SIX_ORDER.label);

        InlineKeyboardButton sevenButton = new InlineKeyboardButton();
        sevenButton.setCallbackData(ButtonData.SEVEN_ORDER.label);

        InlineKeyboardButton nextButton = new InlineKeyboardButton();
        nextButton.setText(">>");
        nextButton.setCallbackData(ButtonData.NEXT_ORDER.label);

        InlineKeyboardButton prevButton = new InlineKeyboardButton();
        prevButton.setText("<<");
        prevButton.setCallbackData(ButtonData.PREV_ORDER.label);

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText(ButtonMessage.BACK.label);
        backButton.setCallbackData(ButtonData.BACK.label);

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        if (number > 7) {
            c = number - 6;
        }

        if (c <= sizeListProductOrder) {
            oneButton.setText(String.valueOf(c));
            rowInLineOne.add(oneButton);
            ++c;
        }

        if (c <= sizeListProductOrder) {
            twoButton.setText(String.valueOf(c));
            rowInLineOne.add(twoButton);
            ++c;
        }

        if (c <= sizeListProductOrder) {
            threeButton.setText(String.valueOf(c));
            rowInLineOne.add(threeButton);
            ++c;
        }

        if (c <= sizeListProductOrder) {
            fourButton.setText(String.valueOf(c));
            rowInLineOne.add(fourButton);
            ++c;
        }

        if (c <= sizeListProductOrder) {
            fiveButton.setText(String.valueOf(c));
            rowInLineOne.add(fiveButton);
            ++c;
        }

        if (c <= sizeListProductOrder) {
            sixButton.setText(String.valueOf(c));
            rowInLineOne.add(sixButton);
            ++c;
        }

        if (c <= sizeListProductOrder) {
            sevenButton.setText(String.valueOf(c));
            rowInLineOne.add(sevenButton);
            ++c;
        }

        if (sizeListProductOrder > 7) {
            rowInLineTwo.add(prevButton);
            rowInLineTwo.add(nextButton);
        }

        rowInLineThree.add(backButton);
        rowInLineFour.add(startButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getNumberStockButton(int number) {
        int i = 1;
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineFour = new ArrayList<>();

        InlineKeyboardButton oneButton = new InlineKeyboardButton();
        oneButton.setCallbackData(ButtonData.ONE_STOCK.label);

        InlineKeyboardButton twoButton = new InlineKeyboardButton();
        twoButton.setCallbackData(ButtonData.TWO_STOCK.label);

        InlineKeyboardButton threeButton = new InlineKeyboardButton();
        threeButton.setCallbackData(ButtonData.THREE_STOCK.label);

        InlineKeyboardButton fourButton = new InlineKeyboardButton();
        fourButton.setCallbackData(ButtonData.FOUR_STOCK.label);

        InlineKeyboardButton fiveButton = new InlineKeyboardButton();
        fiveButton.setCallbackData(ButtonData.FIVE_STOCK.label);

        InlineKeyboardButton sixButton = new InlineKeyboardButton();
        sixButton.setCallbackData(ButtonData.SIX_STOCK.label);

        InlineKeyboardButton sevenButton = new InlineKeyboardButton();
        sevenButton.setCallbackData(ButtonData.SEVEN_STOCK.label);

        InlineKeyboardButton nextButton = new InlineKeyboardButton();
        nextButton.setText(">>");
        nextButton.setCallbackData(ButtonData.NEXT_STOCK.label);

        InlineKeyboardButton prevButton = new InlineKeyboardButton();
        prevButton.setText("<<");
        prevButton.setCallbackData(ButtonData.PREV_STOCK.label);

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText(ButtonMessage.BACK.label);
        backButton.setCallbackData(ButtonData.BACK.label);

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        if (number > 7) {
            i = number - 6;
        }

        if (i <= sizeListProductStock) {
            oneButton.setText(String.valueOf(i));
            rowInLineOne.add(oneButton);
            ++i;
        }

        if (i <= sizeListProductStock) {
            twoButton.setText(String.valueOf(i));
            rowInLineOne.add(twoButton);
            ++i;
        }

        if (i <= sizeListProductStock) {
            threeButton.setText(String.valueOf(i));
            rowInLineOne.add(threeButton);
            ++i;
        }

        if (i <= sizeListProductStock) {
            fourButton.setText(String.valueOf(i));
            rowInLineOne.add(fourButton);
            ++i;
        }

        if (i <= sizeListProductStock) {
            fiveButton.setText(String.valueOf(i));
            rowInLineOne.add(fiveButton);
            ++i;
        }

        if (i <= sizeListProductStock) {
            sixButton.setText(String.valueOf(i));
            rowInLineOne.add(sixButton);
            ++i;
        }

        if (i <= sizeListProductStock) {
            sevenButton.setText(String.valueOf(i));
            rowInLineOne.add(sevenButton);
            ++i;
        }

        if (sizeListProductStock > 7) {
            rowInLineTwo.add(prevButton);
            rowInLineTwo.add(nextButton);
        }

        rowInLineThree.add(backButton);
        rowInLineFour.add(startButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);
        rowsInLine.add(rowInLineFour);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }
}
