package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.println("명령어)");
		String command = scanner.nextLine();
		
		
		if (command.equals("article list")) {
			System.out.println("==게시물 리스트==");
		}
		else if (command.equals("article add")) {
			System.out.println("==게시물 작성==");
		}
		else {
			System.out.println("==존재하지 않는 명령어==");
				
			}
		}

	}

