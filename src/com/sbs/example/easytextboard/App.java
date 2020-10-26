package com.sbs.example.easytextboard;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	Scanner scan = new Scanner(System.in);
	List<Article> list = new ArrayList<Article>();

	private int no;
	private int size;

	public App() {
	
//		articles = new Article[32];
		no = 0;
		size = 0;
		
		for (int i = 0; i < 32; i++) {
			list.add(new Article(list.size(), "제목" + (i + 1), "내용" + (i + 1)));
		}
	}
	public void run() {
		System.out.println(list.get(2).sub);
	}

}


//	}
//
//	private int size() {
//		return size;
//	}
//
//	private Article getArticle(int id) {
//		int index = getIndexId(id);
//
//		if (index == -1) {
//			return null;
//		}
//		return articles[index];
//	}
//
//	private int getIndexId(int id) {
//		for (int i = 0; i < size(); i++) {
//			if (articles[i].no == id) {
//				return i;
//			}
//		}
//		return -1;
//	}
//
//	private int add(String sub, String con) {
//		Article[] newArticles = new Article[articles.length + 5];
//
//		for (int i = 0; i < articles.length; i++) {
//			newArticles[i] = articles[i];
//		}
//
//		articles = newArticles;
//
//		Article article = new Article();
//		article.sub = sub;
//		article.con = con;
//		article.no = no + 1;
//
//		articles[size] = article;
//		size++;
//		no = article.no;
//
//		return article.no;
//	}
//
//	private int listPage(int inputId) {
//		System.out.printf("==%s 페이지==\n", inputId);
//
//		if (inputId <= 1) {
//			inputId = 1;
//		}
//
//		int page = 10;
//		int start = size() - 1;
//		start -= (inputId - 1) * page;
//		int end = start - (page - 1);
//
//		if (end < 0) {
//			end = 0;
//		}
//
//		for (int i = start; i >= end; i--) {
//			Article article = articles[i];
//
//			System.out.println(article.no + " / " + article.sub);
//		}
//		if (start < 0) {
//			System.out.println(inputId + "페이지는 존재하지 않습니다.");
//		}
//		return 0;
//	}
//
//	public void run() {
//
//		while (true) {
//			System.out.printf("명령어) ");
//			String command = scan.nextLine();
//
//			if (command.equals("add")) {
//				System.out.println("==게시물 등록==");
//
//				System.out.printf("제목: ");
//				String sub = scan.nextLine();
//				System.out.printf("제목: ");
//				String con = scan.nextLine();
//
//				int no = add(sub, con);
//				System.out.printf("%d 번 게시물이 생성되었습니다.\n", no);
//			}
//
//			else if (command.startsWith("list ")) {
//				int inputId = Integer.parseInt(command.split(" ")[1]);
//
//				if (size() == 0) {
//					System.out.println(inputId + " 페이지에 게시물이 없습니다.");
//					continue;
//				}
//				System.out.println("번호 / 제목");
//
//				int listNo = listPage(inputId);
//
//			}
//
//			else if (command.equals("list")) {
//				System.out.println("==게시물 리스트==");
//
//				if (size() == 0) {
//					System.out.println("저장된 게시물이 없습니다.");
//					continue;
//				}
//
//				System.out.println("번호 / 제목");
//
//				for (int i = size() - 1; i >= 0; i--) {
//					Article article = articles[i];
//
//					System.out.println(article.no + " / " + article.sub);
//				}
//			}
//
//			else if (command.startsWith("detail ")) {
//				int inputId = 0;
//				try {
//					inputId = Integer.parseInt(command.split(" ")[1]);
//				}
//
//				catch (NumberFormatException a) {
//					System.out.println("게시물 번호를 양의 정수로 입력해 주세요.");
//					continue;
//				}
//
//				System.out.println("==게시물 상세==");
//
//				Article article = getArticle(inputId);
//
//				if (article == null) {
//					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputId);
//					continue;
//				}
//
//				System.out.println("번호: " + article.no);
//				System.out.println("제목: " + article.sub);
//				System.out.println("내용: " + article.con);
//			}
//
//			else if (command.startsWith("modify ")) {
//				int inputId = 0;
//				try {
//					inputId = Integer.parseInt(command.split(" ")[1]);
//				}
//
//				catch (NumberFormatException a) {
//					System.out.println("게시물 번호를 양의 정수로 입력해 주세요.");
//					continue;
//				}
//				System.out.println("==게시물 수정==");
//
//				Article article = getArticle(inputId);
//
//				if (article == null) {
//					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputId);
//				}
//
//				System.out.printf("게시물 번호: %s\n", article.no);
//				System.out.printf("제목: ");
//				String sub = scan.nextLine();
//				System.out.printf("제목: ");
//				String con = scan.nextLine();
//				System.out.printf("%d번 게시물이 수정되었습니다.\n", article.no);
//
//				article.sub = sub;
//				article.con = con;
//			}
//
//			else if (command.startsWith("del ")) {
//				int inputId = 0;
//				try {
//					inputId = Integer.parseInt(command.split(" ")[1]);
//				}
//
//				catch (NumberFormatException a) {
//					System.out.println("게시물 번호를 양의 정수로 입력해 주세요.");
//					continue;
//				}
//				System.out.println(" ==게시물 삭제==");
//
//				if (no <= 0 || inputId > no) {
//					System.out.println(inputId + "번 게시물이 없습니다.");
//					continue;
//				}
//				for (int i = inputId; i < size(); i++) {
//					articles[i - 1] = articles[i];
//
//				}
//				size--;
//
//				System.out.println(inputId + "번 게시물이 삭제되었습니다.");
//			}
//
//			else if (command.startsWith("search ")) {
//				String[] commandBits = command.split(" ");
//				String searchKeyword = commandBits[1];
//				int page = 1;
//				if (commandBits.length >= 3) {
//					page = Integer.parseInt(commandBits[2]);
//				}
//				if (page <= 1) {
//					page = 1;
//				}
//				System.out.println("==게시물 검색==");
//
//				int searchResultAticleLen = 0;
//
//				for (Article article : articles) {
//					if (article == null) {
//						break;
//					}
//
//					if (article.sub.contains(searchKeyword) || article.con.contains(searchKeyword)) {
//						searchResultAticleLen++;
//					}
//				}
//
//				Article[] searchResultArticles = new Article[searchResultAticleLen];
//
//				int searchResultAticlesIndex = 0;
//				for (Article article : articles) {
//					if (article == null) {
//						break;
//					}
//
//					if (article.sub.contains(searchKeyword) || article.con.contains(searchKeyword)) {
//						searchResultArticles[searchResultAticlesIndex] = article;
//						searchResultAticlesIndex++;
//					}
//				}
//				if (searchResultArticles.length == 0) {
//					System.out.println("검색결과가 존재하지 않습니다.");
//					continue;
//				}
//				System.out.println("번호 / 제목");
//
//				int itemsInAPage = 10;
//				int startPos = searchResultArticles.length - 1;
//				startPos -= (page - 1) * itemsInAPage;
//				int endPos = startPos - (itemsInAPage - 1);
//
//				if (endPos < 0) {
//					endPos = 0;
//				}
//
//				if (startPos < 0) {
//					System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
//					continue;
//				}
//
//				for (int i = startPos; i >= endPos; i--) {
//					Article article = searchResultArticles[i];
//
//					System.out.printf("%d / %s\n", article.no, article.sub);
//				}
//			}
//
//			else if (command.equals("exit")) {
//				System.out.println("==프로그램 종료==");
//				break;
//			}
//
//			else {
//				System.out.println("**명령어 오류");
//			}
//		}
//	}
//
//}