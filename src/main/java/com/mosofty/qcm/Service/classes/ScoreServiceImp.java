package com.mosofty.qcm.Service.classes;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosofty.qcm.Service.interfaces.ScoreService;
import com.mosofty.qcm.model.Score;
import com.mosofty.qcm.repository.ScoreRepository;

@Service
public class ScoreServiceImp implements ScoreService {
	
	@Autowired
	ScoreRepository  scoreRepo;

	@Override
	public Score addScore(Score score) {
		if(score==null) {
			throw new IllegalArgumentException("Score cannot be null");
		}
		return scoreRepo.save(score);
	}

	@Override
	public Score getScoreById(Long id) {
		return scoreRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Score not found"));
	}

	@Override
	public List<Score> getAllScores() {
		return scoreRepo.findAll();
	}

	@Override
	public void deleteScore(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Score ID cannot be null");
		}
		if(!scoreRepo.existsById(id)) {
			throw new NoSuchElementException("Score not found");
		}
		scoreRepo.deleteById(id);
	}

	@Override
	public Score updateScore(Score score, Long id) {
		if(id==null) {
			throw new IllegalArgumentException("Score ID cannot be null");
		}
		if(!scoreRepo.existsById(id)) {
			throw new NoSuchElementException("Score not found");
		}
		Score s=scoreRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Score not found"));
		s.setQuiz(score.getQuiz());
		s.setScore(score.getScore());
		s.setUser(score.getUser());
		return scoreRepo.save(s);
	}

}
