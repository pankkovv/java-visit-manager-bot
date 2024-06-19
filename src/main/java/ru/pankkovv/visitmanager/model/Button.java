package ru.pankkovv.visitmanager.model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.pankkovv.visitmanager.message.ButtonData;
import ru.pankkovv.visitmanager.message.ButtonMessage;

import java.util.ArrayList;
import java.util.List;

public class Button {

    public static InlineKeyboardMarkup getStartButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineOne = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineTwo = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineThree = new ArrayList<>();

        InlineKeyboardButton startButton = new InlineKeyboardButton();
        startButton.setText(ButtonMessage.START.label);
        startButton.setCallbackData(ButtonData.START_BTN.label);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText(ButtonMessage.HELP.label);
        helpButton.setCallbackData(ButtonData.HELP_BTN.label);

        InlineKeyboardButton regButton = new InlineKeyboardButton();
        regButton.setText(ButtonMessage.REGISTRATION.label);
        regButton.setCallbackData(ButtonData.REGISTRATION_BTN.label);

        rowInLineOne.add(startButton);
        rowInLineTwo.add(helpButton);
        rowInLineThree.add(regButton);

        rowsInLine.add(rowInLineOne);
        rowsInLine.add(rowInLineTwo);
        rowsInLine.add(rowInLineThree);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        return inlineKeyboardMarkup;
    }
}
