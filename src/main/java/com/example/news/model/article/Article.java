package com.example.news.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article {

	// TODO: Validation, types e.g. URL

	@Id @JsonIgnore private final Long id;
	private final Date datePublished;
	private final String title;
	private final String author;
	private final String thumbnailImageUrl;
	@Column(length = 1000) private final String description;
	@Column(length = 500) private final String url;
}