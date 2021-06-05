package com.daoxuanson.shop.demo.service;

import com.daoxuanson.shop.demo.model.Response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.function.Function;

public interface BaseService<T, ID> {
    <RQ> void save(RQ request,Function<RQ, T> transform);

    void delete(ID id);

    <RP> RP findById(ID id, Function<T, RP> transform);

    <RP> RP findOne(Specification<T> filter, Function<T, RP> transform);

    T findOne(Specification<T> filter);

    <RP> PageResponse<RP> findOne(Specification<T> filter, Pageable pageable, Function<T, RP> transform);

    <RP> List<RP> filter(Specification<T> filter, Function<T, RP> transform);
}
