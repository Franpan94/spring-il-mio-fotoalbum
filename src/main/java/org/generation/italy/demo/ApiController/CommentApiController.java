package org.generation.italy.demo.ApiController;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Foto;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/comments")
public class CommentApiController {

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/photo/{id}")
	public List<Comment> getCommentByPhotoId(@PathVariable("id") int id){
		
		List<Comment> comments = fotoService.findById(id).getComments();
		
		return comments;
	}
	
	@PostMapping("/add/{id}")
	public Comment addCommentPhoto(@PathVariable("id") int id, @Valid Comment comment) {
		
		Foto f = fotoService.findById(id);
		
		Comment c = new Comment(comment.getText(), f);
		
		commentService.save(c);
		
		return c;
		
	}
}
