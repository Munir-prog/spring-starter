package com.mprog.spring.service;

import com.mprog.spring.database.entity.Company;
import com.mprog.spring.database.repository.CrudRepository;
import com.mprog.spring.dto.CompanyReadDto;
import com.mprog.spring.listener.entity.AccessType;
import com.mprog.spring.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CrudRepository<Integer, Company> companyCrudRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyCrudRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }

}
