package ru.sw24sx.todolist.adapter.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todo")
public class ToDoController {

    @GetMapping
    public String test() {
        return "Example";
    }
}
