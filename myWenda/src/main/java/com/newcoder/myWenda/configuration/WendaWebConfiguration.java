package com.newcoder.myWenda.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.newcoder.myWenda.interceptor.LoginRequredInterceptor;
import com.newcoder.myWenda.interceptor.PassportInterceptor;

@Configuration
public class WendaWebConfiguration implements WebMvcConfigurer{
	
	@Autowired
	private PassportInterceptor passportInterceptor;
	
	@Autowired
	private LoginRequredInterceptor loginRequredInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] excludeStrings = new String[] {"/images/**","/scripts/**","/styles/**"};
		registry.addInterceptor(passportInterceptor).addPathPatterns("/**").excludePathPatterns(excludeStrings);
		registry.addInterceptor(loginRequredInterceptor).addPathPatterns("/user/**").excludePathPatterns(excludeStrings);
	}
	
	
//	 @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//       registry.addInterceptor(passportInterceptor).addPathPatterns("/**").excludePathPatterns("/static/**");
//       registry.addInterceptor(loginRequredInterceptor).addPathPatterns("/user/*").excludePathPatterns("/static/**");
//    }

	//增加静态资源路径
//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**")
//        .addResourceLocations("classpath:/META-INF/resources/")
//        .addResourceLocations("classpath:/resources/")
//        .addResourceLocations("classpath:/static/")
//        .addResourceLocations("classpath:/public/");
//		super.addResourceHandlers(registry);
//	}
	 
	
}
