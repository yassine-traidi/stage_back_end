package com.mosofty.qcm.Service.interfaces;

import java.util.List;

import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;

public interface AnswerService {
	
	Answer saveAnswer(Answer answer);
	
	Answer getAnswerById(Long id);
	
	List<Answer> getAllAnswers();
	
	void deleteAnswer(Long id);
	
	Answer updateAnswer(Answer answer,Long id);
	
	List<Answer> getAllCorrectAnswersByQuestion(Question question);

}
