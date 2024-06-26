package ru.pankkovv.visitmanager.utils;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Utils {
    public static String getUserName(Update update) {
        if (update.hasCallbackQuery()) {
            return (update.getCallbackQuery().getFrom().getUserName() != null) ?
                    update.getCallbackQuery().getFrom().getUserName() :
                    String.format("%s %s", update.getCallbackQuery().getFrom().getLastName(),
                            update.getCallbackQuery().getFrom().getFirstName());
        } else {
            return (update.getMessage().getFrom().getUserName() != null) ? update.getMessage().getFrom().getUserName() :
                    String.format("%s %s", update.getMessage().getFrom().getLastName(),
                            update.getMessage().getFrom().getFirstName());
        }
    }

    public static Long getChatId(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        } else {
            return update.getMessage().getChatId();
        }
    }

    public static String getText(Message msg) {
        if (msg.getText() != null) {
            return msg.getText();
        } else if (msg.getCaption() != null) {
            return msg.getCaption();
        } else {
            return "";
        }
    }
}
