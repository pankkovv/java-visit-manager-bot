package ru.pankkovv.visitmanager.product.model;

public enum Type {
    ORDER("Заказ"),
    STOCK("Наличие");

    public final String label;

    Type(String label) {
        this.label = label;
    }
}
