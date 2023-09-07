package com.mosofty.qcm.Service.interfaces;

import java.util.List;

import com.mosofty.qcm.model.Question;

public interface QuestionService {
	
	Question saveQuestion(Question question);
	
	Question getQuestionById(Long id);
	
	List<Question> getAllQuestions();
	
	void deleteQuestion(Long id);
	
	Question updateQuestion(Question question,Long id);

}
