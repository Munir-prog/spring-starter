package com.mprog.spring.database.repository;

import com.mprog.spring.bpp.Auditing;
import com.mprog.spring.bpp.InjectBean;
import com.mprog.spring.bpp.Transaction;
import com.mprog.spring.database.entity.Company;
import com.mprog.spring.database.pool.ConnectionPool;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @InjectBean
    private ConnectionPool connectionPool;

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

//    public CompanyRepository(ConnectionPool connectionPool) {
//        this.connectionPool = connectionPool;
//    }
//
//    public static CompanyRepository of(ConnectionPool connectionPool) {
//        return new CompanyRepository(connectionPool);
//    }
}
