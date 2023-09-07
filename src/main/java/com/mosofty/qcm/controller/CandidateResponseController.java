package com.mosofty.qcm.controller;

import java.util.ArrayList;
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

import com.mosofty.qcm.Service.classes.CandidateResponseServiceImp;
import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.CandidateResponse;

@RestController
@RequestMapping("candidateResponses")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:60835"})
public class CandidateResponseController {
	
	@Autowired
	CandidateResponseServiceImp candidateResponseService;
	
	//CRUD

	//CREATE CANDIDATE-RESPONSE
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<CandidateResponse> add(@RequestBody CandidateResponse candidateResponse){
		CandidateResponse cr=candidateResponseService.saveCandidateResponse(candidateResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(cr);
	}
	
	//RETRIEVE CANDIDATE-RESPONSE
	@ResponseBody
	@GetMapping("/get/{id}")
	public ResponseEntity<CandidateResponse> get(@PathVariable(value="id")Long id){
		try {
			CandidateResponse cr=candidateResponseService.getCandidateResponseById(id);
			return ResponseEntity.ok(cr);
		}catch(NoSuchElementException e){
			return ResponseEntity.notFound().build();
		}
	}
	
	//RETRIEVE ALL CANDIDATE-RESPONSES
	@ResponseBody
	@GetMapping("/getAll")
	public ResponseEntity<List<CandidateResponse>> getAll(){
		List<CandidateResponse> responses=candidateResponseService.getAllCandidateResponses();
		return ResponseEntity.ok(responses);
	}
	
	//DELETE CANDIDATE-RESPONSE
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id")Long id){
		try {
			candidateResponseService.deleteCandidateResponse(id);
			return ResponseEntity.noContent().build();
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//UPDATE CANDIDATE-RESPONSE
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<CandidateResponse> update(@RequestBody CandidateResponse candidateResponse,@PathVariable(value="id")Long id){
		try {
			CandidateResponse cr=candidateResponseService.updateCandidateResponse(candidateResponse, id);
			return ResponseEntity.ok(cr);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
