package com.daoxuanson.shop.demo.controller;

import com.daoxuanson.shop.demo.exception.StatusTemplate;
import com.daoxuanson.shop.demo.model.Response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseController {

    public ResponseEntity<BaseResponse> success(){
        BaseResponse response = new BaseResponse(StatusTemplate.SUCCESS);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse> success(Object object){
        return ResponseEntity.ok(new BaseResponse(StatusTemplate.SUCCESS, object));
    }

}
