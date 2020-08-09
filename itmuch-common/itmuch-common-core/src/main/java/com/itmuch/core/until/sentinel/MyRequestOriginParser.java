package com.itmuch.core.until.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: uniquek
 * @Date: 2020/2/16 18:47
 * @Description:实现sentinel区分来源
 */
//@Component
public class MyRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        //从请求参数中获取名为origin的参数，这个返回值就是来源
        //如果获取不到origin参数，那么就抛异常
        //实际项目中建议把来源放入到header中，从header中读取，是url更美观
        String origin = httpServletRequest.getParameter("origin");
        if (StringUtils.isBlank(origin)){
            throw new IllegalArgumentException("origin must be specified");
        }
        return origin;
    }
}
