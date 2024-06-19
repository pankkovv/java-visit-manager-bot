package ru.pankkovv.visitmanager.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class CommandService {

    public SendMessage mapCommand(Update update) {
        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    sendMessage.setChatId(String.valueOf(chatId));
                    sendMessage.setText("Start");
                    break;
            }
        }

        return sendMessage;
    }
}
