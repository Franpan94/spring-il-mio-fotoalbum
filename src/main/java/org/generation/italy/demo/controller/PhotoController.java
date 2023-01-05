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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photo")
public class PhotoController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired CategoryService categoryService;
	
	@GetMapping("/user")
	public String getPhotos(Model model) {
		
		List<Foto> photos = fotoService.findAll();
		model.addAttribute("photos", photos);
		
		return "Photos/PhotoIndex";
	}
	
	@GetMapping("/user/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Foto photo = fotoService.findById(id);
        model.addAttribute("photo", photo);
		
		return "Photos/PhotoShow";
	}
	
	@GetMapping("/admin/create")
	public String create(Model model) {
		
		Foto photo = new Foto();
		model.addAttribute("photo", photo);
		
		List<Category> categories = categoryService.findAllWithFoto();
		model.addAttribute("categories", categories);
		
		return "Photos/PhotoCreate";
	}
	
	@PostMapping("/admin/store")
	public String store(@Valid @ModelAttribute("photo") Foto photo,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
        if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/photo/admin/create";
		}
		
        try {
			
			fotoService.save(photo);
			
		}catch(Exception e) {
			
			String message = "Il nome deve essere unico";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/photo/admin/create";
		}
		
		return "redirect:/photo/user";
	}
	
	@GetMapping("/admin/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		Foto photo = fotoService.findById(id);
		model.addAttribute("photo", photo);
		
		List<Category> categories = categoryService.findAllWithFoto();
		model.addAttribute("categories", categories);
		
		return "Photos/PhotoEdit";
	}
	

	@PostMapping("/admin/update")
	public String update(@Valid Foto photo,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
       if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/photo/admin/edit/" + photo.getId();
		}
		
        try {
			
			fotoService.save(photo);
			
		}catch(Exception e) {
			
			String message = "Il nome deve essere unico";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/photo/admin/edit/" + photo.getId();
		}
		
		return "redirect:/photo/user";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") int id, 
			             RedirectAttributes redirectAttributes) {
		
		try {
			
		      fotoService.deleteById(id);
		      
		
		} catch(Exception e) {
			
			String message = "Operazione non permessa";
			redirectAttributes.addFlashAttribute("message", message);
		}
		
		return "redirect:/photo/user";
	}
	
	@GetMapping("/user/search")
	public String search(Model model, 
			             @RequestParam(name = "name", required = false) String query ) {
		
		List<Foto> photos = null;
		
		if(query == null) photos = fotoService.findAll();
		else photos = fotoService.findByTitleOrTag(query, query);
		
		model.addAttribute("photos", photos);
			
		
		return "Photos/PhotoSearch";
		
	}
}
