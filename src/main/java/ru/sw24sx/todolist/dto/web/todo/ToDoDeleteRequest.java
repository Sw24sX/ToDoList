package ru.sw24sx.todolist.dto.web.todo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ToDoDeleteRequest {

    @NotEmpty
    private List<Long> ids;
}
