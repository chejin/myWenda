package com.newcoder.myWenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyWendaApplication {
//	extends WebMvcConfigurationSupport
	
	public static void main(String[] args) {
		SpringApplication.run(MyWendaApplication.class, args);
	}
	
//	 配置静态资源文件路径
//	 @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
//        super.addResourceHandlers(registry);
//    }
}
