package com.sbs.example.easytextboard.service;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Article;

public class ArticleService {
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	private int id;

	private int getIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).no == id) {
				return i;
			}
		}
		return -1;
	}

	public Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}
		return articles.get(index);
	}

	public ArticleService() {
		articles = new ArrayList<>();
		id = 0;

		for (int i = 1; i < 32; i++) {
			add(i % 2 == 0 ? 1 : 2, "제목" + i, "내용" + i);
		}
	}

	public int add(int memberId, String sub, String con) {
		Article article = new Article();

		article.no = id + 1;
		article.memberId = memberId;
		article.sub = sub;
		article.con = con;
		articles.add(article);

		id = article.no;
		return article.no;
	}

	public void remove(int id) {
		int index = getIndexById(id);
		if (index == -1) {
			return;
		}
		articles.remove(index);
	}

	public void modify(int inputId, String sub, String con) {
		Article article = getArticle(inputId);
		article.sub = sub;
		article.con = con;		
	}

	public int getArticlesSize() {
		return articles.size();
	}

	public Article getArticlesByIndex(int i) {
		return articles.get(i);
	}

}
