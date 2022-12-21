package org.generation.italy.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column
	private String text;
	
	@ManyToOne
	@JoinColumn(name ="fotos_id")
	@JsonIgnore
	private Foto photo;
	
	public Comment() {
		
	}
	
	public Comment(String text, Foto photo) {
		setText(text);
		setPhoto(photo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Foto getPhoto() {
		return photo;
	}

	public void setPhoto(Foto photo) {
		this.photo = photo;
	}

}
