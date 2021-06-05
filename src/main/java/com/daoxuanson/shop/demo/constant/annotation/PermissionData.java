package com.daoxuanson.shop.demo.constant.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.daoxuanson.shop.demo.constant.RoleConstant.*;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, METHOD})
@Retention(RUNTIME)
@PreAuthorize(
        ROLE_ADMIN
                + OR
                + ROLE_ROOT_ADMIN
                + OR
                + ROLE_USER
                + OR
                + ROLE_PARTNER
                + OR
                + ROLE_AGENCY
)
public @interface PermissionData {
}
