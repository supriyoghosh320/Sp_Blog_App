package com.sg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.entity.Post;

@Repository
public interface IPostRepo extends JpaRepository<Post, Long>{

	public Post findByTitleOrDescription(String title,String description);
}
