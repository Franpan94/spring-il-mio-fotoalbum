package org.generation.italy.demo.ApiController;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
@CrossOrigin("*")
public class CategoryApiController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	public List<Category> getCategories(){
		
		List<Category> categories = categoryService.findAll();
		
		return categories;
	}
	
	@GetMapping("/photo/{id}")
	public List<Category> getCategoryPhotoById(@PathVariable("id") int id){
		
		Foto photo = fotoService.findById(id);
		
		return photo.getCategories();
	}
}
