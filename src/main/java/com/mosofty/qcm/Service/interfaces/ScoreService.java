package com.mosofty.qcm.Service.interfaces;

import java.util.List;

import com.mosofty.qcm.model.Score;

public interface ScoreService {
	
	 Score addScore(Score score);
	 
	 Score getScoreById(Long id);
	 
	 List<Score> getAllScores();
	 
	 void deleteScore(Long id);
	 
	 Score updateScore(Score score,Long id);
	

}
