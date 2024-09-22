package com.sg.service;



import java.util.List;

import com.sg.dto.PostDTO;


public interface IPostService {
	public PostDTO createPost(PostDTO postDTO) throws Exception;
	public List<PostDTO> getAllPosts() throws Exception;
}
