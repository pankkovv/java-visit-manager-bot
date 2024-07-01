package ru.pankkovv.visitmanager.bot.model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.pankkovv.visitmanager.bot.message.ButtonData;
import ru.pankkovv.visitmanager.bot.message.ButtonMessage;

import java.util.ArrayList;
import java.util.List;

public class Button {
    public static int pagination = 1;
    public static int sizeListProductOrder = 0;

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

    public static InlineKeyboardMarkup getNumberButton(int number) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();
        int i = number - 6;

        InlineKeyboardButton oneButton = new InlineKeyboardButton();
        oneButton.setCallbackData(ButtonData.ONE.label);

        InlineKeyboardButton twoButton = new InlineKeyboardButton();
        twoButton.setCallbackData(ButtonData.TWO.label);

        InlineKeyboardButton threeButton = new InlineKeyboardButton();
        threeButton.setCallbackData(ButtonData.THREE.label);

        InlineKeyboardButton fourButton = new InlineKeyboardButton();
        fourButton.setCallbackData(ButtonData.FOUR.label);

        InlineKeyboardButton fiveButton = new InlineKeyboardButton();
        fiveButton.setCallbackData(ButtonData.FIVE.label);

        InlineKeyboardButton sixButton = new InlineKeyboardButton();
        sixButton.setCallbackData(ButtonData.SIX.label);

        InlineKeyboardButton sevenButton = new InlineKeyboardButton();
        sevenButton.setCallbackData(ButtonData.SEVEN.label);

        InlineKeyboardButton nextButton = new InlineKeyboardButton();
        nextButton.setText(">>");
        nextButton.setCallbackData(ButtonData.NEXT.label);

        InlineKeyboardButton prevButton = new InlineKeyboardButton();
        prevButton.setText("<<");
        prevButton.setCallbackData(ButtonData.PREV.label);

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        while(i < number){
            oneButton.setText(String.valueOf(i));
            ++i;

            twoButton.setText(String.valueOf(i));
            ++i;

            threeButton.setText(String.valueOf(i));
            ++i;

            fourButton.setText(String.valueOf(i));
            ++i;

            fiveButton.setText(String.valueOf(i));
            ++i;

            sixButton.setText(String.valueOf(i));
            ++i;

            sevenButton.setText(String.valueOf(i));
            ++i;
        }

        rowInLineOne.add(oneButton);
        rowInLineOne.add(twoButton);
        rowInLineOne.add(threeButton);
        rowInLineOne.add(fourButton);
        rowInLineOne.add(fiveButton);
        rowInLineOne.add(sixButton);
        rowInLineOne.add(sevenButton);

        rowInLineTwo.add(prevButton);
        rowInLineTwo.add(nextButton);

        rowInLineThree.add(startButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }
}
