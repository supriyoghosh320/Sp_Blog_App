package com.sg.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TBL_COMMENT")
@Entity
public class Comment {

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", message=" + message + ", commentDate=" + commentDate + ", post="
				+ post + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentId;
	@Column(length = 100)
	private String message;
	@Column
	private LocalDate commentDate;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,targetEntity = Post.class)
	@JoinColumn(name = "post_id",referencedColumnName = "postId")
	private Post post;
}
