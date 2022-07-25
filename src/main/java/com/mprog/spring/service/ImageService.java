package com.mprog.spring.service;

import com.mprog.spring.database.repository.CompanyRepository;
import com.mprog.spring.dto.CompanyReadDto;
import com.mprog.spring.listener.entity.AccessType;
import com.mprog.spring.listener.entity.EntityEvent;
import com.mprog.spring.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final CompanyRepository companyCrudRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final CompanyReadMapper companyReadMapper;
    @Value("${app.image.bucket:/home/munir/IdeaProjects/spring-starter/images}")
    private final String bucket;

    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        var fullPath = Path.of(bucket, imagePath);
        try (content) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

}
