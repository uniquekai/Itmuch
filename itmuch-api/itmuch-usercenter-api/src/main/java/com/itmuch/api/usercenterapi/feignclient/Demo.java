package com.itmuch.api.usercenterapi.feignclient;

import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.common.mybatisplus.user.entity.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: uniquek
 * @Date: 2020/2/13 20:40
 * @Description:当feign被限流或者降级时自定义的处理类
 */
@Component
@Slf4j
public class Demo implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public R<User> userInfoByid(Integer id) {
                log.warn("远程调用被限流/降级了 ：{}",throwable);
                return R.ok(new User().setWxNickname("被限流或者降级"));
            }
        };
    }
}
