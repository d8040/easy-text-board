package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int lastArticleId = 0;
		while (true) {
			
			System.out.printf("명령어) ");
			String command = scanner.nextLine();
			
			if (command.equals("add")) {
				
				int id = 1 + lastArticleId;
				String title;
				String body;
				
				System.out.println("게시물 추가");
				System.out.printf("제목: ");
				title = scanner.nextLine();
				System.out.printf("내용: ");
				body = scanner.nextLine();
				
				lastArticleId = id;
				
				System.out.println(id +"번 게시물이 생성되었습니다.");
				
			} 
			else if (command.equals("list")) {
				System.out.println("게시물 목록");
			} 
			else if (command.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			} 
			else {
				System.out.println("명령어 오류");
			}
			
		}
		scanner.close();
	}
}