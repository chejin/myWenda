package com.newcoder.myWenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
	@RequestMapping(path= {"/","/index"})
	@ResponseBody
	public String indext() {
		return "hello";
	}
	
	@RequestMapping(path= {"/profile/{groupId}/{userId}"})
	@ResponseBody
	public String profile(@PathVariable("userId") int userId,
						  @PathVariable("groupId") String groupId,
						  @RequestParam(value="type", defaultValue = "1",required = true) int type,
						  @RequestParam(value="key", defaultValue = "admin",required = false) String key) {
		return String.format("profile page of %s %d type:%d key:%s",groupId, userId,type, key);
	}
}
