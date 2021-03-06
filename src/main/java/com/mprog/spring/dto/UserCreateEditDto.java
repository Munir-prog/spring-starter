package com.mprog.spring.dto;

import com.mprog.spring.database.entity.Role;
import com.mprog.spring.validation.UserInfo;
import com.mprog.spring.validation.group.CreateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {

    @Email
    String username;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    @Size(min = 3, max = 64)
    String firstname;

    String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    Role role;
    Integer companyId;

    MultipartFile image;
}
