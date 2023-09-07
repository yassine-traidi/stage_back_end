package com.mosofty.qcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mosofty.qcm.model.ExamSession;

@Repository
public interface ExamSessionRepository extends JpaRepository<ExamSession, Long>{

}
