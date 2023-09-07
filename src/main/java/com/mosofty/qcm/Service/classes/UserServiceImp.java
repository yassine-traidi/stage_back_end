package com.mosofty.qcm.Service.classes;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosofty.qcm.Service.interfaces.UserService;
import com.mosofty.qcm.model.User;
import com.mosofty.qcm.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public User saveUser(User user) {
		if(user==null) {
			throw new IllegalArgumentException("User cannot be null");
		}
		return userRepo.save(user);
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepo.findById(id).orElseThrow(()-> new NoSuchElementException("user not found"));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User updateUser(User user, Long id) {
		if(id==null) {
			throw new IllegalArgumentException("User ID cannot be null");
		}
		if(!userRepo.existsById(id)) {
			throw new NoSuchElementException("User not found");
		}
		User u=userRepo.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
		u.setEmail(user.getEmail());
		u.setUsername(user.getUsername());
		//u.setResponses(user.getResponses());
		u.setPhone(user.getPhone());
		return userRepo.save(u);
	}

	@Override
	public void deleteUser(Long id) {
		if(id==null) {
			throw new IllegalArgumentException("User ID cannot be null");
		}
		if(!userRepo.existsById(id)) {
			throw new NoSuchElementException("User not found");
		}
		userRepo.deleteById(id);

	}


}
