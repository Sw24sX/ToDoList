package ru.sw24sx.todolist.dto.web.todo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ToDoDeleteRequest {

    private List<Long> ids;
}
