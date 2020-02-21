package com.newcoder.myWenda.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.newcoder.myWenda.interceptor.LoginRequredInterceptor;
import com.newcoder.myWenda.interceptor.PassportInterceptor;

@Component
public class WendaWebConfiguration extends WebMvcConfigurationSupport{
	
	@Autowired
	PassportInterceptor passportInterceptor;
	
	@Autowired
	LoginRequredInterceptor loginRequredInterceptor;
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(passportInterceptor);
		registry.addInterceptor(loginRequredInterceptor).addPathPatterns("/user/*");
		super.addInterceptors(registry);
	}
	
	
}
