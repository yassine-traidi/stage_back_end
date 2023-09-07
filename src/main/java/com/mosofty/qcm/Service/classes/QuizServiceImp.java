package com.mosofty.qcm.Service.classes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosofty.qcm.Service.interfaces.QuizService;
import com.mosofty.qcm.model.Quiz;
import com.mosofty.qcm.repository.QuizRepository;

import jakarta.transaction.Transactional;

@Service
public class QuizServiceImp implements QuizService {
	
	@Autowired
	QuizRepository quizRepo;

	@Override
	public Quiz saveQuiz(Quiz quiz) {
		if(quiz==null)
		{
			throw new IllegalArgumentException("Quiz cannot be null");
		}
		return quizRepo.save(quiz);
	}

	@Override
	public Quiz getQuizByID(Long id) {
		return quizRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Quiz ID not found"));
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		return quizRepo.findAll();
	}

	@Override
	public void deleteQuiz(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Quiz ID cannot be null");
		}
		if(!quizRepo.existsById(id))
		{
			throw new NoSuchElementException("Quiz not found");
		}
		quizRepo.deleteById(id);
	}

	@Transactional
	@Override
	public Quiz updateQuiz(Quiz updatedQuiz, Long id) {
	    if (id == null) {
	        throw new IllegalArgumentException("Quiz ID cannot be null");
	    }
	    
	    Optional<Quiz> optionalQuiz = quizRepo.findById(id);
	    if (!optionalQuiz.isPresent()) {
	        throw new NoSuchElementException("Quiz not found");
	    }
	    
	    Quiz existingQuiz = optionalQuiz.get();
	    
	    // Update properties of the existing quiz
	    existingQuiz.setDescription(updatedQuiz.getDescription());

	    // Update the questions relationship
	    existingQuiz.getQuestions().clear(); // Clear existing questions
	    existingQuiz.getQuestions().addAll(updatedQuiz.getQuestions()); // Add updated questions
	    
	    return quizRepo.save(existingQuiz);
	}

	
	/*public List<Question> listQuestionsOfQuiz(Quiz quiz) {
		return quiz.getQuestions();
	}*/

}
