package com.daoxuanson.shop.demo.constant;

public interface RoleConstant {
    String ROLE_USER = "hasAuthority('USER')";
    String ROLE_ADMIN = "hasAuthority('ADMIN')";
    String ROLE_ROOT_ADMIN = "hasAuthority('ROOT_ADMIN')";
    String ROLE_AGENCY = "hasAuthority('AGENCY')";
    String ROLE_PARTNER = "hasAuthority('PARTNER')";
    String OR = " or ";

    String ROOT_ADMIN = "ROOT_ADMIN";
    String ADMIN = "ADMIN";
    String USER = "USER";
    String AGENCY = "AGENCY";
    String PARTNER = "PARTNER";
}
