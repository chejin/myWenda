package com.nowcoder.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowcoder.model.HostHolder;
import com.nowcoder.model.Question;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.WendaUtil;


@Controller
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	//未登录的匿名用户ID
	public static int ANONYMOUS_USERID = 3;
	
	@Autowired
    QuestionService questionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HostHolder hostholder;
	
	@RequestMapping(value="/question/add",method = {RequestMethod.POST})
	@ResponseBody
	public String addQuestion(@RequestParam("title") String title,@RequestParam("content") String content) {
		try {
			Question question = new Question();
			question.setTitle(title);
			question.setContent(content);
			question.setCreatedDate(new Date());
			question.setCommentCount(0);
			if(hostholder.getUser()==null) {
//				question.setUserId(ANONYMOUS_USERID);
				return WendaUtil.getJSONString(999);
			}else {
				question.setUserId(hostholder.getUser().getId());
			}
			if(questionService.addQuestion(question) > 0) {
				return WendaUtil.getJSONString(0);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return WendaUtil.getJSONString(1,"失败");
	}
	
	@RequestMapping(value = "/question/{qid}")
	public String questionDetail(Model model, @PathVariable("qid") int qid) {
		Question question = questionService.selectById(qid);
		model.addAttribute("question",question);
		model.addAttribute("user",userService.getUser(question.getUserId()));
		return "detail";
	}
}
