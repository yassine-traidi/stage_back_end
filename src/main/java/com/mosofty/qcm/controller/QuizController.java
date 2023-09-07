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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mosofty.qcm.Service.classes.QuestionServiceImp;
import com.mosofty.qcm.Service.classes.QuizServiceImp;
import com.mosofty.qcm.Service.interfaces.AnswerService;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;


@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:54068"})
@RequestMapping("quizzes")
public class QuizController {
	
	@Autowired
	QuizServiceImp quizService;
	
	@Autowired
	QuestionServiceImp questionService;
	
	@Autowired
	AnswerService answerService;
	
	//ADD QUESTION TO QUIZ
	@ResponseBody
	@PostMapping("/{quizId}/addQuestion")
	public ResponseEntity<Question> addQuestionToQuiz(@RequestBody Question question, @PathVariable(name="quizId") Long id) {
	    // Fetch the quiz from the database
	    Quiz quiz = quizService.getQuizByID(id);

	    // Associate the question with the quiz
	    question.setQuiz(quiz);

	    // Add the question to the quiz's list of questions
	    quiz.getQuestions().add(question);

	    // Update the quiz (assuming this method updates the quiz in the database)
	    quizService.updateQuiz(quiz, id);
	    

	    // Return the saved question
	    return ResponseEntity.status(HttpStatus.OK).body(question);
	}





	
	//LIST QUESTIONS OF QUIZ
	@ResponseBody
	@GetMapping("/{id}/getQuestions")
	public ResponseEntity<List<Question>> listQuestionsOfQuiz(@PathVariable(name="id") Long id){
		Quiz quiz=quizService.getQuizByID(id);
		return ResponseEntity.status(HttpStatus.OK).body(quiz.getQuestions());
	}
	
	
	//CRUD
	
	//CREATE QUIZ
	@ResponseBody
	@PostMapping("/add") 
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
		Quiz q=quizService.saveQuiz(quiz);
		return ResponseEntity.status(HttpStatus.CREATED).body(q);
	}
	
	//RETRIEVE QUIZ
	@ResponseBody
	@GetMapping("/get/{id}")
	public ResponseEntity<Quiz> get(@PathVariable(value="id")Long id){
		try {
			Quiz q=quizService.getQuizByID(id);
			return ResponseEntity.ok(q);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
		
	//RETRIEVE ALL QUIZZES
	@ResponseBody
	@GetMapping("/getAll")
	public ResponseEntity <List<Quiz>> getAll(){
		List<Quiz> quizzes=quizService.getAllQuizzes();
		return ResponseEntity.ok(quizzes);
	}
	
	//DELETE QUIZ
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id")Long id){
		try {
			quizService.deleteQuiz(id);
			return ResponseEntity.noContent().build();
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	//UPDATE QUIZ
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz,@PathVariable(value="id")Long id){
		try {
			Quiz q=quizService.updateQuiz(quiz, id);
			return ResponseEntity.ok(q);
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
		
}


