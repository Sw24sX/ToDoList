package ru.sw24sx.todolist.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sw24sx.todolist.domain.user.User;
import ru.sw24sx.todolist.dto.web.todo.*;
import ru.sw24sx.todolist.service.ToDoListService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoListService toDoListService;

    @PostMapping
    public ToDoEntry create(@AuthenticationPrincipal User user, @RequestBody ToDoCreateRequest request) {

        return toDoListService.create(request, user);
    }

    @PutMapping("{id}")
    public ToDoEntry update(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody ToDoUpdateRequest updateRequest) {

        return toDoListService.update(id, updateRequest, user);
    }
    @GetMapping
    public List<ToDoEntry> getAll(@AuthenticationPrincipal User user, @RequestBody ToDoFilterRequest request) {

        return toDoListService.getAll(request, user);
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal User user, @RequestBody ToDoDeleteRequest request) {

        toDoListService.deleteAllByIds(request, user);
    }
}
