package com.example.news.service;

import com.example.news.model.article.Article;
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class NewsFetcherService {

	@Value("${feedPath}")
	private String path;
	@Value("${feedImageType}")
	private String[] imageType;


	public List<Article> fetchArticles() {
		List<Article> articles = new ArrayList<>();
		try {
			URL feedUrl = new URL(this.path);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));
			String feedAuthor = feed.getAuthor();
			Article article;

			for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {

				Date datePublished = entry.getPublishedDate();
				String title = entry.getTitle();
				// author tends to be empty for each individual item - if so just take the feed author
				String author= entry.getAuthor().isEmpty() ? feedAuthor : entry.getAuthor();
				String thumbnailImageUrl = "";

				// Get the dc identifier for each item and use as ID so we only store unique news articles
				DCModule dc = (DCModule) entry.getModule(DCModule.URI);
				Long id = Long.valueOf(dc.getIdentifier());

				// Image URL nested in enclosures of each item, so need to loop through them to get it
				for (SyndEnclosure enc : (List<SyndEnclosure>) entry.getEnclosures()) {
					if (Arrays.asList(imageType).contains(enc.getType())) {
						thumbnailImageUrl = enc.getUrl();
					}
				}

				String description = entry.getDescription().getValue();
				String url = entry.getUri();
				article = new Article(id,datePublished,title,author,thumbnailImageUrl,description,url);
				articles.add(article);
				log.debug(article.toString());
			}

		} catch (FeedException | IOException e) {
			log.error("Error fetching news feed", e);
		}
		return articles;
	}

}
