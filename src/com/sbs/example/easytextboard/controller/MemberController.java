package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	List<Member> members = new ArrayList<>();
	private int memberNo = 0;

	private int join(String logId, String logPw, String name) {
		Member member = new Member();

		member.no = memberNo + 1;

		member.loginId = logId;
		member.loginPw = logPw;
		member.name = name;
		members.add(member);
		memberNo = member.no;

		return member.no;
	}

	private boolean isJoinAvailableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public void run(Scanner scan, String command) {

		if (command.equals("member join")) {
			System.out.println("== 회원가입 ==");

			String loginId = "";
			String loginPw;
			String name;

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsValid = false;

			while (true) {
				if (loginIdMaxCount <= loginIdCount) {
					System.out.println("다음에 다시 시도해 주세요.");
					break;
				}

				System.out.printf("로그인아이디: ");
				loginId = scan.nextLine().trim();

				if (loginId.length() == 0) {
					loginIdCount++;
					continue;
				} else if (isJoinAvailableLoginId(loginId) == false) {
					loginIdCount++;
					System.out.println(loginId + "(은)는 이미 사용중인 아이디 입니다.");
					continue;
				}
				loginIdIsValid = true;
				break;
			}

			if (loginIdIsValid == false) {
				return;
			}
			while (true) {
				System.out.printf("로그인비번: ");
				loginPw = scan.nextLine().trim();

				if (loginPw.length() == 0) {
					continue;
				}
				break;
			}
			while (true) {
				System.out.printf("이름: ");
				name = scan.nextLine().trim();

				if (name.length() == 0) {
					continue;
				}
				break;
			}

			int id = join(loginId, loginPw, name);
		}

	}

}