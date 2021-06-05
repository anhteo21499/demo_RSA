package com.daoxuanson.shop.demo.service.impl;

import com.daoxuanson.shop.demo.entity.User;
import com.daoxuanson.shop.demo.repository.UserRepository;
import com.daoxuanson.shop.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }
}
