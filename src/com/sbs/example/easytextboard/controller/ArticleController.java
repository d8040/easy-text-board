package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Article;
import com.sbs.example.easytextboard.service.ArticleService;

public class ArticleController{
	private ArticleService articleService;
	
	public ArticleController() {
		articleService = Container.artcleService;
	}
	public void run(Scanner scan, String command) {

		if (command.equals("article add")) {
			System.out.println("==게시물 등록==");

			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해 주세요");
				return;
			}

			System.out.printf("제목: ");
			String con = scan.nextLine();
			System.out.printf("내용: ");
			String sub = scan.nextLine();

			int id = articleService.add(Container.session.loginedMemberId, sub, con);

			System.out.println(id + "번 게시물이 생성 되었습니다.");
		}

		else if (command.equals("article list")) {
			System.out.println("==게시물 리스트==");

			System.out.println("번호 / 작성자 / 제목");

			for (int i = articleService.getArticles().size() - 1; i >= 0; i--) {
				Article article = articleService.getArticles().get(i);
				System.out.printf("%d / %s / %s\n", article.no, article.memberId, article.sub);
			}
		}

		else if (command.startsWith("article list ")) {
			int inputId = Integer.parseInt(command.split(" ")[2]);

			System.out.println("==게시물 리스트==");
			if (inputId <= 1) {
				inputId = 1;
			}

			if (articleService.getArticlesSize() == 0) {
				System.out.println("저장된 게시물이 없습니다.");
				return;
			}

			int page = 10;
			int start = articleService.getArticlesSize() - 1;
			start -= (inputId - 1) * page;
			int end = start - (page - 1);

			if (end < 0) {
				end = 0;
			}

			System.out.println("번호 / 작성자 / 제목");
			if (start < 0) {
				System.out.println(page + "페이지는 존재하지 않습니다.");
			}
			for (int i = start; i >= end; i--) {
				Article article = articleService.getArticlesByIndex(i);
				System.out.printf("%d / %s / %s\n", article.no, article.memberId, article.sub);
			}
			
		}

		else if (command.startsWith("article detail ")) {
			int inputId = 0;

			try {
				inputId = Integer.parseInt(command.split(" ")[2]);
			} catch (NumberFormatException e) {
				System.out.println("게시물 번호를 양의 정수로 입력해 주세요");
				return;
			}

			System.out.println("==게시물 디테일==");

			Article article = articleService.getArticle(inputId);

			if (article == null) {
				System.out.println(inputId + "번 게시물는 존재 하지 않습니다.");
				return;
			}

			System.out.println("번호: " + article.no);
			System.out.println("제목: " + article.sub);
			System.out.println("내용: " + article.con);
		}

		else if (command.startsWith("article del ")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해 주세요");
				return;
			}

			int inputId = Integer.parseInt(command.split(" ")[2]);
			System.out.println("==게시물 삭제==");

			Article article = articleService.getArticle(inputId);

			if (article == null) {
				System.out.println(inputId + "번 게시물는 존재 하지 않습니다.");
				return;
			}

			articleService.remove(inputId);

			System.out.println(inputId + "번 게시물이 삭제되었습니다.");
		}

		else if (command.startsWith("article modify ")) {
			if (Container.session.isLogout()) {
				System.out.println("로그인 후 이용해 주세요");
				return;
			}

			int inputId = Integer.parseInt(command.split(" ")[2]);
			System.out.println("==게시물 수정==");

			Article article = articleService.getArticle(inputId);

			if (article == null) {
				System.out.println(inputId + "번 게시물는 존재 하지 않습니다.");
				return;
			}

			System.out.println("게시물 번호: " + article.no);
			System.out.printf("제목: ");
			String sub = scan.nextLine();
			System.out.printf("내용: ");
			String con = scan.nextLine();

			articleService.modify(inputId, sub, con);

		}

		else if (command.startsWith("article search ")) {
			String[] bits = command.split(" ");
			String keyword = bits[2];
			int page = 1;
			if (bits.length >= 4) {
				page = Integer.parseInt(bits[3]);
			}

			if (page <= 1) {
				page = 1;
			}
			System.out.println("==게시물 검색==");

			List<Article> resulArticles = new ArrayList();

			for (Article article : articleService.getArticles()) {
				if (article.sub.contains(keyword) || article.con.contains(keyword)) {
					resulArticles.add(article);
				}
			}

			if (resulArticles.size() == 0) {
				System.out.println("검색결과가 존재하지 않습니다.");
				return;
			}
			int searchPage = 10;
			int start = resulArticles.size() - 1;
			start -= (page - 1) * searchPage;
			int end = start - (searchPage - 1);

			if (end < 0) {
				end = 0;
			}

			System.out.println("번호 / 제목");

			for (int i = start; i >= end; i--) {
				Article article = resulArticles.get(i);

				System.out.println(article.no + " / " + article.sub);
			}
			if (start < 0) {
				System.out.println(page + "페이지는 존재하지 않습니다.");
				return;
			}
		}

		else {
			System.out.println("**명령어 오류");
		}
	}
}
