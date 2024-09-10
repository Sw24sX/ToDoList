package ru.sw24sx.todolist.dto.web.todo;

import lombok.Builder;
import lombok.Data;
import ru.sw24sx.todolist.domain.todo.ToDo;
import ru.sw24sx.todolist.dto.domain.ToDoState;

import java.time.LocalDateTime;

@Data
@Builder
public class ToDoEntry {

    private Long id;

    private String header;

    private String description;

    private LocalDateTime createdAt;

    private ToDoState state;

    public static ToDoEntry of(ToDo toDo) {
        return builder()
                .id(toDo.getId())
                .header(toDo.getHeader())
                .description(toDo.getDescription())
                .state(toDo.getState())
                .createdAt(toDo.getCreatedAt())
                .build();
    }
}
