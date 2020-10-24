package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	Scanner scan = new Scanner(System.in);
	private Article[] articles = new Article[5];

	private int no = 0;
	private int size = 0;

	private int size() {
		return size;
	}

	private Article getArticle(int id) {
		int index = getIndexId(id);

		if (index == -1) {
			return null;
		}
		return articles[index];
	}

	private int getIndexId(int id) {
		for (int i = 0; i < size(); i++) {
			if (articles[i].no == id) {
				return i;
			}
		}
		return -1;
	}

	private int add(String sub, String con) {
		Article[] newArticles = new Article[articles.length + 5];

		for (int i = 0; i < articles.length; i++) {
			newArticles[i] = articles[i];
		}

		articles = newArticles;

		Article article = new Article();
		article.sub = sub;
		article.con = con;
		article.no = no + 1;

		articles[size] = article;
		size++;
		no = article.no;

		return article.no;
	}


	public void run() {

		while (true) {
			System.out.printf("명령어) ");
			String command = scan.nextLine();

			if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("제목: ");
				String con = scan.nextLine();

				int no = add(sub, con);
				System.out.printf("%d 번 게시물이 생성되었습니다.\n", no);
			}
			
			else if(command.startsWith("list ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.printf("==%s 페이지==\n",inputId);

				if (size() == 0) {
					System.out.println(inputId+" 페이지에 게시물이 없습니다.");
					continue;
				}
				
				if (inputId <= 1) {
					inputId = 1;
				}
				
				int page = 10;
				int start = size() -1;
				start -= (inputId - 1) * page;
				int end = start - (page - 1);
				
				if (end < 0) {
					end = 0;
				}

				System.out.println("번호 / 제목");

				for (int i = start; i >= end; i--) {
					Article article = articles[i];

					System.out.println(article.no + " / " + article.sub);
				}
			}

			else if (command.equals("list")) {
				System.out.println("==게시물 리스트==");

				if (size() == 0) {
					System.out.println("저장된 게시물이 없습니다.");
					continue;
				}

				System.out.println("번호 / 제목");

				for (int i = size() - 1; i >= 0; i--) {
					Article article = articles[i];

					System.out.println(article.no + " / " + article.sub);
				}
			}
			
			else if(command.startsWith("detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 상세==");
				
				Article article = getArticle(inputId);
				
				if (article == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputId);
					continue;
				}
				
				System.out.println("번호: " + article.no);
				System.out.println("제목: " + article.sub);
				System.out.println("내용: " + article.con);
			}
			
			else if (command.startsWith("modify ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 수정==");
				
				Article article = getArticle(inputId);
				
				if (article == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputId);
				}
				
				System.out.printf("게시물 번호: %s\n", article.no);
				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("제목: ");
				String con = scan.nextLine();
				System.out.printf("%d번 게시물이 수정되었습니다.\n", article.no);
				
				article.sub = sub;
				article.con = con;
			}
			
			else if (command.startsWith("del ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println(" ==게시물 삭제==");

				if (no <= 0 || inputId > no) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}
				for (int i = inputId; i < size(); i++) {
					articles[i - 1] = articles[i];

				}
				size--;

				System.out.println(inputId + "번 게시물이 삭제되었습니다.");
			}			
			
			else if (command.startsWith("search ")) {
				String inputId = command.split(" ")[1];
				System.out.println("==게시물 검색==");
				
/*				if (articles == null || inputId == null) {
					System.out.printf("게시물이 존재하지 않습니다.\n");
					continue;
				}*/
				
				for (int i = 0; i < size(); i++) {						
					if(inputId.equals(articles[i].sub)) {						
						Article article = articles[i];
					System.out.println(article.no + " / " + article.sub);
					}
					else {
						System.out.println("없음");
					}
				}
			}	
			
			else if(command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}
			
			else {
				System.out.println("**명령어 오류");
			}
		}
	}
}
