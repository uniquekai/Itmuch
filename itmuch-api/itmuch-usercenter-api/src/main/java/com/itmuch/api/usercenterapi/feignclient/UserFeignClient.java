package com.itmuch.api.usercenterapi.feignclient;

import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.common.mybatisplus.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-center",fallbackFactory = Demo.class)
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    R<User> userInfoByid(@PathVariable Integer id);
}
