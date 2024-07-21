package ru.pankkovv.visitmanager.exception;

public enum ExceptionMessage {
    PRODUCT_NOT_FOUND("К сожалению указанный продукт не получилось найти :("),
    FEEDBACK_NOT_FOUND("К сожалению указанный отзыв не получилось найти :("),
    CATEGORY_NOT_FOUND("К сожалению указанную категорию не получилось найти :("),
    NOT_FOUND_COMMAND_EXCEPTION("Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату.");

    public final String label;

    ExceptionMessage(String label) {
        this.label = label;
    }
}
