package ru.sw24sx.todolist.adapter.web.advice;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sw24sx.todolist.dto.web.ErrorResponse;

import java.util.List;

import static java.util.Objects.requireNonNullElse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;


@ControllerAdvice
public class BindAdvice {

    private static final String DEFAULT_CODE = "BAD_REQUEST";
    private static final String DEFAULT_MESSAGE = "Bad request";

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(@NonNull BindException e) {

        return status(BAD_REQUEST)
                .body(convertErrors(e.getAllErrors()));
    }

    private static ErrorResponse convertErrors(List<ObjectError> objectErrors) {

        return ErrorResponse.builder()
                .errors(objectErrors.stream()
                        .filter(objectError -> objectError.getCode() != null || objectError.getDefaultMessage() != null)
                        .map(BindAdvice::convertError)
                        .toList())
                .build();
    }

    private static ErrorResponse.Error convertError(ObjectError objectError) {

        var errorMessage = "typeMismatch".equals(objectError.getCode()) ?
                "Failed to convert " + objectError.getObjectName() : objectError.getDefaultMessage();


        return new ErrorResponse.Error(
                requireNonNullElse(objectError.getCode(), DEFAULT_CODE),
                requireNonNullElse(errorMessage, DEFAULT_MESSAGE),
                objectError instanceof FieldError ? ((FieldError) objectError).getField() : null);
    }
}
