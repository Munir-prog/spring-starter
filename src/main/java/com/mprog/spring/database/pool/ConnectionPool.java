package com.mprog.spring.database.pool;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Component("pool1")
public class ConnectionPool {

    private final String username;
    private final Integer poolSize;
//    private List<Object> args;
//    private Map<String, Object> properties;

    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}") Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("Connection pool initialization");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Connection pool is cleaning");
    }
}
