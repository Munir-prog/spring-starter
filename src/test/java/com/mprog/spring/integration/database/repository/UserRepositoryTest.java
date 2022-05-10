package com.mprog.spring.integration.database.repository;

import com.mprog.spring.database.entity.User;
import com.mprog.spring.database.repository.UserRepository;
import com.mprog.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;
    
    @Test
    void checkQueries() {
        var users = userRepository.findByAll("a", "ov");
        Assertions.assertThat(users).hasSize(3);
        System.out.println(users);
    }

    @Test
    void checkFindAllByUsername() {
        var users = userRepository.findAllBy("petr@gmail.com");
        Assertions.assertThat(users).hasSize(1);
    }
}