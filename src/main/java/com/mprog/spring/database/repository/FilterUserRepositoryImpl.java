package com.mprog.spring.database.repository;

import com.mprog.spring.database.entity.QUser;
import com.mprog.spring.database.entity.User;
import com.mprog.spring.database.querydsl.QPredicates;
import com.mprog.spring.dto.UserFilter;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.mprog.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .buildOr();
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();

        //        var criteriaBuilder = entityManager.getCriteriaBuilder();
//        var criteria = criteriaBuilder.createQuery(User.class);
//        var user = criteria.from(User.class);
//        criteria.select(user);
//        List<Predicate> predicates = new ArrayList<>();
//        if (userFilter.firstname() != null) {
//            predicates.add(criteriaBuilder.like(user.get("firstname"), userFilter.firstname()));
//        }
//        if (userFilter.lastname() != null) {
//            predicates.add(criteriaBuilder.like(user.get("lastname"), userFilter.lastname()));
//        }
//        if (userFilter.birthDate() != null) {
//            predicates.add(criteriaBuilder.lessThan(user.get("birthDate"), userFilter.birthDate()));
//        }
//        criteria.where(predicates.toArray(Predicate[]::new));
//        return entityManager.createQuery(criteria).getResultList();
    }
}
