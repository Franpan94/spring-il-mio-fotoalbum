package org.generation.italy.demo.service;

import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public void save(User user) {
		
		userRepo.save(user);
	}
	
    public void delete(User user) {
		
    	userRepo.delete(user);
	}
    
    public void findAll() {
    	
    	userRepo.findAll();
    }
    
    public User findById(int id) {
    	
    	return userRepo.findById(id).get();
    }
}
