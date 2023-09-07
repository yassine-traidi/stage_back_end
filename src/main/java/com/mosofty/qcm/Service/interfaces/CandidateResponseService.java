package com.mosofty.qcm.Service.interfaces;

import java.util.List;

import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.CandidateResponse;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;
import com.mosofty.qcm.model.User;

public interface CandidateResponseService {
	
	CandidateResponse saveCandidateResponse(CandidateResponse candidateResponse);
	
	CandidateResponse getCandidateResponseById(Long id);
	
	List<CandidateResponse> getAllCandidateResponses();
	
	void deleteCandidateResponse(Long id);
	
	CandidateResponse updateCandidateResponse(CandidateResponse candidateResponse,Long id);
	
	List<CandidateResponse> getAllResponsesByUserAndQuiz(User user,Quiz quiz);

}
