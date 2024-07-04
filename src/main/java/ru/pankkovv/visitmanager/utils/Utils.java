package ru.pankkovv.visitmanager.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public static String[] getParameters(String text) {
        return text.replaceAll("/", "")
                .split("\n");
    }

    public static String getParametersUpdate(String text) {
        return text.replace(" :", ":")
                .replace(": ", ":")
                .split(":")[1];
    }

    public static Pageable paged(Integer from, Integer size) {
        Pageable page;
        if (from != null && size != null) {
            if (from < 0 || size < 0) {
                throw new RuntimeException();
            }
            page = PageRequest.of(from > 0 ? from / size : 0, size);
        } else {
            page = PageRequest.of(0, 4);
        }
        return page;
    }
}
