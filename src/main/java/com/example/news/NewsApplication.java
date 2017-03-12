package com.example.news;

import com.example.news.model.article.ArticleRepository;
import com.example.news.service.NewsFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class NewsApplication {

	@Autowired
	private NewsFetcherService nf;

	@Autowired
	private ArticleRepository articleRepo;

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

	@Async
	@Scheduled(cron="${fetchArticlesCron}")
	public void fetchAndSaveArticles() {
		articleRepo.save(nf.fetchArticles());
	}
}
