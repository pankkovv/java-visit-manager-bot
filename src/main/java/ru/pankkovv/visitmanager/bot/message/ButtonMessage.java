package ru.pankkovv.visitmanager.bot.message;

public enum ButtonMessage {
    START("Главное меню \uD83D\uDC8C"),
    HELP("Помощь \uD83E\uDD7A"),
    ABOUT_ME("Обо мне"),
    VIEW_PRODUCTS("Посмотреть товары для заказа");

    public final String label;

    ButtonMessage(String label) {
        this.label = label;
    }
}
