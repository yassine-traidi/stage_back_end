package com.mosofty.qcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

	List<Answer> findAllByQuestionAndValue(Question question,boolean value);
}
