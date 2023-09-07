package com.mosofty.qcm.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.mosofty.qcm.Service.classes.QuestionServiceImp;
import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:54068"})
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	QuestionServiceImp questionService;
	
	@Autowired
	AnswerServiceImp answerService;
	
	
	// GET ANSWERS OF QUESTION
	@ResponseBody
	@GetMapping("/{id}/getAnswers")
	public ResponseEntity<List<Answer>> getAnswersOfQuestion(@PathVariable(name="id") Long id){
		Question question=questionService.getQuestionById(id);
		return ResponseEntity.status(HttpStatus.OK).body(question.getAnswers());
	}
	
	//ADD ANSWER TO QUESTION
	
	@ResponseBody
	@PostMapping("/{id}/addAnswer")
	public ResponseEntity<Answer> addAnswerToQuestion(@RequestBody Answer answer,@PathVariable(value="id")Long id){
		Question question=questionService.getQuestionById(id);
		
		answer.setQuestion(question);
		
		question.getAnswers().add(answer);
		
		questionService.updateQuestion(question, id);
		
		//Answer savedAnswer=answerService.saveAnswer(answer);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(answer);
	}
	
	//CRUD
	
	//CREATE QUESTION
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<Question> add(@RequestBody Question question){
		Question q=questionService.saveQuestion(question);
		return ResponseEntity.status(HttpStatus.CREATED).body(q);
	}
	
	//RETRIEVE QUESTION
	@ResponseBody
	@GetMapping("/get/{id}")
	public ResponseEntity<Question> get(@PathVariable(value="id")Long id){
		try {
			Question q=questionService.getQuestionById(id);
			return ResponseEntity.ok(q);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//RETRIEVE ALL QUESTIONS
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAll")
	public ResponseEntity<List<Question>> getAll(){
		List<Question> questions =questionService.getAllQuestions();
		return ResponseEntity.ok(questions);
	}
	
	//DELETE QUESTION
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id")Long id){
		try {
			questionService.deleteQuestion(id);
			return ResponseEntity.noContent().build();
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//UPDATE QUESTION
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<Question> update(@RequestBody Question question,@PathVariable(value="id")Long id){
		try {
			Question q=questionService.updateQuestion(question, id);
			return ResponseEntity.ok(q);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
