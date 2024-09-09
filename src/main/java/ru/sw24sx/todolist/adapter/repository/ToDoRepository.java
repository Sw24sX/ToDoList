package ru.sw24sx.todolist.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sw24sx.todolist.domain.todo.ToDo;
import ru.sw24sx.todolist.domain.user.User;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByUser(User user);
}
