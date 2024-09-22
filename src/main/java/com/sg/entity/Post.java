package com.sg.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_POST", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }),
		@UniqueConstraint(columnNames = { "description" }) })
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;
	@Column(length = 100)
	private String title;
	@Column(length = 100)
	private String description;
	@Column
	private LocalDate postDate;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,targetEntity = Comment.class,orphanRemoval = true,mappedBy = "post")
	private Set<Comment> comments;

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", description=" + description + ", postDate=" + postDate
				+ ", comments=" + comments + "]";
	}

	
}
