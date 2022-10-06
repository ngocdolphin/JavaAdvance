package com.vti.specification;

import com.vti.entity.Account;
import com.vti.entity.Account_;
import com.vti.entity.Department_;
import com.vti.entity.Role;
import com.vti.form.AccountFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class AccountSpecification {
    public static Specification<Account> buildWhere(AccountFilterForm form){
        if (form == null) {
            return null;
        }
        return hasNameLike(form.getSearch())
                .or(hasDepartmentNameLike(form.getSearch()))
                .and(hasDepartmentIdEqual(form.getDepartmentId()))
                .and(hasAccountIdGreaterThanOrEqualTo(form.getMinId()))
                .and(hasAccountIdLessThanOrEqualTo(form.getMaxId()))
                .and(hasCreatedDateGreaterThanOrEqualTo(form.getMinCreatedDate()))
                .and(hasCreatedDateLessThanOrEqualTo(form.getMaxCreatedDate()))
                .and(hasYearGreaterThanOrEqualTo(form.getMinYear()))
                .and(hasYearLessThanOrEqualTo(form.getMaxYear()))
                .and(hasRoleEqual(form.getRole()));
    }

    private static Specification<Account> hasNameLike(String search){
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return builder.like(root.get(Account_.username), "%" + search.trim() + "%");
        };
    }

    private static Specification<Account> hasDepartmentNameLike(String search) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)) {
                return null;
            }
            return builder.like(root.get(Account_.department).get(Department_.name), "%" + search.trim() + "%");
        };
    }

    private static Specification<Account> hasDepartmentIdEqual(Integer departmentId){
        return (root, query, builder) -> {
            if (departmentId == null){
                return null;
            }
            return builder.equal(root.get(Account_.department).get(Department_.id),departmentId);
        };
    }

    private static Specification<Account> hasAccountIdGreaterThanOrEqualTo(Integer minAccountId){
        return (root, query, builder) -> {
            if (minAccountId == null){
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get(Account_.id), minAccountId);
        };
    }
    private static Specification<Account> hasAccountIdLessThanOrEqualTo(Integer maxAccountId){
        return (root, query, builder) -> {
            if (maxAccountId == null){
                return null;
            }
            return builder.lessThanOrEqualTo(root.get(Account_.id), maxAccountId);
        };
    }
    private static Specification<Account> hasCreatedDateGreaterThanOrEqualTo(LocalDate minCreatedDate){
        return (root, query, builder) -> {
            if (minCreatedDate == null){
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get(Account_.createdDate).as(LocalDate.class), minCreatedDate);
        };
    }

    private static Specification<Account> hasCreatedDateLessThanOrEqualTo(LocalDate maxCreatedDate){
        return (root, query, builder) -> {
                if (maxCreatedDate == null){
                    return null;
                }
                return builder.lessThanOrEqualTo(root.get(Account_.createdDate).as(LocalDate.class), maxCreatedDate);
            };
    }

    private static Specification<Account> hasYearGreaterThanOrEqualTo(Integer minYear){
        return (root, query, builder) -> {
            if (minYear == null){
                return null;
            }
            return builder.greaterThanOrEqualTo(
                    builder.function("YEAR", Integer.class, root.get(Account_.createdDate)), minYear);
        };
    }

    private static Specification<Account> hasYearLessThanOrEqualTo(Integer maxYear){
        return (root, query, builder) -> {
            if (maxYear == null){
                return null;
            }
            return builder.lessThanOrEqualTo(
                    builder.function("YEAR", Integer.class, root.get(Account_.createdDate)), maxYear);
        };
    }

    private static Specification<Account> hasRoleEqual(Role role){
        return (root, query, builder) -> {
            if (role == null){
                return null;
            }
            return builder.equal(root.get(Account_.role),role);
        };
    }


}
