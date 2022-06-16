package com.mprog.spring.database.repository;

import com.mprog.spring.database.entity.QUser;
import com.mprog.spring.database.entity.Role;
import com.mprog.spring.database.entity.User;
import com.mprog.spring.database.querydsl.QPredicates;
import com.mprog.spring.dto.PersonalInfo;
import com.mprog.spring.dto.UserFilter;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mprog.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT
                firstname,
                lastname,
                birth_date
            FROM users
            WHERE company_id = ?
                AND role = ?
            """;

    private static final String UPDATE_COMPANY_AND_ROLE = """
            UPDATE users
            SET company_id = ?,
                role = ?
            WHERE id = ?
            """;

    private static final String UPDATE_COMPANY_AND_ROLE_NAMED = """
            UPDATE users
            SET company_id = :companyId,
                role = :role
            WHERE id = :id
            """;
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(
                FIND_BY_COMPANY_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                ),
                companyId, role.name()
        );
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        var batchArgs = users.stream()
                .map(user -> new Object[]{user.getCompany().getId(), user.getRole().name(), user.getId()})
                .toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, batchArgs);
    }

    @Override
    public void updateCompanyAndRoleNamed(List<User> users) {
        var parameterSources = users.stream()
                .map(user -> Map.of(
                        "companyId", user.getCompany().getId(),
                        "role", user.getRole().name(),
                        "id", user.getId()
                )).map(MapSqlParameterSource::new)
                .toArray(MapSqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE_NAMED, parameterSources);
    }
}
