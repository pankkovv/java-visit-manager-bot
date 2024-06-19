package ru.pankkovv.visitmanager.message;

public enum ButtonData {
    START_BTN("start_btn"),
    HELP_BTN("help_btn"),
    REGISTRATION_BTN("registration_btn");

    public final String label;

    ButtonData(String label) {
        this.label = label;
    }
}
