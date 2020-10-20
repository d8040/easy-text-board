package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Scanner scan = new Scanner(System.in);
	Article[] articles = new Article[11]; 		// Article[]: articles이라는 리모컨을 조정 할 수 있는 리모컨 생성

	Article getArticle(int id) {
		return articles[id];
	}

	int no = 0;

	public void run() {
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}

		while (true) {
			System.out.printf("명령어) ");
			String command = scan.nextLine();

			if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				int id = no + 1;

				if (id >= articles.length) {
					System.out.println("더 이상 게시물을 저장 할 수 없습니다.");
					continue;
				}
				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("내용: ");
				String con = scan.nextLine();
				System.out.println(id + "번 게시물이 생성되었습니다.");
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
			} else if (command.equals("detail ")) {
				System.out.println("**명령어 오류");
			} else if (command.startsWith("detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 상세==");

				if (no <= 0 || inputId > no || inputId == 0) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}

				Article detailArticle = getArticle(inputId);
				System.out.println("번호: " + detailArticle.no);
				System.out.println("제목: " + detailArticle.sub);
				System.out.println("내용: " + detailArticle.con);

			}

			else if (command.equals("delete ")) {
				System.out.println("**명령어 오류");
			}

			else if (command.startsWith("delete ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println(" ==게시물 삭제==");

				if (no <= 0 || inputId > no) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}


//				Article delArticles = getArticle(inputId + 1);
//				articles.con = delArticles.con;
//				articles.sub = delArticles.sub;
//				articles.no = delArticles.no;
				for (int i = inputId; i < no; i++) {
					articles[i] = articles[i+1];
//					Article articles = getArticle(inputId);
//					articles.con = articles.con+1;
//					articles.sub = articles.sub+1;
//					articles.no = articles.no+1;
					

//				System.out.println(getArticle(i).con);
//				System.out.println(getArticle(i).sub);
//				System.out.println(getArticle(i).no);
				}
//				no--;

			} else if (command.equals("exit")) {
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
