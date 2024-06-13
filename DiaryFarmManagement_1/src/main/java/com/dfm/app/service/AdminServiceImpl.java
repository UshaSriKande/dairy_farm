package com.dfm.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfm.app.dao.UserRepo;
import com.dfm.app.model.User;




@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public void updateEmployee(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
		
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}
	
	

}
