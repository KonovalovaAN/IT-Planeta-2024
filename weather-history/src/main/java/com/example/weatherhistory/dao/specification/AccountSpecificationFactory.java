package com.example.weatherhistory.dao.specification;

import com.example.weatherhistory.entity.Account;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class AccountSpecificationFactory {
    public static Specification<Account> firstNameLike(String firstName) {
        return like(firstName);
    }

    public static Specification<Account> lastNameLike(String lastName) {
        return like(lastName);
    }

    public static Specification<Account> emailLike(String email) {
        return like(email);
    }

    private static Specification<Account> like(String s) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (s != null) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("%s".formatted(s))),
                        ("%" + s + "%").toLowerCase());
            } else {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
        };
    }
}
