package com.mosofty.qcm.Service.interfaces;

import java.util.List;

import com.mosofty.qcm.model.Quiz;

public interface QuizService {
	
	Quiz saveQuiz(Quiz quiz);
	
	Quiz getQuizByID(Long id);
	
	List<Quiz> getAllQuizzes();
	
	void deleteQuiz(Long id);
	
	Quiz updateQuiz(Quiz quiz,Long id);

}
