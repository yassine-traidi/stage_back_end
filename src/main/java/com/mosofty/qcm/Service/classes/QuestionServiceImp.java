package com.mosofty.qcm.Service.classes;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosofty.qcm.Service.interfaces.QuestionService;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.repository.QuestionRepository;

@Service
public class QuestionServiceImp implements QuestionService {
	
	@Autowired
	QuestionRepository questionRepo;

	@Override
	public Question saveQuestion(Question question) {
		if(question==null) {
			throw new IllegalArgumentException("Question cannot be null");
		}
		return questionRepo.save(question);
	}

	@Override
	public Question getQuestionById(Long id) {
		return questionRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Question ID not found"));
	}

	@Override
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}

	@Override
	public void deleteQuestion(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Question ID cannot be null");
		}
		if(!questionRepo.existsById(id)) {
			throw new NoSuchElementException("Question not found");
		}
		questionRepo.deleteById(id);
	}

	@Override
	public Question updateQuestion(Question question, Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Question ID cannot be null");
		}
		if(!questionRepo.existsById(id)) {
			throw new NoSuchElementException("Question not found");
		}
		Question q=questionRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Question not found"));
		q.setContent(question.getContent());
		q.setQuiz(question.getQuiz());
		q.setAnswers(question.getAnswers());
		q.setLink(question.getLink());
		return questionRepo.save(q);
	}

}
