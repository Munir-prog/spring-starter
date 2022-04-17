package com.mprog.spring.service;

import com.mprog.spring.database.entity.Company;
import com.mprog.spring.database.repository.CompanyRepository;
import com.mprog.spring.database.repository.CrudRepository;
import com.mprog.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
