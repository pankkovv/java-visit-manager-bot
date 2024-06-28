package ru.pankkovv.visitmanager.bot.message;

public enum ButtonData {
    START_BTN("start_btn"),
    HELP_BTN("help_btn"),
    ABOUT_ME_BTN("about_me_btn");

    public final String label;

    ButtonData(String label) {
        this.label = label;
    }
}
