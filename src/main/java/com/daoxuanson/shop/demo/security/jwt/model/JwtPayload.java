package com.daoxuanson.shop.demo.security.jwt.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtPayload {
    private long id;
    private String userName;
    private String email;
    private String role;
}
