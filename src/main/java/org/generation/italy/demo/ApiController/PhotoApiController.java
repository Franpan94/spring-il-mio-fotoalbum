package org.generation.italy.demo.ApiController;

import java.util.List;

import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foto")
@CrossOrigin("*")
public class PhotoApiController {

	@Autowired 
	private FotoService fotoService;
	
	@GetMapping("/all")
	public List<Foto> getPhotos(){
		
		List<Foto> photos = fotoService.findByVisible();
		
		return photos;
	}
	
	@GetMapping("/search/{query}")
	public List<Foto> searchPhotos(@PathVariable("query") String query ) {
		
		List<Foto> photos = null;
		
		if(query == null) photos = fotoService.findByVisible();
		else photos = fotoService.findByTitleOrTag(query, query);	
		
		return photos;
		
	}
}
