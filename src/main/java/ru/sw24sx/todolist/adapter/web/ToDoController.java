package ru.sw24sx.todolist.adapter.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sw24sx.todolist.domain.user.User;
import ru.sw24sx.todolist.dto.web.todo.*;
import ru.sw24sx.todolist.service.ToDoListService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
@Log4j2
public class ToDoController {

    private final ToDoListService toDoListService;

    @GetMapping
    public List<ToDoEntry> getAll(@AuthenticationPrincipal User user) {

        log.debug("Get all todo for user");
        return toDoListService.getAll(user);
    }

    @PostMapping
    public ToDoEntry create(@AuthenticationPrincipal User user, @Valid @RequestBody ToDoCreateRequest request) {

        log.debug("Create todo {}", request);
        return toDoListService.create(request, user);
    }

    @PutMapping("{id}")
    public ToDoEntry update(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @Valid @RequestBody ToDoUpdateRequest updateRequest) {

        log.debug("Update todo {} with {}", id, updateRequest);
        return toDoListService.update(id, updateRequest, user);
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal User user, @Valid @RequestBody ToDoDeleteRequest request) {

        log.debug("Try remove todo {}", request);
        toDoListService.deleteAllByIds(request, user);
    }
}
