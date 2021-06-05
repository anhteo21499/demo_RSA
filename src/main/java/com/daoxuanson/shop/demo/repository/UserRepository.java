package com.daoxuanson.shop.demo.repository;

import com.daoxuanson.shop.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends BaseRepository<User, Long> {
}
