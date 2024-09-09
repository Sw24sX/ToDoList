package ru.sw24sx.todolist.dto.web.todo;

import lombok.Data;
import ru.sw24sx.todolist.dto.domain.ToDoState;

@Data
public class ToDoCreateRequest {

    private String header;

    private String description;

    private ToDoState state;
}
