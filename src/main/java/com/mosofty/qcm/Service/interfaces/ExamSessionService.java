package com.mosofty.qcm.Service.interfaces;

import java.util.List;

import com.mosofty.qcm.model.ExamSession;

public interface ExamSessionService {
	
	ExamSession saveExamSession(ExamSession examSession);
	
	ExamSession getExamSessionById(Long id);
	
	List<ExamSession> getAllExamSessions();
	
	void deleteExamSession(Long id);
	
	ExamSession updateExamSession(ExamSession examSession,Long id);

}
