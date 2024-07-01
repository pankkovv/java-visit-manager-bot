package ru.pankkovv.visitmanager.bot.message;

public enum ButtonData {
    START_BTN("start_btn"),
    HELP_BTN("help_btn"),
    ABOUT_ME_BTN("about_me_btn"),
    VIEW_PRODUCTS_BTN("view_products_btn"),
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    NEXT("next"),
    PREV("prev");

    public final String label;

    ButtonData(String label) {
        this.label = label;
    }
}
