package com.mprog.spring.database.repository;

import com.mprog.spring.bpp.Auditing;
import com.mprog.spring.bpp.InjectBean;
import com.mprog.spring.bpp.Transaction;
import com.mprog.spring.database.entity.Company;
import com.mprog.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
// commented while learning type filters in component-scan xml function
//@Repository
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool1;
    private final List<ConnectionPool> poolList;
    private final Integer poolSize;

    public CompanyRepository(ConnectionPool pool1,
                             List<ConnectionPool> poolList,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.pool1 = pool1;
        this.poolList = poolList;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("find by id method");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete entity");
    }

}
