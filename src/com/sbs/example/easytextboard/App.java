package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	Scanner scanner = new Scanner(System.in);
	Article[] article = new Article[11];

	public Article getArticle(int id) {
		for (int i = 1; i <= id; i++) {

			return article[id];
		}
		return null;
	}

	public void run() {
		for (int i = 0; i < article.length; i++) {
			article[i] = new Article();
		}

		int no = 0;

		while (true) {
			System.out.printf("명령어) ");

			String command = scanner.nextLine();

			if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				String con;
				String sub;

				int id = 1 + no;

				System.out.printf("제목: ");
				sub = scanner.nextLine();
				System.out.printf("내용: ");
				con = scanner.nextLine();
				System.out.println(id + "번 게시물에 저장 되었습니다.");

				no = id;

				Article article = getArticle(id);

				article.sub = sub;
				article.con = con;
				article.no = no;

			}

			else if (command.equals("list")) {
				System.out.println("==게시물 리스트==");

				if (no == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 1; i <= no; i++) {
					Article article = getArticle(i);

					System.out.println(article.no + " / " + article.sub);
				}
//				if (no >= 1) {
//
//					System.out.println(article1.no + " / " + article1.sub);
//				}
//				if (no >= 2) {
//
//					System.out.println(article2.no + " / " + article2.sub);
//				}
			} else if (command.equals("detail")) {
				System.out.println("**명령어 오류");
				continue;
			} else if (command.startsWith("detail")) {
				int inputedId = Integer.parseInt(command.split("l")[1]);

				Article selArticle = getArticle(inputedId);

				if (selArticle == null || selArticle.no == 0) {
					System.out.println(inputedId + "게시물이 존재 하지 않습니다");
					continue;
				}

				System.out.println("==게시물 상세==");
				System.out.println("번호: " + selArticle.no);
				System.out.println("제목: " + selArticle.sub);
				System.out.println("내용: " + selArticle.con);

			}

			else if (command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}

			else {
				System.out.println("명령어 오류");
			}
		}
		scanner.close();
	}
}
