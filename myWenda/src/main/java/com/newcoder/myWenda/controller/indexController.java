package com.newcoder.myWenda.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newcoder.myWenda.model.User;

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
	
	@RequestMapping(path= {"/ftl"})
	public String template(Model model) {
		model.addAttribute("value1","v11111");
		model.addAttribute("value3","v33333");
		List<String> colors = Arrays.asList(new String[]{"RED", "GREEN", "BLUE"});
        model.addAttribute("colors", colors);

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 4; ++i) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 4; ++i) {
            userList.add(new User("LEE"+i));
        }
        model.addAttribute("map", map);
        model.addAttribute("user", new User("LEE"));
        model.addAttribute("userList", userList);
		return "home";
	}
}
