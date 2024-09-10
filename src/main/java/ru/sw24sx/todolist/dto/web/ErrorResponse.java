package ru.sw24sx.todolist.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Collection;

@Value
@Builder
public class ErrorResponse {

    @Schema(description = "Коллекция ошибок", required = true)
    @NonNull
    @Singular
    Collection<Error> errors;

    @Value
    @AllArgsConstructor
    public static class Error {

        @Schema(description = "Код ошибки", required = true)
        @NonNull
        String code;

        @Schema(description = "Сообщение об ошибке", required = true)
        @NonNull
        String message;

        @Schema(description = "Поле на котором возникла ошибка", nullable = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String field;

        public Error(@NonNull String code, @NonNull String message) {
            this.code = code;
            this.message = message;
            this.field = null;
        }
    }
}
