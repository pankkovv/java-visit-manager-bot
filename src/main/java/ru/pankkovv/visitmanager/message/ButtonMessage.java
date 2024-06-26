package ru.pankkovv.visitmanager.message;

public enum ButtonMessage {
    START("Главное меню \uD83D\uDC8C"),
    HELP("Помощь \uD83E\uDD7A"),
    ABOUT_ME("Обо мне ");

    public final String label;

    ButtonMessage(String label) {
        this.label = label;
    }
}
