package com.sg.service;



import com.sg.dto.CommentDTO;


public interface ICommentsService {

	public CommentDTO createCommemt(Long postId,CommentDTO commentDTO) throws Exception;
}
