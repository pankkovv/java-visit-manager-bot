package ru.pankkovv.visitmanager.bot.message;

public enum ButtonMessage {
    START("Главное меню \uD83D\uDC8C"),
    HELP("Помощь \uD83E\uDD7A"),
    COMMAND_PROFILE("Команды для анкеты"),
    COMMAND_PRODUCT("Команды для товаров"),
    COMMAND_FEEDBACK("Команды для отзывов"),
    COMMAND_CATEGORY("Команды для категорий"),
    BACK_ALL_COMMAND("Назад"),
    ABOUT_ME("Обо мне \uD83D\uDC69\u200D\uD83C\uDFA8"),
    VIEW_PRODUCTS("Товары \uD83D\uDECD\uFE0F"),
    VIEW_PRODUCTS_ORDER("Товары на заказ"),
    VIEW_PRODUCTS_STOCK("Товары в наличии"),
    BACK("Назад"),
    VIEW_FEEDBACKS("Отзывы \uD83E\uDD70"),
    MAKE_ORDER("Оформить заказ  \uD83D\uDCB8"),
    QUEUE("Очередь"),
    START_QUEUE("Попасть в очередь"),
    JOIN_QUEUE("Выйти из очереди"),
    VIEW_QUEUE("Посмотреть номер в очереди");

    public final String label;

    ButtonMessage(String label) {
        this.label = label;
    }
}
