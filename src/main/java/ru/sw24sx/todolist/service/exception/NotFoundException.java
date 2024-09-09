package ru.sw24sx.todolist.service.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super(String.format("Entity with id %s not found", id));
    }
}
