package com.sbs.example.easytextboard;

import java.util.Calendar;
import java.util.Scanner;

public class Exam {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		캐릭터[] 캐릭터들 = new 캐릭터[n];

		for (int i = 0; i < n; i++) {
			String 이름 = sc.next();
			int 태어난해 = Integer.parseInt(sc.next().replace("년", ""));
			String 직업 = sc.next();

			캐릭터 a캐릭터 = null;

			if (직업.equals("의적")) {
				a캐릭터 = new 의적();
			}
			else if (직업.equals("도적")) {
				a캐릭터 = new 도적();
			}
			else if (직업.equals("상인")) {
				a캐릭터 = new 상인();
			}

			a캐릭터.이름 = 이름;
			a캐릭터.번호 = i + 1;
			a캐릭터.태어난해 = 태어난해;
		}
	}
}

class 의적 extends 캐릭터 {
	의적() {
		직업 = "의적";
	}
}
class 도적 extends 캐릭터 {
	도적() {
		직업 = "도적";
	}
}
class 상인 extends 캐릭터 {
	상인() {
		직업 = "상인";
	}
}
class 캐릭터 {
	int 번호;
	String 이름;
	String 직업;
	int 태어난해;
	int 나이;
	int 힘;
	int 지능;
	int 민첩;
	
	int get나이() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year - 태어난해;
	}
	void 자기소개() {
		System.out.println("== 자기소개 시작 ==");
		System.out.println("번호: " + 번호);
		System.out.println("이름: " + 이름);
		System.out.println("캐릭터 종류: " + 직업);
		System.out.println("태어난 해: " + 태어난해);
		System.out.println("나이: " + get나이());
		System.out.println("힘: " + 힘);
		System.out.println("지능: " + 지능);
		System.out.println("민첩: " + 민첩);
		System.out.println("== 자기소개 끝 ==");
	}
	void 공격() {
	System.out.println("== 공격 시작 ==");
	System.out.println("== 공격 끝 ==");
	}
}