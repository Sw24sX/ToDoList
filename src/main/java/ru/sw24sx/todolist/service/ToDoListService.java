package ru.sw24sx.todolist.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sw24sx.todolist.adapter.repository.ToDoRepository;
import ru.sw24sx.todolist.domain.BaseEntity;
import ru.sw24sx.todolist.domain.todo.ToDo;
import ru.sw24sx.todolist.domain.user.User;
import ru.sw24sx.todolist.dto.web.todo.*;
import ru.sw24sx.todolist.service.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoListService {

    private final ToDoRepository toDoRepository;

    public List<ToDoEntry> getAll(ToDoFilterRequest request, User user) {

        return toDoRepository.findAllByUser(user).stream()
                .map(ToDoEntry::of)
                .toList();
    }

    @Transactional
    public ToDoEntry create(ToDoCreateRequest request, User user) {

        var entity = ToDo.builder()
                .header(request.getHeader())
                .description(request.getDescription())
                .state(request.getState())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        var createdEntity = toDoRepository.save(entity);
        return ToDoEntry.of(createdEntity);
    }

    @Transactional
    public ToDoEntry update(Long id, ToDoUpdateRequest request, User user) {

        var entity = toDoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (!user.getId().equals(entity.getUser().getId())) {
            throw new NotFoundException(id);
        }

        if (!StringUtils.isEmpty(request.getDescription())) {
            entity.setDescription(request.getDescription());
        }

        if (!StringUtils.isEmpty(request.getHeader())) {
            entity.setHeader(request.getHeader());
        }

        if (request.getState() != null) {
            entity.setState(request.getState());
        }

        return ToDoEntry.of(toDoRepository.save(entity));
    }

    @Transactional
    public void deleteAllByIds(ToDoDeleteRequest request, User user) {

        var ids = toDoRepository.findAllById(request.getIds()).stream()
                        .filter(it -> it.getUser().getId().equals(user.getId()))
                        .map(BaseEntity::getId)
                        .toList();

        toDoRepository.deleteAllById(ids);
    }
}
