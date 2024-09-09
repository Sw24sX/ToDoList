package ru.sw24sx.todolist.dto.web.todo;

import lombok.Builder;
import lombok.Data;
import ru.sw24sx.todolist.dto.domain.ToDoState;

@Data
@Builder
public class ToDoCreateRequest {

    private String header;

    private String description;

    private ToDoState state;
}
