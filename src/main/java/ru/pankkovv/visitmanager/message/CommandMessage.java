package ru.pankkovv.visitmanager.message;

public enum CommandMessage {
    START("Давайте начнём\n\n" +
            "Я бот-визитка, рад знакомству!\n" +
            "Сейчас я вас познакомлю с творчеством @eee_kisel."),
    HELP_COMMON("Если возникнут вопросы по работе с ботом\n" +
            "или же предложения и комментарии, вы можете нам написать:\n" +
            "@pankkovv - создатель и администратор бота\n" +
            "@eee_kisel - заказчик бота\n"),
    HELP_ADMIN("Если возникнут вопросы по работе с ботом\n" +
            "или же предложения и комментарии, вы можете нам написать:\n" +
            "@pankkovv - создатель и администратор бота\n" +
            "@eee_kisel - заказчик бота\n"),
    REGISTRATION("Для регистрации необходимо ввести код-пароль");

    public final String label;

    CommandMessage(String label) {
        this.label = label;
    }
}
