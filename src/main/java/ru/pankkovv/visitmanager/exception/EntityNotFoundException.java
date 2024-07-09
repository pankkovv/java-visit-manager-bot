package ru.pankkovv.visitmanager.exception;

public class EntityNotFoundException extends RuntimeException{
    String message;

    public EntityNotFoundException(String message) {
        this.message = message;
    }
}
