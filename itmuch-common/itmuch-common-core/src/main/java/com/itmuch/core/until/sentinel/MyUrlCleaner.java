package com.itmuch.core.until.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Auther: uniquek
 * @Date: 2020/2/16 19:08
 * @Description:让sentinel支持RESTful URl接口
 */
@Component
@Slf4j
public class MyUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String s) {
        //让shares/1 与 shares/2的返回值相同
        String[] split = s.split("/");

        return Arrays.stream(split)
                .map(string -> {
                    if (NumberUtils.isNumber(string)){
                        return "{number}";
                    }
                    return string;
                })
                .reduce((a,b) -> a + "/" +b)
                .orElse("");

    }
}
