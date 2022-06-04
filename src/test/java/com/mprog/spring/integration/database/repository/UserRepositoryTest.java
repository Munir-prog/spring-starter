package com.mprog.spring.integration.database.repository;

import com.mprog.spring.database.entity.Role;
import com.mprog.spring.database.entity.User;
import com.mprog.spring.database.repository.UserRepository;
import com.mprog.spring.dto.PersonalInfo;
import com.mprog.spring.dto.UserFilter;
import com.mprog.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkBatch() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
        assertThat(users.get(0).lastname()).isEqualTo("Petrov");
    }

    @Test
    @Commit
    void checkAuditing() {
        var user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }


    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(
                null,
                "ov",
                LocalDate.now()
        );
        var allByFilter = userRepository.findAllByFilter(filter);
        System.out.println();
        assertThat(allByFilter).hasSize(4);
    }


    @Test
    void checkProjection() {
        var personalInfos = userRepository.findAllByCompanyId(1);
        assertThat(personalInfos).hasSize(2);
        System.out.println();
    }


    @Test
    void checkPageable() {
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getId()));


        while(slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getId()));
        }
        //        assertThat(result).hasSize(2);
    }


    @Test
    void checkSort() {
        var sortBy = Sort.sort(User.class);
        var sort = sortBy.by(User::getFirstname)
                .and(sortBy.by(User::getLastname));

        var sortByNames = Sort.by("firstname").and(Sort.by("lastname"));

        var users = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(users).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        var topUser = userRepository.findTopByOrderByIdDesc();
        assertThat(topUser).isPresent();
        topUser.ifPresent(user -> assertThat(user.getId()).isEqualTo(5L));
    }


    @Test
    void checkQueries() {
        var users = userRepository.findByAll("a", "ov");
        assertThat(users).hasSize(3);
        System.out.println(users);
    }

    @Test
    void checkFindAllByUsername() {
        var users = userRepository.findAllBy("petr@gmail.com");
        assertThat(users).hasSize(1);
    }

    @Test
    void checkUpdate() {
        var user = userRepository.getById(1L);
        assertSame(Role.ADMIN, user.getRole());

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);

        var theSameUser = userRepository.getById(1L);
        assertSame(Role.USER, theSameUser.getRole());

        assertThat(resultCount).isEqualTo(2);
    }
}