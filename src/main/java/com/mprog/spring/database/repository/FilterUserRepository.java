package com.mprog.spring.database.repository;

import com.mprog.spring.database.entity.User;
import com.mprog.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);
}
