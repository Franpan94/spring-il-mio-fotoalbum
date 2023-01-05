package org.generation.italy.demo.service;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.repo.FotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
    
	@Autowired
	private FotoRepo fotoRepo;
	
	public void save(Foto foto) {
		
		fotoRepo.save(foto);
	}
	
    public void delete(Foto foto) {
		
		fotoRepo.delete(foto);
	}
    
    public List<Foto> findAll(){
    	
    	return fotoRepo.findAll();
    }
   
    public List<Foto> findByVisible(){
    	
    	return fotoRepo.findByVisibleTrue();
    }
    
    public Foto findById(int id) {
    	
    	return fotoRepo.findById(id).get();
    }
    
    public void deleteById(int id) {
    	
    	fotoRepo.deleteById(id);
    }
    
    public List<Foto> findByTitleOrTag(String title, String tag) {
    	
    	return fotoRepo.findByTitleContainingIgnoreCaseOrTagContaining(title, tag);
    }
}
