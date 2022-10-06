package com.vti.specification;

import com.vti.entity.*;
import com.vti.form.DepartmentFilterForm;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.time.LocalDate;

public class DepartmentSpecification {
    public static Specification<Department> buildWhere(DepartmentFilterForm form){
        if (form == null){
            return null;
        }
        return hasNameLike(form.getSearch())
                .or(hasAccountUserNameLike(form.getSearch()))
                .and(hasCreatedDateGreaterThanOrEqualTo(form.getMinCreatedDate()))
                .and(hasCreatedDateLessThanOrEqualTo(form.getMaxCreatedDate()))
                .and(hasTypeEqual(form.getType()));
    }

    private static Specification<Department> hasNameLike(String search){
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            return builder.like(root.get(Department_.name), "%" + search.trim() + "%");
        };
    }

    private static Specification<Department> hasAccountUserNameLike(String search){
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            Join<Department, Account> join = root.join(Department_.accounts, JoinType.LEFT);
            return builder.like(join.get(Account_.username),"%" + search.trim() + "%");
        };
    }

    private static Specification<Department> hasCreatedDateGreaterThanOrEqualTo(LocalDate minCreatedDate){
        return (root, query, builder) -> {
            if (minCreatedDate == null){
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get(Department_.createdDate).as(LocalDate.class), minCreatedDate);
        };
    }

    private static Specification<Department> hasCreatedDateLessThanOrEqualTo(LocalDate maxCreatedDate){
        return (root, query, builder) -> {
            if (maxCreatedDate == null){
                return null;
            }
            return builder.lessThanOrEqualTo(root.get(Department_.createdDate).as(LocalDate.class), maxCreatedDate);
        };
    }

    private static Specification<Department> hasTypeEqual(Type type){
        return (root, query, builder) -> {
            if (type == null){
                return null;
            }
            return builder.equal(root.get(Department_.type), type);
        };
    }

}
