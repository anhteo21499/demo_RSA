package com.daoxuanson.shop.demo.model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public final class PageResponse<T> {
    private long totalItem;
    private List<T> data;

    private PageResponse(final long totalItem,final List<T> data) {
        this.totalItem = totalItem;
        this.data = data;
    }

    public static <T> PageResponse<T> of(final long totalItem,final List<T> data){
        return new PageResponse<>(totalItem, data);
    }

    public long getTotalItem() {
        return totalItem;
    }

    public List<T> getData() {
        return data;
    }
}
