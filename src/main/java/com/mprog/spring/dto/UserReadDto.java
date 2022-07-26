package com.mprog.spring.dto;

import com.mprog.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    String firstname;
    String lastname;
    String image;
    LocalDate birthDate;
    Role role;
    CompanyReadDto company;
}
