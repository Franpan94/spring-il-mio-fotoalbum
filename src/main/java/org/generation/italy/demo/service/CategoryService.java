package org.generation.italy.demo.service;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.repo.CategoryRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public void save(Category category) {
		
		categoryRepo.save(category);
	}
	
    public void delete(Category category) {
		
		categoryRepo.delete(category);
	}
    
    public List<Category> findAll(){
    	
    	return categoryRepo.findAll();
    }
    @Transactional
    public List<Category> findAllWithFoto(){
    	
    	List<Category> categories = categoryRepo.findAll();
    	for(Category c : categories) 
    		Hibernate.initialize(c.getFotos());
    	
    	return categories;
    }
    
    public void deleteById(int id) {
    	
    	categoryRepo.deleteById(id);
    }
    
    public Category findById(int id) {
    	
    	return categoryRepo.findById(id).get();
    }
}
