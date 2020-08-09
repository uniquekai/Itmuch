package com.itmuch.setvice.usercontentservice.service;

import com.itmuch.common.mybatisplus.user.entity.User;
import com.itmuch.common.mybatisplus.user.mapper.UserMapper;
import com.itmuch.common.mybatisplus.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/28
 * Time: 6:37 下午
 * Description:用户的服务类。
 */
@Slf4j
@Service
public class UserService {
    final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User userInfoByid(Integer id) {
        User user = userMapper.selectById(id);
        log.info("查询的用户信息：{}",user);
        return user;
    }
}