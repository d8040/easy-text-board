package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Scanner scan = new Scanner(System.in);
	private Article[] articles = new Article[5];

	private int no;
	private int size;

	private int size() {
		return size;
	}

	public App() { // init 초기 실행 클래스
		no = 0;
		size = 0;
		for (int i = 0; i < 32; i++) {
			add(i + 1 + "", i + 1 + "");
		}

	}

	private Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles[index];
	}

	private int getIndexById(int id) {
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

	private void remove(int id) {

		for (int i = id; i < size(); i++) {
			articles[i - 1] = articles[i];

		}
		size--;

	}

	private void modify(int id, String sub, String con) {

		Article article = getArticle(id);
		article.sub = sub;
		article.con = con;
	}

	public void run() {

		while (true) {
			System.out.printf("명령어) ");
			String command = scan.nextLine();

			if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("내용: ");
				String con = scan.nextLine();

				int no = add(sub, con);
				System.out.println(no + "번 게시물이 생성되었습니다.");

			}

			else if (command.startsWith("list ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);

				System.out.println("==페이지 표시==");

				if (inputId <= 1) {
					inputId = 1;
				}

				if (size() == 0) {
					System.out.println("저장된 게시물이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				int page = 10;
				int startPos = size() - 1;
				startPos -= (inputId - 1) * page;
				int endPos = startPos - (page - 1);

				if (endPos < 0) {
					endPos = 0;
				}

				if (startPos < 0) {
					System.out.println(inputId + "페이지가 존재하지 않습니다.");
				}

				for (int i = startPos; i >= endPos; i--) {
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

			else if (command.equals("detail ")) {
				System.out.println("**명령어 오류");
			}

			else if (command.startsWith("detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 상세==");

				Article article = getArticle(inputId);

				if (article == null) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}

				System.out.println("번호: " + article.no);
				System.out.println("제목: " + article.sub);
				System.out.println("내용: " + article.con);

			}

			else if (command.equals("del ")) {
				System.out.println("**명령어 오류");
			}

			else if (command.startsWith("del ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println(" ==게시물 삭제==");

				if (no <= 0 || inputId > no) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}

				remove(inputId);

				System.out.println(inputId + "번 게시물이 삭제되었습니다.");

			}

			else if (command.startsWith("modify ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 수정==");

				Article article = getArticle(inputId);

				if (article == null) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}

				System.out.println("게시물 번호: " + article.no);
				System.out.printf("수정제목: ");
				String sub = scan.nextLine();
				System.out.printf("수정내용: ");
				String con = scan.nextLine();

				System.out.println(inputId + "번 게시물이 수정 되었습니다.");

				modify(inputId, sub, con);

			}

			else if (command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}

			else {
				System.out.println("**명령어 오류");
			}
		}
		scan.close();
	}

}