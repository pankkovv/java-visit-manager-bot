package ru.pankkovv.visitmanager.exception;

public enum ExceptionMessage {
    DESIGNER_NOT_FOUND("К сожалению, автор еще не заполнил информацию о себе."),
    PRODUCT_NOT_FOUND("К сожалению, указанный продукт не получилось найти :("),
    FEEDBACK_NOT_FOUND("К сожалению, указанный отзыв не получилось найти :("),
    CATEGORY_NOT_FOUND("К сожалению, указанную категорию не получилось найти :("),
    QUEUE_NOT_FOUND("К сожалению, вас не получилось получилось найти в очереди :("),
    QUEUE_IS_EMPTY("В очереди еще нет ни одного клиента :("),
    DUPLICATE_ENTITY_EXCEPTION("Такая запись уже существует."),
    NOT_FOUND_COMMAND_EXCEPTION("Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату."),
    ADMIN_NOT_FOUND_COMMAND_EXCEPTION("Кажется, вам не доступны права админа, чтобы совершить это действие.");

    public final String label;

    ExceptionMessage(String label) {
        this.label = label;
    }
}
