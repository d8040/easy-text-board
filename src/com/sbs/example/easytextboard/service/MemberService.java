package com.sbs.example.easytextboard.service;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.easytextboard.dto.Member;

public class MemberService {

	List<Member> members;
	private int memberNo;

	public MemberService() {
		memberNo = 0;
		members = new ArrayList<>();

		for (int i = 1; i < 3; i++) {
			join("user" + i, "user" + i, "유저" + i);
		}
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public int join(String logId, String logPw, String name) {
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

	public boolean isJoinAvailableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	Member member = null;

}
