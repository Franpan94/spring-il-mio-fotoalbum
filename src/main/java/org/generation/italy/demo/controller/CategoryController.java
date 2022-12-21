package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@GetMapping("/user")
	public String getCategories(Model model) {
		
		List<Category> categories = categoryService.findAllWithFoto();
		model.addAttribute("categories", categories);
		
		return "Categories";
	}
    
	@GetMapping("/admin/create")
	public String create(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		List<Foto> photos = fotoService.findAll();
		model.addAttribute("photos", photos);
		
		return "CategoryCreate";
	}
	
	@PostMapping("/admin/store")
	public String store(@Valid Category category,
				BindingResult bindingResult, RedirectAttributes redirectAttributes) {
			
			if(bindingResult.hasErrors()) {
				
				redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
				return "redirect:/category/admin/create";
			}
			
			try {
				
				List<Foto> categoryPhotos = category.getFotos();
				for(Foto photo : categoryPhotos) {
					
					photo.getCategories().add(category);
				}
				
				categoryService.save(category);
				
			}catch (Exception e) {
				
				String message = "Il nome deve essere unico";
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:/ingredient/admin/create";
			}
			return "redirect:/category/user";
	}
	
	@GetMapping("/admin/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		List<Foto> photos = fotoService.findAll();
		model.addAttribute("photos", photos);
		
		
		Category category = categoryService.findById(id);
		model.addAttribute("category", category);
		
		return "CategoryEdit";
	}
	
	@PostMapping("/admin/update/{id}")
	public String update(@PathVariable("id") int id, @Valid Category category,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/category/admin/edit/" + id;
		}
		
		try {
			
			Category oldCategory = categoryService.findById(id);
			for(Foto photo : oldCategory.getFotos()) {
				photo.getCategories().remove(oldCategory);
			}
			
		    List<Foto> photoCategories = category.getFotos();
			for(Foto f : photoCategories) {
				f.addCategories(category);
			}
			categoryService.save(category);
		
		}catch (Exception e) {

			String message = "Il nome deve essere unico";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/category/admin/edit/" + id;
		}
		
		return "redirect:/category/user";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") int id,
			RedirectAttributes redirectAttributes) {
		
        try {
			Category category = categoryService.findById(id);
			
			for(Foto c : category.getFotos()) {
				c.getCategories().remove(category);
			}
			categoryService.deleteById(id);
			
		} catch(Exception e) {
			
			String message = "Operazione non permessa";
			redirectAttributes.addFlashAttribute("message", message);
		}
		
		return "redirect:/category/user";
	}
}
