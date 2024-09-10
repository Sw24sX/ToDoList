package ru.sw24sx.todolist.adapter.web.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.sw24sx.todolist.service.exception.NotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NotFoundExceptionAdvice {

    @ExceptionHandler({
            NotFoundException.class,
            NoHandlerFoundException.class
    })
    public ResponseEntity<Void> handleCustomException() {
        return ResponseEntity.notFound().build();
    }
}
