package com.mosofty.qcm.Service.classes;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosofty.qcm.Service.interfaces.ExamSessionService;
import com.mosofty.qcm.model.ExamSession;
import com.mosofty.qcm.repository.ExamSessionRepository;

@Service
public class ExamSessionServiceImp implements ExamSessionService {
	
	@Autowired
	ExamSessionRepository examRepo;

	@Override
	public ExamSession saveExamSession(ExamSession examSession) {
		if(examSession==null) {
			throw new IllegalArgumentException("Exam Session cannot be null");
		}
		return  examRepo.save(examSession);
	}

	@Override
	public ExamSession getExamSessionById(Long id) {
		return examRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Exam Session not found"));
	}

	@Override
	public List<ExamSession> getAllExamSessions() {
		return examRepo.findAll();
	}

	@Override
	public void deleteExamSession(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Exam Session ID cannot be null");
		}
		if(!examRepo.existsById(id)) {
			throw new NoSuchElementException("Exam Session not found");
		}
		examRepo.deleteById(id);
	}

	@Override
	public ExamSession updateExamSession(ExamSession examSession, Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Exam Session ID cannot be null");
		}
		if(!examRepo.existsById(id)) {
			throw new NoSuchElementException("Exam Session not found");
		}
		ExamSession es=examRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Exam Session not found"));
		es.setUser(examSession.getUser());
		es.setQuiz(examSession.getQuiz());
		es.setStartTime(examSession.getStartTime());
		es.setEndTime(examSession.getEndTime());
		return examRepo.save(es);
	}

}
