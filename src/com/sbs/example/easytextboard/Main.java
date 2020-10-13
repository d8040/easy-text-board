package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//1번 게시물 저장소
		int article1__id = 0;
		String article1__title = "";
		String article1__body = "";
		//2번 게시물 저장소
		int article2__id = 0;
		String article2__title = "";
		String article2__body = "";
		
		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.equals("add")) {

				int id = 1 + lastArticleId;
				String title;
				String body;

				System.out.println("==게시물 추가==");
				System.out.printf("제목: ");
				title = scanner.nextLine();
				System.out.printf("내용: ");
				body = scanner.nextLine();

				lastArticleId = id;
				
				if (id == 1) {
					article1__id = id;
					article1__title = title;
					article1__body = body;					
				}
				if (id == 2) {
					article2__id = id;
					article2__title = title;
					article2__body = body;					
				}

				System.out.println(id + "번 게시물이 생성되었습니다.");

			} else if (command.equals("list")) {
				System.out.println("==게시물 목록==");
				
				if (lastArticleId == 0) {
					System.out.println("게시물이 없습니다.");
					continue; //이번턴을 지속 하겠다는 명령어
				}
				System.out.println("번호 / 제목");
				
				if (lastArticleId >= 1) {
					System.out.printf("%d / %s\n", article1__id, article1__title);
				}
				if (lastArticleId >= 2) {
					System.out.printf("%d / %s\n", article2__id, article2__title);
				}
			} else if (command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			} else {
				System.out.println("==명령어 오류==");
			}

		}
		scanner.close();
	}
}