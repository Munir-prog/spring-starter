package com.mprog.spring.config;

import com.mprog.spring.database.repository.CrudRepository;
import com.mprog.web.config.WebConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(
        basePackages = "com.mprog.spring",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository"),
        }
)
public class ApplicationConfiguration {

}
