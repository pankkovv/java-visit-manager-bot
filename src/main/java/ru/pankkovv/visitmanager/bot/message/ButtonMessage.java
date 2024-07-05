package ru.pankkovv.visitmanager.bot.message;

public enum ButtonMessage {
    START("Главное меню \uD83D\uDC8C"),
    HELP("Помощь \uD83E\uDD7A"),
    COMMAND_PROFILE("Команды для анкеты"),
    COMMAND_PRODUCT("Команды для товаров"),
    COMMAND_FEEDBACK("Команды для отзывов"),
    COMMAND_CATEGORY("Команды для категорий"),
    BACK_ALL_COMMAND("Назад"),
    ABOUT_ME("Обо мне"),
    VIEW_PRODUCTS("Товары"),
    VIEW_PRODUCTS_ORDER("Прайслист"),
    VIEW_PRODUCTS_STOCK("Товары в наличии"),
    BACK("Назад"),
    VIEW_FEEDBACKS("Отзывы"),
    MAKE_ORDER("Оформить заказ");

    public final String label;

    ButtonMessage(String label) {
        this.label = label;
    }
}
