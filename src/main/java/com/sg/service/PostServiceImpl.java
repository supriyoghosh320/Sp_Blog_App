package com.sg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.dto.CommentDTO;
import com.sg.dto.PostDTO;
import com.sg.entity.Comment;
import com.sg.entity.Post;
import com.sg.exception.PostAlreadyExistException;
import com.sg.repo.ICommentsRepo;
import com.sg.repo.IPostRepo;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostRepo postRepo;
	@Autowired
	private ICommentsRepo commentsRepo;

	@Override
	public PostDTO createPost(PostDTO postDTO) throws Exception {
		// TODO Auto-generated method stub
		Post dbPost = postRepo.findByTitleOrDescription(postDTO.getTitle(), postDTO.getDescription());
		if (dbPost != null) {
			throw new PostAlreadyExistException("Post Already Exist Having Title :: " + postDTO.getTitle());
		}
		Post post = mapToPost(postDTO);
		Set<Comment> cmts = post.getComments();
		for (Comment cmt : cmts) {
			cmt.setPost(post);
		}
		//System.out.println(post);
		post = postRepo.save(post);
		postDTO = mapToPostDTO(post);
		return postDTO;
	}

	@Override
	public List<PostDTO> getAllPosts() throws Exception {
		List<Post> allPost = postRepo.findAll();
		List<PostDTO> listPost = null;
		PostDTO dto = new PostDTO();
		if (allPost.size() > 0) {
			listPost = allPost.stream().map(post -> {
			
				return mapToPostDTO(post);
			}).collect(Collectors.toList());
		}

		return listPost;
	}

	private PostDTO mapToPostDTO(Post post) {
		PostDTO dto = new PostDTO();
		 dto.setComments(
				 post.getComments().stream().map(cmt -> {
						CommentDTO comment = new CommentDTO();
						BeanUtils.copyProperties(cmt, comment);
						return comment;

					}).collect(Collectors.toSet())
				 
				 );
		dto.setDescription(post.getDescription());
		dto.setPostDate(post.getPostDate());
		dto.setPostId(post.getPostId());
		dto.setTitle(post.getTitle());
		return dto;
	}

	private Post mapToPost(PostDTO postDTO) {
		Post post = new Post();
		//System.out.println("PostDTO " + postDTO);
		/*
		 * postDTO.getComments().stream().map(cmt -> { Comment comment = new Comment();
		 * BeanUtils.copyProperties(cmt, comment); return comment;
		 * 
		 * }).collect(Collectors.toSet());
		 */
		post.setComments(postDTO.getComments().stream().map(cmt -> {
			Comment comment = new Comment();
			BeanUtils.copyProperties(cmt, comment);
			return comment;

		}).collect(Collectors.toSet()));
		post.setDescription(postDTO.getDescription());
		post.setPostDate(postDTO.getPostDate());
		post.setPostId(postDTO.getPostId());
		post.setTitle(postDTO.getTitle());

		return post;
	}
}
