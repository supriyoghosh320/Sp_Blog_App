package com.sg.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.dto.CommentDTO;
import com.sg.entity.Comment;
import com.sg.entity.Post;
import com.sg.exception.ResourceNotFoundException;
import com.sg.repo.ICommentsRepo;
import com.sg.repo.IPostRepo;

@Service
public class CommentsServiceImpl implements ICommentsService {

	@Autowired
	private IPostRepo postRepo;
	@Autowired
	private ICommentsRepo commentsRepo;

	@Override
	public CommentDTO createCommemt(Long postId, CommentDTO commentDTO) throws Exception {
		// TODO Auto-generated method stub
		Post dbPost = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post Not Found Having PostId :: " + postId));
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDTO, comment);
		comment.setPost(dbPost);
		comment = commentsRepo.save(comment);
		BeanUtils.copyProperties(comment, commentDTO);
		return commentDTO;
	}

}
