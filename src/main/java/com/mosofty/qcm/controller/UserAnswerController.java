package com.mosofty.qcm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mosofty.qcm.Service.interfaces.AnswerService;
import com.mosofty.qcm.Service.interfaces.UserService;
import com.mosofty.qcm.model.User;

@RestController
@RequestMapping("User/Answers")
public class UserAnswerController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnswerService answerService;
	
	//save user with answers
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUserWithAnswers(@RequestBody User user){
		User u=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	//get all users
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll(){
		List<User> users=userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	//get user by ID
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id")long id){
		User user=userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	

}
