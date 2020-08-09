package com.itmuch.core.config.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.cloud.alibaba.sentinel.rest.SentinelClientHttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @Auther: uniquek
 * @Date: 2020/2/13 00:17
 * @Description:
 */
public class ExceptionUtil {
    public static SentinelClientHttpResponse handleException(HttpRequest request,
                                                             byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        System.err.println("Oops: " + ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("bbbb");
    }

    public static SentinelClientHttpResponse fallback(HttpRequest request,
                                                      byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        System.err.println("fallback: " + ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("aaa");
    }

}
