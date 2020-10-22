package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	Scanner scan = new Scanner(System.in);
	private Article[] articles = new Article[5]; // Article[]: articles이라는 리모컨을 조정 할 수 있는 리모컨 생성
	
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
			if (articles[i].no == no) {
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

				if (size() >= articles.length) {
					System.out.println("더 이상 게시물을 저장 할 수 없습니다.");
					continue;
				}

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

				if (no <= 0 || inputId > size() || inputId == 0) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}

				Article article = articles[inputId-1];
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

				for (int i = inputId +1; i < size(); i++) {
					articles[i - 1] = articles[i];

				}
				size--;
				System.out.println(inputId + "번 게시물이 삭제되었습니다.");

			}

			else if (command.startsWith("modify ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);
				System.out.println("==게시물 수정==");

				if (no <= 0 || inputId > size() || inputId == 0) {
					System.out.println(inputId + "번 게시물이 없습니다.");
					continue;
				}

				Article article = getArticle(inputId);

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