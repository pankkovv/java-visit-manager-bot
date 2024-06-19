package ru.pankkovv.visitmanager.model;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pankkovv.visitmanager.config.BotConfig;
import ru.pankkovv.visitmanager.service.CommandService;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final CommandService service;

    public TelegramBot(BotConfig config, CommandService service) {
        this.config = config;
        this.service = service;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(service.mapCommand(update));
        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }
    }
}
