package com.newcoder.myWenda.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newcoder.myWenda.model.Question;
import com.newcoder.myWenda.model.ViewObjiect;
import com.newcoder.myWenda.service.QuestionService;
import com.newcoder.myWenda.service.UserService;


@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserService UserService;
	
	@Autowired
	QuestionService QuestionService;

	@RequestMapping(path= {"/","/index"})
	public String indext(Model model) {
		model.addAttribute("vos", getQuestions(0,0,10));
		return "index";
	}
	
	@RequestMapping(path= {"/user/{userId}"},method = {RequestMethod.GET})
	public String userIndext(Model model,@PathVariable("userId") int userId) {
		model.addAttribute("vos", getQuestions(userId,0,10));
		return "index";
	}
	
	private List<ViewObjiect> getQuestions(int userId, int offset, int limit){
		List<Question> questionsList = QuestionService.getLatestqQuestions(userId, offset, limit);
		List<ViewObjiect> vos = new ArrayList<ViewObjiect>();
		for(Question question:questionsList){
			ViewObjiect vo = new ViewObjiect();
			vo.set("question", question);
			vo.set("user", UserService.getUser(question.getUserId()));
			vos.add(vo);
//			System.out.println("=================================="+UserService.getUser(question.getUserId()));
		}
		return vos;
	}
}
