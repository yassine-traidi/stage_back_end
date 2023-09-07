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

import com.mosofty.qcm.Service.classes.AnswerServiceImp;
import com.mosofty.qcm.Service.classes.CandidateResponseServiceImp;
import com.mosofty.qcm.Service.classes.QuestionServiceImp;
import com.mosofty.qcm.Service.classes.QuizServiceImp;
import com.mosofty.qcm.Service.classes.ScoreServiceImp;
import com.mosofty.qcm.Service.classes.UserServiceImp;
import com.mosofty.qcm.model.Answer;
import com.mosofty.qcm.model.CandidateResponse;
import com.mosofty.qcm.model.Question;
import com.mosofty.qcm.model.Quiz;
import com.mosofty.qcm.model.Score;
import com.mosofty.qcm.model.User;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:53499"})
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImp userService;
	
	@Autowired
	CandidateResponseServiceImp candidateResponseService;
	
	@Autowired
	QuestionServiceImp questionService;
	
	@Autowired
	AnswerServiceImp answerService;
	
	@Autowired
	ScoreServiceImp scoreService;
	
	@Autowired
	QuizServiceImp quizService;
	
	/*private int calculateScoreForUser(User user, Question question) {
	    int totalScore = 0;
	    List<CandidateResponse> candidateResponses = candidateResponseService.getAllResponsesByUserAndQuestion(user, question);
	    List<Answer> correctAnswers = answerService.getAllCorrectAnswersByQuestion(question);

	    for (CandidateResponse candidateResponse : candidateResponses) {
	        List<Answer> candidateAnswers = candidateResponse.getAnswers();
	        for (Answer candidateAnswer : candidateAnswers) {
	            if (correctAnswers.contains(candidateAnswer)) {
	                totalScore++;
	            }
	        }
	    }

	    return totalScore;
	}



	
	//SAVE THE CANDIDATE'S SCORE
	/*@ResponseBody
	@PostMapping("/quizzes/{quizId}/users/{userId}/scores")
	public ResponseEntity<Score> addScoretoUser(@PathVariable(value="quizId")Long quizId,@PathVariable(value="userId")Long userId){
		User user=userService.getUserById(userId);
		Quiz quiz=quizService.getQuizByID(quizId);
		
		Score s=new Score();
		
		int calculatedScore = calculateScoreForUser(user, quiz);
		
		s.setQuiz(quiz);
		s.setUser(user);
		s.setScore(calculatedScore); 
		
		Score savedScore=scoreService.addScore(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedScore);
	}*/
	
	
	/*@ResponseBody
	@PostMapping("/{userId}/responses/{questionId}/{answerId}")
	public ResponseEntity<CandidateResponse> addCandidateResponseToUser(
	        @PathVariable(value="userId") Long userId,
	        @PathVariable(value="questionId") Long questionId,
	        @PathVariable(value="answerId") Long answerId) {
	    
	    User user = userService.getUserById(userId);
	    Question question = questionService.getQuestionById(questionId);
	    Answer answer = answerService.getAnswerById(answerId);
	    
	    // Create a new CandidateResponse object
	    CandidateResponse candidateResponse = new CandidateResponse();
	    
	    candidateResponse.setUser(user);
	    candidateResponse.setQuiz(question);
	    
	    // Create a list of answers and add the selected answer
	    List<Answer> selectedAnswers = new ArrayList<>();
	    selectedAnswers.add(answer);
	    candidateResponse.setAnswers(selectedAnswers);
	    
	    
	    // Save the candidate's response
	    CandidateResponse savedCandidateResponse = candidateResponseService.saveCandidateResponse(candidateResponse);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidateResponse);
	}*/
	
	//CRUD
	
	//CREATE USER
	@ResponseBody
	@PostMapping("/add")
	public ResponseEntity<User> add(@RequestBody User user) {
		
		User u= userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	//RETRIEVE USER
	@ResponseBody
	@GetMapping("get/{id}")
	public ResponseEntity<User> get(@PathVariable(value="id")Long id) {
		try {
			User u=userService.getUserById(id);
			return ResponseEntity.ok(u);
		}
		catch(NoSuchElementException e){
			return ResponseEntity.notFound().build();
		}
	}
	
	//RETRIEVE ALL USERS
	@ResponseBody
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll(){
		List<User> users= userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	//DELETE USER
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value="id")Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		}catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//UPDATE USER
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@RequestBody User user,@PathVariable(value="id")Long id) {
		try {
			User u=userService.updateUser(user, id);
			return ResponseEntity.ok(u);
			}catch(NoSuchElementException e) {
				return ResponseEntity.notFound().build();
			
		}
	}
	
	
	
}
