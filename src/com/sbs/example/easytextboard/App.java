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
		int index = getIndexById(id);
		
		if (index == -1) {
			return null;
		}
		
		return articles[index];
	}
	
	private int getIndexById(int id) {
		for (int i = 0 ; i < size(); i++) {
			if (articles[i].no == id) {
				return i;
			}
		}
		
		return -1;
	}


	public void run() {

		while (true) {
			System.out.printf("명령어) ");
			String command = scan.nextLine();

			if (command.equals("add")) {
				System.out.println("==게시물 등록==");				

				Article[] newArticles = new Article[articles.length +5];
				
				for (int i = 0 ; i < articles.length ; i++) {
					newArticles[i] = articles[i];
				}
				
				articles = newArticles;

				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("내용: ");
				String con = scan.nextLine();				

				Article article = new Article();
				article.sub = sub;
				article.con = con;
				article.no = no + 1;
				
				System.out.println(article.no + "번 게시물이 생성되었습니다.");
				
				articles[size] = article;
				size++;
				no = article.no;
			}

			else if (command.equals("list")) {
				System.out.println("==게시물 리스트==");

				if (size() == 0) {
					System.out.println("저장된 게시물이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 0; i <= size()-1; i++) {
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

				for (int i = inputId; i < size(); i++) {
					articles[i - 1] = articles[i];

				}
				size--;
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

				article.sub = sub;
				article.con = con;

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