package com.daoxuanson.shop.demo.mapper;

import com.daoxuanson.shop.demo.entity.Role;
import com.daoxuanson.shop.demo.entity.User;
import com.daoxuanson.shop.demo.model.Response.user.UserResponse;
import com.daoxuanson.shop.demo.model.request.user.UserSaveRequest;
import com.daoxuanson.shop.demo.repository.RoleRepository;
import com.daoxuanson.shop.demo.utils.BeanUtils;
import com.daoxuanson.shop.demo.validate.UserValidate;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@Data
public class UserMapper {

    private final RoleRepository roleRepository;

    public User mapToEntity(UserSaveRequest userSaveRequest){
        UserValidate.validate(userSaveRequest);
        User user = new User();
        BeanUtils.refine(userSaveRequest, user, BeanUtils::copyNonNull);
        List<Role> roles = roleRepository.findAllByIdIn(userSaveRequest.getIds());
        user.setRoles(new HashSet<>(roles));

        return user;
    }

    public UserResponse mapToResponse(User user){
        UserResponse userResponse = new UserResponse();
        BeanUtils.refine(user, userResponse, BeanUtils::copyNonNull);

        return userResponse;
    }
}
