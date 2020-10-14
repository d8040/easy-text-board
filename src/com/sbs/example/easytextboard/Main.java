package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int no = 0;
		String sub = "";
		String co = "";
		
		int id1 = 0;
		String sub1 = "";
		String co1 = "";
		
		int id2 = 0;
		String sub2 = "";
		String co2 = "";

		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();
			if (command.equals("add")) {

				int id = no + 1;

				System.out.println("==게시물 등록==");
				System.out.printf("제목: ");
				sub = scanner.nextLine();
				System.out.printf("내용: ");
				co = scanner.nextLine();
				System.out.println(id + "번에 저장되었습니다.");

				no = id;

				if (id == 1) {
					sub1 = sub;
					co1 = co;
					id1 = id;
				}
				if (id == 2) {
					sub2 = sub;
					co2 = co;
					id2 = id;
				}

			} else if (command.equals("list")) {
				System.out.println("==게시물 목록==");
				if (no == 0) {
					System.out.println("저장된 게시물이 없습니다.");
					continue;
				}
				System.out.println("번호 / 제목");
				if (no >= 1) {
					System.out.println(id1 + " / " + sub1);
				}
				if (no >= 2) {
					System.out.println(id2 + " / " + sub2);
				}

			} else if (command.equals("detail")) {
				System.out.println("==게시물 상세==");
				if (no == 0) {
					System.out.println("저장된 게시물이 없습니다.");
					continue;
				}
				if(no >= 1) {
					System.out.println("번호: "+ id1);
					System.out.println("제목: "+ sub1);
					System.out.println("내용: "+ co1);
				} 
				if(no >= 2) {
					System.out.println("번호: "+ id2);
					System.out.println("제목: "+ sub2);
					System.out.println("내용: "+ co2);
				}
			}
			else if (command.equals("exit")) {
				System.out.println("==프로그램 종료==");
				break;
			} else {
				System.out.println("**명령어 오류");
			}

		}
		scanner.close();
	}
}