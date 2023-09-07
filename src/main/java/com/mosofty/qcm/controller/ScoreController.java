package com.mosofty.qcm.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mosofty.qcm.Service.classes.ScoreServiceImp;
import com.mosofty.qcm.model.Score;

@RestController
@RequestMapping("/scores")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:53499"})
public class ScoreController {
	
	@Autowired
	ScoreServiceImp scoreService;
	
	//CRUD
	
	//CREATE SCORE
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<Score> add(@RequestBody Score score){
		Score s=scoreService.addScore(score);
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
	}
	
	//RETRIEVE SCORE
	@ResponseBody
	@GetMapping("/get/{id}")
	public ResponseEntity<Score> get(@PathVariable(value="id")Long id){
		try {
			Score s=scoreService.getScoreById(id);
			return ResponseEntity.ok(s);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//RETREIVE ALL SCORES
	@ResponseBody
	@GetMapping("/getAll")
	public ResponseEntity<List<Score>> getAll(){
		List<Score> scores=scoreService.getAllScores();
		return ResponseEntity.ok(scores);
	}
	
	
	//DELETE SCORE
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id")Long id){
		try {
			scoreService.deleteScore(id);
			return ResponseEntity.noContent().build();
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//UPDATE SCORE
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<Score> update(@RequestBody Score score,@PathVariable(value="id")Long id){
		try {
			Score s=scoreService.updateScore(score, id);
			return ResponseEntity.ok(s);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
