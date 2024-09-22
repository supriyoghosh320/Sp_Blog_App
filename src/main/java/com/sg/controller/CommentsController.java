package com.sg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.dto.CommentDTO;

import com.sg.service.ICommentsService;


@RestController
@RequestMapping("/api/v1")

public class CommentsController {

	@Autowired
	private ICommentsService commentsService;

	@PostMapping("/{postId}/comment")
	public ResponseEntity<CommentDTO> createPost(@PathVariable Long postId, @RequestBody CommentDTO commentDTO)
			throws Exception {
		return new ResponseEntity<>(commentsService.createCommemt(postId, commentDTO), HttpStatus.CREATED);
	}

}
