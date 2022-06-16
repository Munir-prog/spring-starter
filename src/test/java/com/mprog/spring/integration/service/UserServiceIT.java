package com.mprog.spring.integration.service;

import com.mprog.spring.database.entity.Role;
import com.mprog.spring.dto.UserCreateEditDto;
import com.mprog.spring.dto.UserReadDto;
import com.mprog.spring.integration.IntegrationTestBase;
import com.mprog.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Long USER_60 = 60L;
    private static final Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
    void findAll() {
        var result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        var maybeUser = userService.findById(USER_1);
        assertThat(maybeUser).isPresent();
        maybeUser.ifPresent(user -> assertThat(user.getUsername()).isEqualTo("ivan@gmail.com"));
    }

    @Test
    void create() {
        var userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                "Test",
                LocalDate.now(),
                Role.ADMIN,
                COMPANY_1
        );

        var actualResult = userService.create(userCreateEditDto);
        assertThat(userCreateEditDto.getUsername()).isEqualTo(actualResult.getUsername());
        assertThat(userCreateEditDto.getBirthDate()).isEqualTo(actualResult.getBirthDate());
        assertThat(userCreateEditDto.getLastname()).isEqualTo(actualResult.getLastname());
        assertThat(userCreateEditDto.getFirstname()).isEqualTo(actualResult.getFirstname());
        assertThat(userCreateEditDto.getCompanyId()).isEqualTo(actualResult.getCompany().id());
        assertThat(userCreateEditDto.getRole()).isSameAs(actualResult.getRole());
    }

    @Test
    void update() {
        var userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                "Test",
                LocalDate.now(),
                Role.ADMIN,
                COMPANY_1
        );

        var actualResult = userService.update(USER_1, userCreateEditDto);
        assertThat(actualResult).isPresent();
        actualResult.ifPresent(user -> {
            assertThat(userCreateEditDto.getUsername()).isEqualTo(user.getUsername());
            assertThat(userCreateEditDto.getBirthDate()).isEqualTo(user.getBirthDate());
            assertThat(userCreateEditDto.getLastname()).isEqualTo(user.getLastname());
            assertThat(userCreateEditDto.getFirstname()).isEqualTo(user.getFirstname());
            assertThat(userCreateEditDto.getCompanyId()).isEqualTo(user.getCompany().id());
            assertThat(userCreateEditDto.getRole()).isSameAs(user.getRole());
        });
    }


    @Test
    void delete() {
        var firstResult = userService.delete(USER_1);
        var secondResult = userService.delete(USER_60);
        assertThat(firstResult).isTrue();
        assertThat(secondResult).isFalse();
    }
}
