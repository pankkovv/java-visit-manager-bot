package ru.pankkovv.visitmanager.bot.message;

public enum ButtonData {
    START_BTN("start_btn"),
    HELP_BTN("help_btn"),
    ABOUT_ME_BTN("about_me_btn"),
    VIEW_FEEDBACKS_BTN("view_feedbacks_btn"),
    VIEW_PRODUCTS_BTN("view_products_btn"),
    VIEW_PRODUCTS_ORDER_BTN("view_products_order_btn"),
    VIEW_PRODUCTS_STOCK_BTN("view_products_stock_btn"),
    BACK("back"),
    //Пагинация для товаров с типом ORDER
    ONE_ORDER("one_order"),
    TWO_ORDER("two_order"),
    THREE_ORDER("three_order"),
    FOUR_ORDER("four_order"),
    FIVE_ORDER("five_order"),
    SIX_ORDER("six_order"),
    SEVEN_ORDER("seven_order"),
    NEXT_ORDER("next_order"),
    PREV_ORDER("prev_order"),
    //Пагинация для товаров с типом STOCK
    ONE_STOCK("one_stock"),
    TWO_STOCK("two_stock"),
    THREE_STOCK("three_stock"),
    FOUR_STOCK("four_stock"),
    FIVE_STOCK("five_stock"),
    SIX_STOCK("six_stock"),
    SEVEN_STOCK("seven_stock"),
    NEXT_STOCK("next_stock"),
    PREV_STOCK("prev_stock"),
    //Пагинация отзывов
    ONE_FEEDBACK("one_feedback"),
    TWO_FEEDBACK("two_feedback"),
    THREE_FEEDBACK("three_feedback"),
    FOUR_FEEDBACK("four_feedback"),
    FIVE_FEEDBACK("five_feedback"),
    SIX_FEEDBACK("six_feedback"),
    SEVEN_FEEDBACK("seven_feedback"),
    NEXT_FEEDBACK("next_feedback"),
    PREV_FEEDBACK("prev_feedback"),;


    public final String label;

    ButtonData(String label) {
        this.label = label;
    }
}
