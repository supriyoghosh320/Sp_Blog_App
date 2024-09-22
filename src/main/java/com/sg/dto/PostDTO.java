package com.sg.dto;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sg.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {

	@Nullable
	private Long postId;

	private String title;

	private String description;

	@JsonFormat(pattern =  "dd-MM-yyyy")
	private LocalDate postDate;

	@Nullable
	private Set<CommentDTO> comments;

	@Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", title=" + title + ", description=" + description + ", postDate="
				+ postDate + ", comments=" + comments + "]";
	}

}
