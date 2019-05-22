package com.newcoder.myWenda.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.newcoder.myWenda.model.User;

@Controller
public class IndexController {
	@RequestMapping(path= {"/","/index"})
	@ResponseBody
	public String indext(HttpSession httpSession) {
		return "hello"+httpSession.getAttribute("msg");
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
	
	@RequestMapping(path = {"/request"}, method = {RequestMethod.GET})
    @ResponseBody
    public String request(HttpServletResponse response,
                           HttpServletRequest request,
                           HttpSession httpSession,
                          @CookieValue("JSESSIONID") String sessionId
                          ) {
        StringBuilder sb = new StringBuilder();
        sb.append("COOKIEVALUE:" + sessionId+"<br>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                sb.append("Cookie:" + cookie.getName() + " value:" + cookie.getValue());
            }
        }
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        sb.append(request.getRequestURI() + "<br>");

        response.addHeader("nowcoderId", "hello");
        response.addCookie(new Cookie("username", "nowcoder"));

        return sb.toString();
    }
	
	@RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
    public RedirectView redirect(@PathVariable("code") int code,
                                 HttpSession httpSession) {
        httpSession.setAttribute("msg", "jump from redirect");
//        return "redirect:/";
        RedirectView red = new RedirectView("/", true);
        if (code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return  red;
    }
	
	@RequestMapping(path = {"/admin"}, method = {RequestMethod.GET})
    @ResponseBody
    public String admin(@RequestParam("key") String key) {
        if ("admin".equals(key)) {
            return "hello admin";
        }
        throw  new IllegalArgumentException("参数不对");
    }
	
	@ExceptionHandler()
    @ResponseBody
    public String error(Exception e) {
        return "error:" + e.getMessage();
    }
}
