package com.example.news;

import com.example.news.model.article.Article;
import com.example.news.service.NewsFetcherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NewsFetcherServiceTests {

	@Autowired
	private NewsFetcherService nf;

	private static final String TITLE="Test Title ";
	private static final String DESC="This is the test news article ";
	private static final String URL="http://www.test.com/";

	@Test
	public void fetchesArticlesFromRssFeed() {
		List<Article> articles = nf.fetchArticles();
		articles.sort(Comparator.comparing(Article::getId));

		assertEquals(3, articles.size());

		// Should be 3 articles imported from our test.rss sample file in test resources folder
		for(int i = 1; i< 4;i++) {
			assertEquals(Long.valueOf(i), articles.get(i-1).getId());
			assertEquals(TITLE + i, articles.get(i-1).getTitle());
			assertEquals(DESC + i, articles.get(i-1).getDescription());
			assertEquals(URL + i + ".jpeg", articles.get(i-1).getThumbnailImageUrl());
			assertEquals(URL + i, articles.get(i-1).getUrl());
		}

	}
}
