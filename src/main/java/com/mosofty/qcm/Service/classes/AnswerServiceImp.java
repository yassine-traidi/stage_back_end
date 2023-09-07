package com.mosofty.qcm.Service.classes;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosofty.qcm.Service.interfaces.AnswerService;
import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;
import com.mosofty.qcm.repository.AnswerRepository;

@Service
public class AnswerServiceImp implements AnswerService {
	
	@Autowired
	AnswerRepository answerRepo;

	@Override
	public Answer saveAnswer(Answer answer) {
		if(answer==null) {
			throw new IllegalArgumentException("Answer cannot be null");
		}
		return answerRepo.save(answer);
	}

	@Override
	public Answer getAnswerById(Long id) {
		return answerRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Answer not found"));
	}

	@Override
	public List<Answer> getAllAnswers() {
		return answerRepo.findAll();
	}

	@Override
	public void deleteAnswer(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Answer ID cannot be null");
		}
		if(!answerRepo.existsById(id)) {
			throw new NoSuchElementException("Answer not found");
		}
		answerRepo.deleteById(id);
	}

	@Override
	public Answer updateAnswer(Answer answer, Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Answer ID cannot be null");
		}
		if(!answerRepo.existsById(id)) {
			throw new NoSuchElementException("Answer not found");
		}
		Answer a=answerRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Answer not found"));
		a.setContent(answer.getContent());
		a.setQuestion(answer.getQuestion());
		a.setValue(answer.getValue());
		//a.setQuiz(answer.getQuiz());
		return answerRepo.save(a);
	}
	
	@Override
	public List<Answer> getAllCorrectAnswersByQuestion(Question question){
		return answerRepo.findAllByQuestionAndValue(question,true);
	}

}
