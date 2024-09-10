package ru.sw24sx.todolist.dto.web.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.sw24sx.todolist.dto.domain.ToDoState;

@Data
@Builder
public class ToDoCreateRequest {

    @NotBlank(message = "Заголовок не может быть пустыми")
    private String header;

    private String description;

    @NotNull
    private ToDoState state;
}
