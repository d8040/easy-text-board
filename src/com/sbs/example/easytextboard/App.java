package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	Scanner scan = new Scanner(System.in);
	Article[] articles = new Article[11];
	
	
	public Article getArticle(int id) {
		return articles[id];
	}

	public void run() {
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}
		
		int no = 0;
		while (true) {
			System.out.printf("명령어) ");
			String command = scan.nextLine();
					
			int id = no + 1;
				
			if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				if (id >= articles.length) {
					System.out.println("더이상 저장할 수 없습니다.");
					continue;
				}			
				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("내용: ");
				String con = scan.nextLine();
				System.out.println(id + "번 게시물에 저장되었습니다.");

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
				for (int i = 1; i<= no; i++) {
					Article articles = getArticle(i);
					System.out.println(articles.no + " / " + articles.sub );					
				}
			}
			else if (command.startsWith("detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				Article selArticle = getArticle(inputId);
				
				if (selArticle == null || selArticle.no == 0) {
					System.out.println(inputId + "번 게시물이 존재하지 않습니다.");
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
				System.out.println("**명령어 오류");
			}

		}
		scan.close();
	}
}
