package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	Article[] articles = new Article[33];
	Scanner sc = new Scanner(System.in);

	int id = 0;
	int size = 0;

	public int size() {
		return size;
	}

	public App() {
		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
		}
	}

	public Article getArticle(int id) {
		int index = getIndexId(id);
		if (index == -1) {
			return null;
		}
		return articles[index];
	}

	private int getIndexId(int id) {
		for (int i = 0; i < size(); i++) {
			if (articles[i].id == id) {
				return i;
			}
		}
		return -1;
	}

	private int add(String title, String body) {
		Article[] newArticles = new Article[articles.length + 5];
		for (int i = 0; i < articles.length; i++) {
			newArticles[i] = articles[i];
		}

		articles = newArticles;
		Article article = new Article();
		article.title = title;
		article.body = body;
		article.id = id + 1;

		articles[size] = article;
		size++;
		id = article.id;

		return article.id;
	}

	private int listPage(int id) {
		if (id <= 1) {
			id = 1;
		}

		int page = 10;
		int start = size() - 1;
		start -= (id - 1) * page;
		int end = start - (page - 1);

		if (end < 0) {
			end = 0;
		}

		for (int i = start; i >= end; i--) {
			Article article = articles[i];

			System.out.println(article.id + " / " + article.title);
		}
		if (start < 0) {
			System.out.println(id + "페이지가 존재하지 않습니다.");
		}
		return 0;
	}

	public void run() {

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String body = sc.nextLine();

				int no = add(title, body);
				System.out.println(no + "번 게시물이 생성되었습니다.");
			}

			else if (command.startsWith("list ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.printf("==%d 페이지==\n", inputId);

				if (size() == 0) {
					System.out.println(inputId + "페이지에 게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");
				int listNo = listPage(inputId);
			}

			else if (command.startsWith("detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 상세==");

				Article article = getArticle(inputId);

				if (article == null) {
					System.out.println(inputId + "번 게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호: " + article.id);
				System.out.println("제목: " + article.title);
				System.out.println("내용: " + article.body);
			}

			else if (command.startsWith("del ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 삭제==");

				Article article = getArticle(inputId);

				if (article == null) {
					System.out.println(inputId + "번 게시물이 존재하지 않습니다.");
					continue;
				}

				for (int i = inputId; i < size(); i++) {
					articles[i - 1] = articles[i];
				}
				size--;

				System.out.println(inputId + "번 게시물이 삭제되었습니다.");
			}

			else if (command.startsWith("modify ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.printf("==게시물 수정==");

				Article article = getArticle(inputId);

				if (article == null) {
					System.out.println(inputId + "번 게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.printf("게시물번호: %s\n", article.id);
				System.out.printf("제목: ");
				article.title = sc.nextLine();
				System.out.printf("내용: ");
				article.body = sc.nextLine();
				System.out.printf("%s번 게시물이 변경되었습니다.\n", inputId);
			}

			else if (command.startsWith("search ")) {
				String[] commandBits = command.split(" ");
				String searchKeyword = commandBits[1];

				int page = 1;

				if (commandBits.length >= 3) {
					page = Integer.parseInt(commandBits[2]);
				}
				if (id <= 1) {
					id = 1;
				}

				System.out.println("==게시물 검색==");
				int searchResultArticleLen = 0;

				for (Article article : articles) {
					if (article == null) {
						break;
					}
					if (article.title.contains(searchKeyword) || article.body.contains(searchKeyword)) {
						searchResultArticleLen++;
					}
				}

				Article[] searchResultArticles = new Article[searchResultArticleLen];

				int searchResulArticleIndex = 0;
				for (Article article : articles) {
					if (article == null) {
						break;
					}
					if (article.title.contains(searchKeyword) || article.body.contains(searchKeyword)) {
						searchResultArticles[searchResulArticleIndex] = article;
						searchResulArticleIndex++;
					}
				}
				if(searchResultArticles.length == 0) {
					System.out.println("검색결과가 존재하지 않습니다.");
					continue;
				}
				

				int searchPage = 10;
				int start = searchResultArticles.length - 1;
				start -= (page - 1) * searchPage;
				int end = start - (searchPage - 1);

				if (end < 0) {
					end = 0;
				}

				for (int i = start; i >= end; i--) {
					Article article = articles[i];

					System.out.println(article.id + " / " + article.title);
				}
				if (start < 0) {
					System.out.println(id + "페이지가 존재하지 않습니다.");
				}
			}
			
			else if (command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}
			
			else {
				System.out.println("**명령어 오류");
			}

		}
	}

}
