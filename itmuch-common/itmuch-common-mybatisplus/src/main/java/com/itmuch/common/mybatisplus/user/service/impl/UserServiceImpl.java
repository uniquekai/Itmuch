package com.itmuch.common.mybatisplus.user.service.impl;

import com.itmuch.common.mybatisplus.user.entity.User;
import com.itmuch.common.mybatisplus.user.mapper.UserMapper;
import com.itmuch.common.mybatisplus.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分享 服务实现类
 * </p>
 *
 * @author uniquek
 * @since 2019-12-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
