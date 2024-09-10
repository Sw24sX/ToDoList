package ru.sw24sx.todolist.dto.web.todo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDeleteRequest {

    @NotEmpty
    private List<Long> ids;
}
