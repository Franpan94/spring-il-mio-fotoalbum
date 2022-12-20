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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Il titolo deve contenere qualcosa")
	@Size(min = 3, message = "Il titolo deve contenere almeno 3 caratteri")
	@Column(unique = true)
	private String title;
	
	@NotEmpty(message = "La descrizione deve contenere qualcosa")
	@Size(min = 5, message = "La descrizione deve contenere almeno 5 caratteri")
	@Column
	private String description;
	
	@NotEmpty(message = "L'immagine deve contenere qualcosa")
	@Size(min = 5, message = "L' immagine deve contenere almeno 5 caratteri")
	@Column
	private String url;
	
	@NotEmpty(message = "Il campo tag deve contenere qualcosa")
	@Size(min = 3, message = "I tag devono contenere almeno 3 caratteri")
	@Column
	private String tag;
	
	@NotNull(message = "La visibilità deve essere inserita")
    @Column
    private boolean visible;
	
	@ManyToMany
	@JsonIgnore
	List<Category> categories;

	public Foto() {
		
	}
	
	public Foto(String title, String description, String url, String tag, boolean visible) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setTag(tag);
		setVisible(visible);
	}
	
	public Foto(String title, String description, String url, String tag,
			    boolean visible, List<Category> categories) {
		this(title, description, url, tag, visible);
		setCategories(categories);
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	@Override
	public String toString() {
		return "\nid: " + getId() + "\ntitolo: " + getTitle() + "\nvisibilità: " + isVisible();
	}
}
