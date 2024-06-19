package ru.pankkovv.visitmanager.message;

public enum ButtonMessage {
    START("Главное меню \uD83D\uDC8C"),
    HELP("Помощь \uD83E\uDD7A"),
    REGISTRATION("Регистрация ");

    public final String label;

    ButtonMessage(String label) {
        this.label = label;
    }
}
