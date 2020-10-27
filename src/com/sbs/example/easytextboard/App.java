package com.sbs.example.easytextboard;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.stream.events.Comment;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class App {
	Scanner scan = new Scanner(System.in);
	List<Article> articles = new ArrayList<Article>();

	private int no;
	private int size;

	public App() {

		no = 0;
		size = 0;

		for (int i = 0; i < 32; i++) {
			articles.add(new Article(articles.size() + 1, "제목" + (i + 1), "내용" + (i + 1)));
		}
	}

	public void run() {

		while (true) {
			System.out.printf("명령어) ");
			String command = scan.nextLine();

			if (command.equals("add")) {
				System.out.println("==게시물 등록==");

				int id = no + 1;

				System.out.printf("제목: ");
				String con = scan.nextLine();
				System.out.printf("내용: ");
				String sub = scan.nextLine();
				System.out.println(id + "번 게시물이 생성 되었습니다.");

				articles.add(new Article(id, sub, con));
				no = id;
			}

			else if (command.equals("list")) {
				System.out.println("==게시물 등록==");

				System.out.println("번호 / 제목");

				for (int i = articles.size() - 1; i >= 0; i--) {
					System.out.println(articles.get(i).no + " / " + articles.get(i).sub);
				}
			}

			else if (command.startsWith("list ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);

				System.out.println("==게시물 리스트==");
				if (inputId <= 1) {
					inputId = 1;
				}

				if (articles.size() == 0) {
					System.out.println("저장된 게시물이 없습니다.");
					continue;
				}				

				int page = 10;
				int start = articles.size() - 1;
				start -= (inputId - 1) * page;
				int end = start - (page - 1);

				if (end < 0) {
					end = 0;
				}

				System.out.println("번호 / 제목");

				for (int i = start; i >= end; i--) {
					System.out.println(articles.get(i).no + " / " + articles.get(i).sub);
				}
				if (start < 0) {
					System.out.println(inputId + "페이지는 존재하지 않습니다.");
				}
			}

			else if (command.startsWith("detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);

				System.out.println("==게시물 디테일==");

				if (inputId >= articles.size()) {
					System.out.println(inputId + "번 게시물는 존재 하지 않습니다.");
					continue;
				}

				System.out.println("번호: " + articles.get(inputId - 1).no);
				System.out.println("제목: " + articles.get(inputId - 1).sub);
				System.out.println("내용: " + articles.get(inputId - 1).con);
			}

			else if (command.startsWith("del ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);

				System.out.println("==게시물 삭제==");

				if (inputId >= articles.size()) {
					System.out.println(inputId + "번 게시물는 존재 하지 않습니다.");
					continue;
				}

				articles.remove(inputId - 1);
			}

			else if (command.startsWith("modify ")) {
				int inputId = Integer.parseInt(command.split(" ")[1]);

				System.out.println("==게시물 수정==");

				if (inputId >= articles.size()) {
					System.out.println(inputId + "번 게시물는 존재 하지 않습니다.");
					continue;
				}

				System.out.printf("게시물 번호: " + articles.get(inputId - 1));
				System.out.printf("제목: ");
				String sub = scan.nextLine();
				System.out.printf("내용: ");
				String con = scan.nextLine();

				articles.set(inputId - 1, new Article(inputId - 1, sub, con));
			}

			else if (command.startsWith("search ")) {
				String[] bits = command.split(" ");
				String keyword = bits[1];
				int page = 1;
				if (bits.length >= 3) {
					page = Integer.parseInt(bits[2]);
				}

				if (page <= 1) {
					page = 1;
				}
				System.out.println("==게시물 검색==");

				List<Article> resulArticles = new ArrayList();
			
				for (Article article : articles) {
					if (article.sub.contains(keyword) || article.con.contains(keyword)) {
						resulArticles.add(article);
					}
				}
				
				if (resulArticles.size() == 0 ) {
					System.out.println("검색결과가 존재하지 않습니다.");
					continue;
				}
				int searchPage = 10;
				int start = resulArticles.size() - 1;
				start -= (page - 1) * searchPage;
				int end = start - (searchPage - 1);

				if (end < 0) {
					end = 0;
				}

				System.out.println("번호 / 제목");

				for (int i = start; i >= end; i--) {
					Article article = resulArticles.get(i);
					
					System.out.println(article.no + " / " + article.sub);
				}
				if (start < 0) {
					System.out.println(page + "페이지는 존재하지 않습니다.");
					continue;
				}
			}
			
			else if (command.equals("exit")) {
				break;
			}
			
			else {
				System.out.println("**명령어 오류");
			}
			scan.close();
		}
	}
}
