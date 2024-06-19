package ru.pankkovv.visitmanager.message;

public enum ExceptionMessage {
    NOT_FOUND_COMMAND_EXCEPTION("Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату."),
    NOT_FOUND_RULES_ADMIN_EXCEPTION("Для выполнения данной команды необходимы права администратора.");

    public final String label;

    ExceptionMessage(String label) {
        this.label = label;
    }
}
