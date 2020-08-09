package com.itmuch.setvice.usercontentservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.api.usercenterapi.feignclient.UserFeignClient;
import com.itmuch.common.mybatisplus.user.entity.User;
import com.itmuch.setvice.usercontentservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/27
 * Time: 11:21 下午
 * Description: 提供网关调用用户相关的api
 */
@RestController
@Slf4j
public class UserContentController implements UserFeignClient {
    @Autowired
    private UserService userService;

    @Override
    public R<User> userInfoByid(Integer id) {
        return R.ok(userService.userInfoByid(id));
    }

    @GetMapping("/a/{id}")
    public User userInfoByid1(@PathVariable Integer id) {
        System.out.println("1");
        return userService.userInfoByid(id);
    }


}