package com.mosofty.qcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mosofty.qcm.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
