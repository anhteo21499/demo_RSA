package com.daoxuanson.shop.demo.repository.specification;

import com.daoxuanson.shop.demo.entity.User;
import com.daoxuanson.shop.demo.model.request.user.UserAuthRequest;
import com.daoxuanson.shop.demo.model.request.user.UserFilterRequest;
import com.daoxuanson.shop.demo.utils.PasswordHasher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> filter(UserFilterRequest userFilterRequest){
        return Specification.where(withUserNameLike(userFilterRequest.getUserName()))
                .and(withAddress(userFilterRequest.getAddress()));
    }

    public static Specification<User> authFilter(UserAuthRequest userAuthRequest){
        return Specification.where(withUserName(userAuthRequest.getUserName()))
                .and(withPassword(userAuthRequest.getPassword()));
    }

    private static Specification<User> withUserName(String userName) {
        if (StringUtils.isBlank(userName)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userName"), userName);
    }

    private static Specification<User> withPassword(String password) {
        if (StringUtils.isBlank(password)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("password"), password);
    }

    private static Specification<User> withUserNameLike(String userName) {
        if (StringUtils.isBlank(userName)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("userName"), "%" + userName + "%");
    }

    public static Specification<User> withAddress(String address){
        if (StringUtils.isBlank(address)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("address"), address);
    }

}
