package org.generation.italy.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.FotoService;
import org.generation.italy.demo.service.RoleService;
import org.generation.italy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category c1 = new Category("Natale");
		Category c2 = new Category("2023");
		Category c3 = new Category("Estate");
		Category c4 = new Category("Mare");
		Category c5 = new Category("Compleanno");
		
		List<Category> categories1 = new ArrayList<>();
		categories1.add(c1);
		categories1.add(c2);
		
		List<Category> categories2 = new ArrayList<>();
		categories2.add(c3);
		categories2.add(c4);
		
		List<Category> categories3 = new ArrayList<>();
		categories2.add(c5);
		
		categoryService.save(c1);
		categoryService.save(c2);
		categoryService.save(c3);
		categoryService.save(c4);
		categoryService.save(c5);
		
		String url1 = "https://img.freepik.com/free-vector/gradient-christmas-tinsel-background_52683-76117.jpg";
		String url2 = "https://cdn.sanity.io/images/24oxpx4s/prod/65970e463d9a8c80a4cbfbcc36a2a8df52534f66-3200x1800.jpg?w=1600&h=900&fit=crop";
		String url3 = "https://www.montagnaestate.it/wp-content/uploads/DSC_0958-scaled.jpg";
		String url4 = "https://cdn.shopify.com/s/files/1/0876/3834/products/happybirthday2_1024x1024.jpg?v=1605801007";
		
		Foto f1 = new Foto("Il Natale più bello di sempre", "Descrizione del mio Natael", url1, "#MyChristmas, #HappyNewYear", true, categories1);
		Foto f2 = new Foto("L'estate più bella della mia vita", "Descrizione della mia estate", url2, "#MySummer", true, categories2);
		Foto f3 = new Foto("Giornata in montagna", "Descrizione della mia giornata in montagna", url3, "#MyWinter, #Nature", false);
		Foto f4 = new Foto("Il mio compleanno", "Descrizione della mio compleanno", url4, "#MyBirthday", true, categories3);
		
		fotoService.save(f1);
		fotoService.save(f2);
		fotoService.save(f3);
		fotoService.save(f4);
		
		System.err.println("-----------------------------------------------\nCategorie per foto" + categoryService.findAllWithFoto());
		
		System.err.println("-----------------------------------------------");
		
		Role r1 = new Role("USER");
		Role r2 = new Role("ADMIN");
		
		Set<Role> roles = new HashSet<>();
		roles.add(r1);
		roles.add(r2);
		
		roleService.save(r1);
		roleService.save(r2);
		
		User user = new User("user", "{noop}userpsw", r1);
		User admin = new User("admin", "{noop}adminpsw", r2);
		User superadmin = new User("superadmin", "{noop}superadminpsw", roles);
		
		userService.save(user);
		userService.save(admin);
		userService.save(superadmin);
		
	}

}
