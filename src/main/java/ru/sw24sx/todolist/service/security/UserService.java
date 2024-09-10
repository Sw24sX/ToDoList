package ru.sw24sx.todolist.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sw24sx.todolist.adapter.repository.UserRepository;
import ru.sw24sx.todolist.domain.user.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    public User getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }
}
