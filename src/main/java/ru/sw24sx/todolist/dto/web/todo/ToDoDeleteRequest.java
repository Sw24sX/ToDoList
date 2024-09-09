package ru.sw24sx.todolist.dto.web.todo;

import lombok.Data;

import java.util.List;

@Data
public class ToDoDeleteRequest {

    private List<Long> ids;
}
