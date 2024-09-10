package ru.sw24sx.todolist.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collection;

@Value
@Builder
public class ErrorResponse {

    @NonNull
    @Singular
    Collection<Error> errors;

    @Value
    @AllArgsConstructor
    public static class Error {

        @NonNull
        String code;

        @NonNull
        String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String field;
    }
}
