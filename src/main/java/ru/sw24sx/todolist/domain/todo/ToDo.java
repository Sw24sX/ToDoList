package ru.sw24sx.todolist.domain.todo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import ru.sw24sx.todolist.domain.BaseEntity;
import ru.sw24sx.todolist.domain.user.User;
import ru.sw24sx.todolist.dto.domain.ToDoState;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo")
public class ToDo extends BaseEntity {

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private ToDoState state;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
