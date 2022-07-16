package com.mprog.spring.dto;

import com.mprog.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
    Role role;
    Integer companyId;
}
