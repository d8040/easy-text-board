package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	List<Member> members = new ArrayList<>();
	private int memberNo = 0;

	private int memberJoin(String loginId, String loginPw, String name) {

		Member member = new Member();

		member.no = memberNo + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;
		members.add(member);

		memberNo = member.no;
		return member.no;
	}

	public void run(Scanner scan, String command) {

		if (command.equals("member join")) {
			System.out.println("== 회원가입 ==");

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsvalid = false;

			while (true) {
				if (loginIdCount >= loginIdMaxCount) {
					System.out.println("다음에 다시 시도해 주세요.");
					break;
					}
				
					System.out.printf("로그인아이디: ");
					String loginId = scan.nextLine().trim();
					System.out.printf("로그인비번: ");
					String loginPw = scan.nextLine();
					System.out.printf("이름: ");
					String name = scan.nextLine();

					int id = memberJoin(loginId, loginPw, name);

					System.out.println(id + "번 회원이 생성되었습니다.");

				}
			}
		}
	}

}
