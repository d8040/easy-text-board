package com.sbs.example.easytextboard;

import java.util.Scanner;


public class App {
	Scanner scan = new Scanner(System.in);
	Article[] articles = new Article[11];

	public Article getArticle(int id) {
		return articles[id];
	}

	int no = 0;

	public void run() {

		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}
System.out.println(articles.length);
		while (true) {

			System.out.printf("명령어) ");
			String command = scan.nextLine();

			if (command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			}

			else if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				int id = no + 1;
				
				if (id > articles.length-1) {
					System.out.println("더 이상 게시물을 저장할 수 없습니다.");
					continue;
				}

				
				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("내용: ");
				String con = scan.nextLine();
				System.out.println(id + "번 게시물이 저장되었습니다.");
				no = id;

				Article articles = getArticle(id);

				articles.sub = sub;
				articles.con = con;
				articles.no = no;
			}

			else if (command.equals("list")) {
				System.out.println("==게시물 리스트==");

				if (no == 0) {
					System.out.println("저장된 게시물이 없습니다.");
					continue;
				}

				System.out.println("번호 / 제목");

				for (int i = 1; i <= no; i++) {
					Article articles = getArticle(i);
					System.out.println(articles.no + " / " + articles.sub);
				}
			}

			else if (command.equals("detail ")) {
				System.out.println("**명령어 오류**");
			}

			else if (command.startsWith("detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 상세==");

				if (no <= 0 || inputId > articles.length || inputId > no) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}
				Article selArticle = getArticle(inputId);
				System.out.println("번호: " + selArticle.no);
				System.out.println("제목: " + selArticle.sub);
				System.out.println("내용: " + selArticle.con);
			}

			else {
				System.out.println("**명령어 오류**");
			}

		}
		scan.close();

	}
}
