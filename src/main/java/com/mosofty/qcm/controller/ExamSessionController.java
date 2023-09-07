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

import com.mosofty.qcm.Service.classes.ExamSessionServiceImp;
import com.mosofty.qcm.model.ExamSession;

@RestController
@RequestMapping("/examSessions")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:54068"})
public class ExamSessionController {

	@Autowired
	ExamSessionServiceImp examSessionService;
	
	
	//CRUD
	
	//CREATE EXAM SESSION
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<ExamSession> add(@RequestBody ExamSession examSession){
		ExamSession es=examSessionService.saveExamSession(examSession);
		return ResponseEntity.status(HttpStatus.CREATED).body(es);
	}
	
	//RERIEVE EXAM SESSION
	@ResponseBody
	@GetMapping("/get/{id}")
	public ResponseEntity<ExamSession> get(@PathVariable(value="id")Long id){
		try {
			ExamSession es=examSessionService.getExamSessionById(id);
			return ResponseEntity.ok(es);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//RETRIEVE ALL EXAM SESSIONS
	@ResponseBody
	@GetMapping("/getAll")
	public ResponseEntity<List<ExamSession>> getAll(){
		List<ExamSession> examSessions=examSessionService.getAllExamSessions();
		return ResponseEntity.ok(examSessions);
	}
	
	//DELETE EXAM SESSION
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id")Long id){
		try {
			examSessionService.deleteExamSession(id);
			return ResponseEntity.noContent().build();
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//UPDATE EXAM SESSION
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<ExamSession> update(@RequestBody ExamSession examSession,@PathVariable(value="id")Long id){
		try {
			ExamSession es=examSessionService.updateExamSession(examSession, id);
			return ResponseEntity.ok(es);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
