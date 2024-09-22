package com.sg.dto;

import java.time.LocalDate;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sg.entity.Post;

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
public class CommentDTO {

	@Nullable
	private Long commentId;

	private String message;

	@JsonFormat(pattern =  "dd-MM-yyyy")
	private LocalDate commentDate;

	@Nullable
	@JsonIgnore
	private PostDTO post;

	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", message=" + message + ", commentDate=" + commentDate
				+ ", post=" + post + "]";
	}
	
}
