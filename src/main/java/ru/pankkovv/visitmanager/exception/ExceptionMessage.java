package ru.pankkovv.visitmanager.exception;

public enum ExceptionMessage {
    PRODUCT_NOT_FOUND("К сожалению, указанный продукт не получилось найти :("),
    FEEDBACK_NOT_FOUND("К сожалению, указанный отзыв не получилось найти :("),
    CATEGORY_NOT_FOUND("К сожалению, указанную категорию не получилось найти :("),
    QUEUE_NOT_FOUND("К сожалению, вас не получилось получилось найти в очереди :("),
    QUEUE_IS_EMPTY("В очереди еще нет ни одного клиента :("),
    DUPLICATE_ENTITY_EXCEPTION("Такая запись уже существует."),
    NOT_FOUND_COMMAND_EXCEPTION("Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату.");

    public final String label;

    ExceptionMessage(String label) {
        this.label = label;
    }
}
