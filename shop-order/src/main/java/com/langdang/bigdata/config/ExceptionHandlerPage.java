package com.langdang.bigdata.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常返回
 */
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {
    //BlockException 异常接口,包含Sentinel的五个异常
    //	FlowException 限流异常
    //	DegradeException 降级异常
    //	ParamFlowException 参数限流异常
    //	AuthorityException 授权异常
    //	SystemBlockException 系统负载异常
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ResponseData responseData = null;
        if (e instanceof FlowException){
            responseData = new ResponseData(-1,"接口被限流了");
        }else if(e instanceof DegradeException){
            responseData = new ResponseData(-2,"接口被降级了");
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(responseData));
    }
}

class ResponseData {
    private int code;
    private String message;

    public ResponseData() {
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
