package ru.pankkovv.visitmanager.bot.message;

public enum CommandMessage {
    START("Давайте начнём\n\n" +
            "Я бот-визитка, рад знакомству!\n" +
            "Сейчас я вас познакомлю с творчеством @eeekisel."),
    HELP_COMMON("Если возникнут вопросы по работе с ботом\n" +
            "или же предложения и комментарии, вы можете нам написать:\n" +
            "@pankkovv - создатель и администратор бота\n" +
            "@eee_kisel - заказчик бота\n"),
    HELP_ADMIN("Основные команды по работе с ботом\n\n" +
            "В этом разделе описаны правила взаимодействия с ботом." +
            "Следует четко выполнять инструкции, чтобы не произошла неведомая магия и сломала бот :)"),
    HELP_ADMIN_COMMAND_PROFILE("Создание, редактирование и удаление данных о себе\n\n" +
            "создать-анкету\n" +
            "описание, которое хотите, чтобы бот отображал\n" +
            "+ по желанию, можно прикрепить 1 фото" +
            "\n\n" +
            "редактировать-анкету\n" +
            "новое описание, которое хотите, чтобы отображал бот\n" +
            "+ по желанию, можно прикрепить 1 новое фото" +
            "\n\n" +
            "удалить-анкету"),

    HELP_ADMIN_COMMAND_PRODUCT("Создание, редактирование и удаление товара\n\n" +
            "создать-товар\n" +
            "имя товара\n" +
            "описание\n" +
            "стоимость\n" +
            "категория\n" +
            "тип товара (заказ или наличие)\n" +
            "+ по желанию, можно прикрепить 1 фото" +
            "\n\n" +
            "редактировать-товар\n" +
            "id\n" +
            "имя: новое имя\n" +
            "описание: новое описание\n" +
            "стоимость: новая стоимость\n" +
            "категория: новая категория\n" +
            "тип товара (заказ или наличие): новый тип\n" +
            "+ по желанию, можно прикрепить 1 новое фото" +
            "\n\n" +
            "удалить-товар\n" +
            "id\n"),
    HELP_ADMIN_COMMAND_FEEDBACK("Создание, редактирование и удаление отзыва\n\n" +
            "создать-отзыв\n" +
            "описание\n" +
            "+ по желанию, можно прикрепить 1 фото" +
            "\n\n" +
            "редактировать-отзыв\n" +
            "id\n" +
            "описание: новое описание\n" +
            "+ по желанию, можно прикрепить 1 новое фото" +
            "\n\n" +
            "удалить-отзыв\n" +
            "id"),
    HELP_ADMIN_COMMAND_CATEGORY("Создание и удаление категории\n\n" +
            "создать-категорию\n" +
            "описание" +
            "\n\n" +
            "удалить-категорию\n" +
            "id"),
    MAKE_ORDER("https://t.me/eeekisel/842"),
    DELETE_FORM_COMMAND("Ваши данные успешно удалены");

    public final String label;

    CommandMessage(String label) {
        this.label = label;
    }
}
