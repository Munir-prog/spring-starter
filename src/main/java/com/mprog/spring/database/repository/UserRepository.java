package com.mprog.spring.database.repository;

import com.mprog.spring.database.entity.User;
import com.mprog.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findByAll(String firstname, String lastname);

    @Query(
            value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true
    )
    List<User> findAllBy(String username);
}
