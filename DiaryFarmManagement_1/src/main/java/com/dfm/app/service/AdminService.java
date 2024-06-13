package com.dfm.app.service;

import java.util.List;

import com.dfm.app.model.User;



public interface AdminService {

	List<User> findAllUsers();

	void updateEmployee(User user);

	void deleteEmployee(Long id);

	
	

}
