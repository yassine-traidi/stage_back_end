package com.mosofty.qcm.Service.interfaces;

import java.util.List;

import com.mosofty.qcm.model.User;

public interface UserService {
	
	User saveUser(User user);
	
	User getUserById(Long id);
	
	List<User> getAllUsers();
	
	User updateUser(User user,Long id);
	
	void deleteUser(Long id);

}
