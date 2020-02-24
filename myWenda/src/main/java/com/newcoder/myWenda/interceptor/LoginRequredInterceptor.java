package com.newcoder.myWenda.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.newcoder.myWenda.model.HostHolder;

@Component
public class LoginRequredInterceptor implements HandlerInterceptor{
	
	@Autowired
	HostHolder hostHolder;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(hostHolder.getUser() == null) {
			response.sendRedirect("/reglogin?next="+request.getRequestURI());
		}
		return true;
	}
	
	//视图渲染之前执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
