package com.daoxuanson.shop.demo.service.impl;

import com.daoxuanson.shop.demo.exception.ObjectNotFoundException;
import com.daoxuanson.shop.demo.model.Response.PageResponse;
import com.daoxuanson.shop.demo.repository.BaseRepository;
import com.daoxuanson.shop.demo.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractServiceImpl<T, ID> implements BaseService<T, ID> {

    protected final BaseRepository baseRepository;

    public AbstractServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public <RQ> void save(RQ rq, Function<RQ, T> transform) {
        T t = transform.apply(rq);
        baseRepository.save(t);
    }

    @Override
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    @Override
    public <RP> RP findById(ID id, Function<T, RP> transform) {
        Optional<T> t = baseRepository.findById(id);

        t.orElseThrow(() -> new ObjectNotFoundException("Not found with: " + id));

        return transform.apply(t.get());
    }

    @Override
    public <RP> RP findOne(Specification<T> filter, Function<T, RP> transform) {
        Optional<T> t = baseRepository.findOne(filter);
        t.orElseThrow(()-> new ObjectNotFoundException("NOT FOUND WITH condition"));
        return transform.apply(t.get());
    }

    @Override
    public T findOne(Specification<T> filter) {
        Optional<T> t = baseRepository.findOne(filter);
        t.orElseThrow(()-> new ObjectNotFoundException("NOT FOUND WITH condition"));
        return t.get();
    }

    @Override
    public <RP> PageResponse<RP> findOne(Specification<T> filter, Pageable pageable, Function<T, RP> transform) {
        Page<T> page = baseRepository.findAll(filter, pageable.previousOrFirst());
        List<RP> data = page.stream().map(transform::apply).collect(Collectors.toList());
        return PageResponse.of(page.getTotalElements(), data);
    }

    @Override
    public <RP> List<RP> filter(Specification<T> filter, Function<T, RP> transform) {
        List<T> list = baseRepository.findAll(filter);

        return list.stream().map(transform::apply).collect(Collectors.toList());
    }
}
