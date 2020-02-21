package com.newcoder.myWenda.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.newcoder.myWenda.dao.LoginTicketDAO;
import com.newcoder.myWenda.dao.UserDAO;
import com.newcoder.myWenda.model.HostHolder;
import com.newcoder.myWenda.model.LoginTicket;
import com.newcoder.myWenda.model.User;

@Component
public class PassportInterceptor implements HandlerInterceptor{
	@Autowired
	LoginTicketDAO loginTicketDAO;
	
	@Autowired 
	UserDAO userDAO;
	
	@Autowired
	HostHolder hostHolder;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ticket = null;
		if(request.getCookies() != null) {
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("ticket")) {
					ticket = cookie.getValue();
					break;
				}
			}
		}
		
		if(ticket != null) {
			LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
			if( loginTicket == null || 
				loginTicket.getExpired().before(new Date()) ||
				loginTicket.getStatus() != 0) {
				return true;
			}
			
			User user = userDAO.selectById(loginTicket.getUserId());
			hostHolder.setUser(user);
			
		}
		
		return true;
	}
	
	//视图渲染之前执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null) {
			modelAndView.addObject("user", hostHolder.getUser());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		hostHolder.clear();
	}
	
}
