package ru.sw24sx.todolist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sw24sx.todolist.adapter.repository.ToDoRepository;
import ru.sw24sx.todolist.domain.todo.ToDo;
import ru.sw24sx.todolist.domain.user.User;
import ru.sw24sx.todolist.dto.domain.ToDoState;
import ru.sw24sx.todolist.dto.web.todo.ToDoCreateRequest;
import ru.sw24sx.todolist.dto.web.todo.ToDoDeleteRequest;
import ru.sw24sx.todolist.dto.web.todo.ToDoUpdateRequest;
import ru.sw24sx.todolist.service.ToDoListService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ToDoListServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoListService toDoListService;

    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        when(user.getId()).thenReturn(1L);
        when(toDoRepository.save(any())).thenAnswer(i -> {
            ToDo toDo = (ToDo) i.getArguments()[0];
            toDo.setId(1L);
            return toDo;
        });
    }

    @Test
    public void createTodoTest() {
        var request = ToDoCreateRequest.builder()
                .header("Test header")
                .description("Test description")
                .state(ToDoState.READY)
                .build();

        var result = toDoListService.create(request, user);
        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("Test header", result.getHeader()),
                () -> assertEquals("Test description", result.getDescription()),
                () -> assertEquals(ToDoState.READY, result.getState()),
                () -> assertNotNull(result.getCreatedAt())
        );
    }

    @Test
    public void updateToDoTest() {
        var request = ToDoUpdateRequest.builder()
                .header("Test header")
                .description("Test description")
                .state(ToDoState.READY)
                .build();

        var todoEntity = ToDo.builder()
                .header("start")
                .description("descr")
                .createdAt(LocalDateTime.now())
                .state(ToDoState.DONE)
                .user(user)
                .build();
        todoEntity.setId(1L);

        when(toDoRepository.findById(1L)).thenReturn(Optional.of(todoEntity));

        var result = toDoListService.update(1L, request, user);

        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("Test header", result.getHeader()),
                () -> assertEquals("Test description", result.getDescription()),
                () -> assertEquals(ToDoState.READY, result.getState()),
                () -> assertNotNull(result.getCreatedAt())
        );
    }

    @Test
    public void deleteTest() {
        var request = ToDoDeleteRequest.builder()
                .ids(List.of(1L, 2L))
                .build();

        var todoUserMock = mock(ToDo.class);
        when(todoUserMock.getUser()).thenReturn(user);
        when(todoUserMock.getId()).thenReturn(1L);

        var otherUserMock = mock(User.class);
        when(otherUserMock.getId()).thenReturn(2L);
        var todoOtherUser = mock(ToDo.class);
        when(todoOtherUser.getUser()).thenReturn(otherUserMock);
        when(todoOtherUser.getId()).thenReturn(2L);

        when(toDoRepository.findAllById(any()))
                .thenReturn(List.of(
                        todoOtherUser,
                        todoUserMock
                ));

        toDoListService.deleteAllByIds(request, user);

        var capture = ArgumentCaptor.forClass(List.class);
        verify(toDoRepository).deleteAllById(capture.capture());

        assertEquals(1, capture.getValue().size());
        assertEquals(1L, capture.getValue().get(0));
    }
}
