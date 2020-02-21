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
	
	public List<Question> getLatestqQuestions(int userId, int offset, int limit) {
		return questionDAO.selectLatestQuestions(userId, offset, limit);
	}
}
