package com.mosofty.qcm.Service.classes;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosofty.qcm.Service.interfaces.CandidateResponseService;
import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.CandidateResponse;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;
import com.mosofty.qcm.model.User;
import com.mosofty.qcm.repository.CandidateResponseRepository;

@Service
public class CandidateResponseServiceImp implements CandidateResponseService{
	
	@Autowired
	CandidateResponseRepository candidateRepo;

	@Override
	public CandidateResponse saveCandidateResponse(CandidateResponse candidateResponse) {
		if(candidateResponse==null) {
			throw new IllegalArgumentException("Candidate Response cannot be null");
		}
		return candidateRepo.save(candidateResponse);
	}

	@Override
	public CandidateResponse getCandidateResponseById(Long id) {
		return candidateRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Candidate Response not found"));
	}

	@Override
	public List<CandidateResponse> getAllCandidateResponses() {
		return candidateRepo.findAll();
	}

	@Override
	public void deleteCandidateResponse(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Candidate Response ID cannot be null");
		}
		if(!candidateRepo.existsById(id)) {
			throw new NoSuchElementException("Candidate Response not found");
		}
		candidateRepo.deleteById(id);
	}

	@Override
	public CandidateResponse updateCandidateResponse(CandidateResponse candidateResponse, Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Candidate Response ID cannot be null");
		}
		if(!candidateRepo.existsById(id)) {
			throw new NoSuchElementException("Candidate Response not found");
		}
		CandidateResponse cr=candidateRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Candidate Response not found"));
		cr.setAnswers(candidateResponse.getAnswers());
		cr.setQuiz(candidateResponse.getQuiz());
		cr.setUser(candidateResponse.getUser());
		return candidateRepo.save(cr);
	}
	@Override
	public List<CandidateResponse> getAllResponsesByUserAndQuiz(User user,Quiz quiz){
		return candidateRepo.findAllByUserAndQuiz(user,quiz);
	}

}
