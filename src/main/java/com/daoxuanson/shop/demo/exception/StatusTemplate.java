package com.daoxuanson.shop.demo.exception;

import org.springframework.http.HttpStatus;

public interface StatusTemplate {
    ResponseStatus SUCCESS =
            new ResponseStatus("SHOP-SUCCESS", "SUCCESS", HttpStatus.OK);
    ResponseStatus USER_NOT_FOUND =
            new ResponseStatus("SHOP-USER-01", "user not found", HttpStatus.NOT_FOUND);
    ResponseStatus EXPIRE_TOKEN =
            new ResponseStatus("SHOP-TOKEN-EXPIRED", "toke expired", HttpStatus.BAD_REQUEST);
    ResponseStatus TOKEN_IN_VALID =
            new ResponseStatus("SHOP-TOKEN-INVALID", "invalid token", HttpStatus.BAD_REQUEST);
}
