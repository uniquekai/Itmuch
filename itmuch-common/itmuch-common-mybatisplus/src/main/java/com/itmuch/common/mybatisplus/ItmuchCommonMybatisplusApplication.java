package com.itmuch.common.mybatisplus;


import com.itmuch.common.mybatisplus.user.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;


@MapperScan("com.itmuch.common.mybatisplus")
public class ItmuchCommonMybatisplusApplication {

    @Autowired
    private UserMapper userMapper;

}
