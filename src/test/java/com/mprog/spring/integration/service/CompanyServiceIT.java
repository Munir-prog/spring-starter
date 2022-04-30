package com.mprog.spring.integration.service;

import com.mprog.spring.ApplicationRunner;
import com.mprog.spring.config.DatabaseProperties;
import com.mprog.spring.database.entity.Company;
import com.mprog.spring.dto.CompanyReadDto;
import com.mprog.spring.integration.annotation.IT;
import com.mprog.spring.listener.entity.EntityEvent;
import com.mprog.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) analog in spring.properties
// Analog of
// it:
    //@ActiveProfiles("test")
    //@SpringBootTest
    // Analog of
    // it:
        //@ExtendWith(SpringExtension.class)
        //@ContextConfiguration(
        //        classes = ApplicationRunner.class,
        //        initializers = ConfigDataApplicationContextInitializer.class
        //)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

    }

}
