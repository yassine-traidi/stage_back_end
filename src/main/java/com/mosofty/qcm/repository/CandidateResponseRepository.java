package com.mosofty.qcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mosofty.qcm.model.CandidateResponse;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;
import com.mosofty.qcm.model.User;

@Repository
public interface CandidateResponseRepository extends JpaRepository<CandidateResponse, Long> {

	List<CandidateResponse> findAllByUserAndQuiz(User user,Quiz quiz);
}
