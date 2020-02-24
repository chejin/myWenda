package com.newcoder.myWenda.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newcoder.myWenda.model.HostHolder;
import com.newcoder.myWenda.model.Question;
import com.newcoder.myWenda.service.QuestionService;
import com.newcoder.myWenda.util.WendaUtil;

@Controller
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	public static int ANONYMOUS_USERID = 3;
	
	@Autowired
    QuestionService questionService;
	
	@Autowired
	HostHolder hostholder;
	
	@RequestMapping(value="",method = {RequestMethod.POST})
	@ResponseBody
	public String addQuestion(@RequestParam("title") String title,@RequestParam("content") String content) {
		try {
			Question question = new Question();
			question.setTitle(title);
			question.setContent(content);
			question.setCreatedDate(new Date());
			question.setCommentCount(0);
			if(hostholder.getUser()==null) {
				question.setUserId(ANONYMOUS_USERID);
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
}
