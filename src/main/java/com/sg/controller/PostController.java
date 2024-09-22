package com.sg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.dto.PostDTO;
import com.sg.service.IPostService;

@RestController
@RequestMapping("/api/v1")

public class PostController {

	@Autowired
	private IPostService postService;

	@PostMapping("/post")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) throws Exception {
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}

	@GetMapping("/allPost")
	public ResponseEntity<List<PostDTO>> fetchAllPosts() throws Exception {
		return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
	}
}
