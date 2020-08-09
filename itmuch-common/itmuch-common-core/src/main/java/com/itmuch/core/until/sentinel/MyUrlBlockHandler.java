package com.itmuch.core.until.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: uniquek
 * @Date: 2020/2/15 19:15
 * @Description:sentinel错误页优化配置类
 */
@Component
public class MyUrlBlockHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        ErrorMsg msg = null;
        // e 为接收到的错误类。根据e和异常类型进行匹配从而进行对错误页信息进行优化
        if (e instanceof FlowException) {
            //限流异常
            msg = ErrorMsg.builder()
                    .status(100)
                    .msg("访问已被限流")
                    .build();
        }
        else if (e instanceof DegradeException) {
            //降级异常
            msg = ErrorMsg.builder()
                    .status(101)
                    .msg("访问已被降级")
                    .build();
        }
        else if (e instanceof ParamFlowException) {
            //参数热点规则异常
            msg = ErrorMsg.builder()
                    .status(102)
                    .msg("访问热点参数限流")
                    .build();
        }
        else if (e instanceof SystemBlockException) {
            //系统规则异常
            msg = ErrorMsg.builder()
                    .status(103)
                    .msg("系统规则（负载/。。。不满足要求）")
                    .build();
        }
        else if (e instanceof AuthorityException) {
            //授权异常
            msg = ErrorMsg.builder()
                    .status(104)
                    .msg("授权规则不通过")
                    .build();
        }
        //http状态码
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type","application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        new ObjectMapper()
                .writeValue(
                        httpServletResponse.getWriter()
                        ,msg
                );
    }
}
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ErrorMsg{
    private Integer status;
    private String msg;
}