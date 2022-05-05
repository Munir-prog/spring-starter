package com.mprog.spring.database.repository;

import com.mprog.spring.database.entity.Company;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Integer> {

    Optional<Company> findById(Integer id);

    void delete(Company entity);

}
