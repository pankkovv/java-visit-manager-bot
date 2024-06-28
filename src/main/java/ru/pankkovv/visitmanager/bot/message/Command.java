package ru.pankkovv.visitmanager.bot.message;

public enum Command {
    START("start"),
    HELP("help"),
    REGISTRATION("registration");

    public final String label;

    Command(String label) {
        this.label = label;
    }
}
