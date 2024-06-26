package ru.pankkovv.visitmanager.message;

public enum CommandMessage {
    START("Давайте начнём\n\n" +
            "Я бот-визитка, рад знакомству!\n" +
            "Сейчас я вас познакомлю с творчеством @eeekisel."),
    HELP_COMMON("Если возникнут вопросы по работе с ботом\n" +
            "или же предложения и комментарии, вы можете нам написать:\n" +
            "@pankkovv - создатель и администратор бота\n" +
            "@eee_kisel - заказчик бота\n"),
    HELP_ADMIN("Если возникнут вопросы по работе с ботом\n" +
            "или же предложения и комментарии, вы можете нам написать:\n" +
            "@pankkovv - создатель и администратор бота\n" +
            "@eee_kisel - заказчик бота\n"),
    DELETE_FORM_COMMAND("Ваши данные успешно удалены");

    public final String label;

    CommandMessage(String label) {
        this.label = label;
    }
}
