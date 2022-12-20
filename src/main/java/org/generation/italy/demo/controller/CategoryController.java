package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private FotoService fotoService;
    
	@GetMapping("/admin/create")
	public String create(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		List<Foto> photo = fotoService.findAll();
		model.addAttribute("photo", photo);
		
		return "IngredientCreate";
	}
}
