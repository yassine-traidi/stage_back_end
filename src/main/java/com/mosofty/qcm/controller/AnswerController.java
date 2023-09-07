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

import com.mosofty.qcm.Service.classes.AnswerServiceImp;
import com.mosofty.qcm.model.Answer;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:54068"})
@RequestMapping("/answers")
public class AnswerController {
	
	@Autowired
	AnswerServiceImp answerService;
	
	//CRUD
	
	//CREATE ANSWER
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<Answer> add(@RequestBody Answer answer){
		Answer a=answerService.saveAnswer(answer);
		return ResponseEntity.status(HttpStatus.CREATED).body(a);
	}
	
	//RETRIEVE ASWER
	@ResponseBody
	@GetMapping("/get/{id}")
	public ResponseEntity<Answer> get(@PathVariable(value="id")Long id){
		try {
			Answer a=answerService.getAnswerById(id);
			return ResponseEntity.ok(a);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//RETRIEVE ALL ANSWERS
	@ResponseBody
	@GetMapping("/getAll")
	public ResponseEntity<List<Answer>> getAll(){
		List<Answer> answers=answerService.getAllAnswers();
		return ResponseEntity.ok(answers);
	}
	
	//DELETE ANSWER
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id")Long id){
		try {
			answerService.deleteAnswer(id);
			return ResponseEntity.noContent().build();
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//UPDATE ANSWER
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<Answer> update(@RequestBody Answer answer,@PathVariable(value="id")Long id){
		try {
			Answer a=answerService.updateAnswer(answer, id);
			return ResponseEntity.ok(a);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
