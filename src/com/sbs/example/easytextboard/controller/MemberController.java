package com.sbs.example.easytextboard.controller;

import java.util.Scanner;

import com.sbs.example.easytextboard.container.Container;
import com.sbs.example.easytextboard.dto.Member;
import com.sbs.example.easytextboard.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;
	
	public MemberController() {
		memberService = Container.memberService;
	}

	public void run(Scanner scan, String command) {
		if (command.equals("member whoami")) {
			if (Container.session.isLogout()) {
				System.out.println("로그아웃 상태입니다.");
				return;
			}
			int loginedMemberId = Container.session.loginedMemberId;
			System.out.printf("당신의 회원번호는 %d번 입니다.\n", loginedMemberId);
		}

		if (command.equals("member login")) {
			System.out.println("== 로그인 ==");

			if (Container.session.isLogined()) {
				System.out.println("이미 로그인 되었습니다.");
				return;
			}

			String loginId = "";
			String loginPw;

			int loginIdMaxCount = 3;
			int loginIdCount = 0;
			boolean loginIdIsValid = false;
			
			Member member = null;

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
				}
				member = memberService.getMemberByLoginId(loginId);

				if (member == null) {
					loginIdCount++;
					System.out.println(loginId + "(은)는 존재하지 않는 아이디입니다.");
					continue;
				}
				loginIdIsValid = true;
				break;
			}

			if (loginIdIsValid == false) {
				return;
			}
			int loginPwMaxCount = 3;
			int loginPwCount = 0;
			boolean loginPwIsValid = false;

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
			Container.session.loginedMemberId = member.no;
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
				} else if (memberService.isJoinAvailableLoginId(loginId) == false) {
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

			int id = memberService.join(loginId, loginPw, name);

			System.out.println(id + "번 회원이 생성되었습니다.");
		}

	}

}