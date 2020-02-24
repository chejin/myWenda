package com.newcoder.myWenda.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.newcoder.myWenda.dao.LoginTicketDAO;
import com.newcoder.myWenda.dao.UserDAO;
import com.newcoder.myWenda.model.LoginTicket;
import com.newcoder.myWenda.model.User;
import com.newcoder.myWenda.util.WendaUtil;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private LoginTicketDAO loginTicketDAO;
	
	//注册
	public Map<String, String> register(String name, String password){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isEmpty(name)) {
			map.put("msg", "用户名不能为空");
			return map;
		}
		
		if(StringUtils.isEmpty(name)) {
			map.put("msg", "密码不能为空");
			return map;
		}
		User user = userDAO.selectByName(name);
		if(user != null) {
			map.put("msg", "用户名已存在");
			return map;
		}
		
		user = new User();
		user.setName(name);
		user.setSalt(UUID.randomUUID().toString().substring(0,5));
		user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",
				new Random().nextInt(1000)));
		user.setPassword(WendaUtil.MD5(password+user.getSalt()));
		userDAO.addUser(user);
		
		String ticket = addLoginTicket(user.getId());
		map.put("ticket",ticket);
		
		return map;
	}
	
	
	//登录
	public Map<String, String> login(String name, String password){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isEmpty(name)) {
			map.put("msg", "用户名不能为空");
			return map;
		}
		
		if(StringUtils.isEmpty(password)) {
			map.put("msg", "密码不能为空");
			return map;
		}
		User user = userDAO.selectByName(name);
		if(user == null) {
			map.put("msg", "用户名不存在");
			return map;
		}
		if(!WendaUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
			map.put("msg", "密码错误");
			return map;
		}
		
		String ticket = addLoginTicket(user.getId());
		map.put("ticket",ticket);
		
		return map;
	}
	
	//登出
	public void logout(String ticket) {
		loginTicketDAO.updateStatus(ticket, 1);
	}
	
	public User getUser(int id) {
		return userDAO.selectById(id);
	}
	
	public String addLoginTicket(int userId) {
		LoginTicket loginTicket  = new LoginTicket();
		loginTicket.setUserId(userId);
		Date date = new Date();
		date.setTime(1000*3600*2 + date.getTime());
		loginTicket.setExpired(date);
		loginTicket.setStatus(0);
		loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
		loginTicketDAO.addTicket(loginTicket);
		return loginTicket .getTicket();
		
	}
	
	
}
