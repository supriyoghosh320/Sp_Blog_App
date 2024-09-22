package com.sg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.entity.Comment;


@Repository
public interface ICommentsRepo extends JpaRepository<Comment, Long>{

}
