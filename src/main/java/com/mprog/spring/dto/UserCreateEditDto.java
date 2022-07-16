package com.mprog.spring.dto;

import com.mprog.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
    LocalDate birthDate;
    Role role;
    Integer companyId;
}
