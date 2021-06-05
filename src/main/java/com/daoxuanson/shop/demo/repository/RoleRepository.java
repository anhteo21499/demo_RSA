package com.daoxuanson.shop.demo.repository;

import com.daoxuanson.shop.demo.entity.Role;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role, Long> {
    List<Role> findAllByIdIn(List<Long> ids);
}
