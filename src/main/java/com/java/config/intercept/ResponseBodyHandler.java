package com.java.config.intercept;

import com.alibaba.fastjson.JSON;
import com.java.bean.base.BaseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截Controller方法默认返回参数，统一处理返回值/响应体
 *
 * @author yupan@yijiupi.cn
 * @date 2019/3/5 10:45
 */
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (null == body) {
            return BaseResult.successResult();

        } else if (body instanceof String) {
            return JSON.toJSONString(new BaseResult<>(body));

        } else if (body instanceof BaseResult) {
            return body;

        } else {
            return new BaseResult<>(body);
        }
    }
}
