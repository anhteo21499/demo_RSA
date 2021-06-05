package com.daoxuanson.shop.demo.model.Response;


import com.daoxuanson.shop.demo.exception.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private ResponseStatus status;
    private Object baseData;

    public BaseResponse(final ResponseStatus status, final Object baseData) {
        this.status = status;
        this.baseData = baseData;
    }

    public BaseResponse(final ResponseStatus status) {
        this.status = status;
        this.baseData = null;
    }


}
