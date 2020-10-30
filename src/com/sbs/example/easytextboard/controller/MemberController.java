package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	List<Member> members;
	private int memberNo;

	public MemberController() {
		memberNo = 0;
		members = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			join("user" + (i + 1), "user" + (i + 1), "유저" + i);
		}
	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

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

	private boolean isExistLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}
		return false;
	}

	private boolean isJoinAvailableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	Member member = null;

	public void run(Scanner scan, String command) {

		if (command.equals("member login")) {
			System.out.println("== 로그인 ==");

			String loginId = "";
			String loginPw;

			int loginPwMaxCount = 3;
			int loginPwCount = 0;
			boolean loginPwIsValid = false;

			while (true) {
				if (loginPwMaxCount <= loginPwCount) {
					System.out.println("다음에 다시 시도해 주세요.");
					break;
				}

				System.out.printf("로그인아이디: ");
				loginId = scan.nextLine().trim();

				if (loginId.length() == 0) {
					loginPwCount++;
					continue;
				}
				member = getMemberByLoginId(loginId);

				if (member == null) {
					loginPwCount++;
					System.out.println(loginId + "(은)는 존재하지 않는 아이디입니다.");
					continue;
				}
				loginPwIsValid = true;
				break;
			}

			if (loginPwIsValid == false) {
				return;
			}
			while (true) {
				if (loginPwMaxCount <= loginPwCount) {
					System.out.println("다음에 다시 시도해 주세요.");
					break;
				}
				System.out.printf("로그인비번: ");
				loginPw = scan.nextLine().trim();

				if (loginPw.length() == 0) {
					continue;
				}

				if (member.loginPw.equals(loginPw) == false) {
					loginPwCount++;
					System.out.println("입력된 비밀번호가 일치하지 않습니다.");
					continue;
				}

				loginPwIsValid = true;
				break;
			}
			if (loginPwIsValid == false) {
				return;
			}

			System.out.printf("로그인 되었습니다. %s님 환영합니다.\n", member.name);

		}
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

			System.out.println(id + "번 회원이 생성되었습니다.");
		}

	}

}