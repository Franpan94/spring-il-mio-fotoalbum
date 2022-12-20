package org.generation.italy.demo.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Category {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Il nome deve contenere qualcosa")
	@Size(min = 3, message = "Il nome deve contenere almeno 3 caratteri")
	@Column(unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Foto> fotos;
	
	public Category() {
		
	}

	public Category(String name) {
		setName(name);
	}
	
	public Category(String name, List<Foto> fotos) {
		setName(name);
		setFotos(fotos);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	@Override
	public String toString() {
		
		return "\nId: " + getId() + "\nNome: " + getName();
	}
}
