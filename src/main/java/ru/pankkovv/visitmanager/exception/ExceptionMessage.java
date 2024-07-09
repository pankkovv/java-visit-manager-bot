package ru.pankkovv.visitmanager.exception;

public enum ExceptionMessage {
    PRODUCT_NOT_FOUND("К сожалению указанный продукт не получилось найти :("),
    FEEDBACK_NOT_FOUND("К сожалению указанный отзыв не получилось найти :("),
    CATEGORY_NOT_FOUND("К сожалению указанную категорию не получилось найти :(");

    public final String label;

    ExceptionMessage(String label) {
        this.label = label;
    }
}
