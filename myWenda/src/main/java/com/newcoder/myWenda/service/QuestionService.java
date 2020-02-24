package com.newcoder.myWenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newcoder.myWenda.dao.QuestionDAO;
import com.newcoder.myWenda.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDAO questionDAO;
	
	public int addQuestion(Question question) {
		//敏感词过滤
		
		return questionDAO.addQuestion(question) > 0 ? question.getId() : 0;
	}
	
	public List<Question> getLatestqQuestions(int userId, int offset, int limit) {
		return questionDAO.selectLatestQuestions(userId, offset, limit);
	}
}
