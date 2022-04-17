package com.mprog.spring.database.repository;

import com.mprog.spring.bpp.Auditing;
import com.mprog.spring.bpp.InjectBean;
import com.mprog.spring.bpp.Transaction;
import com.mprog.spring.database.entity.Company;
import com.mprog.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

//    @Resource(name = "pool1")
//    @Autowired
//    @Qualifier("pool1")
    private ConnectionPool pool1;

    @Autowired
    private List<ConnectionPool> poolList;

    @Value("${db.pool.size}")
    private Integer poolSize;

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


    @Autowired
    public void setPool1(ConnectionPool pool1) {
        this.pool1 = pool1;
    }
}
