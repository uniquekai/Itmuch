package com.itmuch.core.config.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: uniquek
 * @Date: 2020/2/12 22:11
 * @Description:
 */
@Slf4j
public class BlockHandlerClass {

    /**
     * 功能描述:处理限流或降级
     * @param: [a, e]
     * @return: java.lang.String
     * @auther: uniquek
     * @date: 2020/2/12 10:13 下午
     */
    public static String block(String a, BlockException e){
        //如果被保护的资源被限流或者降级了，就会抛BlockException异常
        log.warn("被保护的资源被限流或者降级 {}",e.toString());
//            e.printStackTrace();
        return "被保护的资源被限流或者降级";
    }
}
