package com.daoxuanson.shop.demo.controller;

import com.daoxuanson.shop.demo.constant.annotation.PermissionData;
import com.daoxuanson.shop.demo.entity.Role;
import com.daoxuanson.shop.demo.entity.User;
import com.daoxuanson.shop.demo.mapper.UserMapper;
import com.daoxuanson.shop.demo.model.Response.BaseResponse;
import com.daoxuanson.shop.demo.model.request.user.UserAuthRequest;
import com.daoxuanson.shop.demo.model.request.user.UserFilterRequest;
import com.daoxuanson.shop.demo.model.request.user.UserSaveRequest;
import com.daoxuanson.shop.demo.repository.specification.UserSpecification;
import com.daoxuanson.shop.demo.security.jwt.TokenProducer;
import com.daoxuanson.shop.demo.security.jwt.model.JwtPayload;
import com.daoxuanson.shop.demo.service.UserService;
import com.daoxuanson.shop.demo.utils.PasswordHasher;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@Data
public class UserController extends BaseController{
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenProducer tokenProducer;

    @PostMapping("/auth")
    public ResponseEntity<BaseResponse> auth(@RequestBody UserAuthRequest userAuthRequest){
        LOGGER.info(MessageFormat.format("request body: {0}" ,userAuthRequest.toString()));
        String password = PasswordHasher.hash(userAuthRequest.getPassword());
        userAuthRequest.setPassword(password);
        User user = userService.findOne(UserSpecification.authFilter(userAuthRequest));
        JwtPayload jwtPayload = creatPayload(user);

        return success(tokenProducer.token(jwtPayload));
    }

    @PostMapping("/user")
    @PermissionData
    public ResponseEntity<BaseResponse> save(@RequestBody UserSaveRequest userSaveRequest) {
        userService.save(userSaveRequest, userMapper::mapToEntity);

        return success();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable long id){
        userService.delete(id);
        return success();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable long id){
        return success(userService.findById(id, userMapper::mapToResponse));
    }

    @GetMapping("/users")
    public ResponseEntity<BaseResponse> findAll(@ModelAttribute UserFilterRequest filterRequest){
        PageRequest pageRequest = PageRequest.of(filterRequest.getIndex(), filterRequest.getSize(), Sort.by("id").descending());
        return success(userService.findOne(UserSpecification.filter(filterRequest), pageRequest, userMapper::mapToResponse));
    }

    private JwtPayload creatPayload(User user){
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setEmail(user.getEmail());
        jwtPayload.setUserName(user.getUserName());
        jwtPayload.setId(user.getId());
        String role = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(","));
        jwtPayload.setRole(role);

        return jwtPayload;
    }

}
