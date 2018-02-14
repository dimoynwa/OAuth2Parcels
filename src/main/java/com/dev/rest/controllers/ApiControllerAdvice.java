package com.dev.rest.controllers;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.dev.rest.model.RequestResult;

@ControllerAdvice("com.dev.rest.controllers")
public class ApiControllerAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public Object beforeBodyWrite(Object arg0, MethodParameter arg1, MediaType arg2,
			Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
		if (arg0 instanceof RequestResult<?>) {
			arg5.setStatusCode(HttpStatus.OK);
			return arg0;

		} else {
			RequestResult<Object> result = new RequestResult<>(true);
			result.setPayload(arg0);
			return result;
		}
	}

	@Override
	public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
		return true;
	}

	
	
}
