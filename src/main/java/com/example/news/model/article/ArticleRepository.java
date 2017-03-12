package com.example.news.model.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
@SuppressWarnings("unused") // find methods not used directly but used by rest repo search
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

	Page findByTitleContainingIgnoreCase(@Param("keyword") String keyword, Pageable p);

	Page findByDatePublishedAfter(@Param("date") Date d, Pageable p);

	Page findByDatePublishedAfterAndTitleContainingIgnoreCase(@Param("date") Date d, @Param("keyword") String keyword, Pageable p);


}