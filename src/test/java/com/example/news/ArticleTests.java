package com.example.news;

import com.example.news.model.article.Article;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ArticleTests {

	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final String DESC = "Description";
	private static final String URL = "http://test.com/article/1";
	private static final String IMAGE_URL = URL+"/image.jpeg";

	@Test
	public void createsValidArticle() {
		Article a = new Article(1L, new Date(), TITLE, AUTHOR, IMAGE_URL, DESC, URL);
		assertNotNull(a);
		assertEquals(TITLE, a.getTitle());
		assertEquals(AUTHOR, a.getAuthor());
		assertEquals(DESC, a.getDescription());
		assertEquals(URL, a.getUrl());
		assertEquals(IMAGE_URL, a.getThumbnailImageUrl());
	}

	// TODO: Add validation and test against it
}
