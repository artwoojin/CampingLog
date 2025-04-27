package com.camp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.camp.model.LoginDAO;
import com.camp.model.MemberVO;

public class LoginAction implements Action {
	@Override
	public String execute(HttpServletRequest request)
			throws ServletException, IOException {
		System.out.println("▶ LoginAction 진입");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setPw(pw);

		MemberVO member = new LoginDAO().login(vo);
		System.out.println("[LoginAction] 파라미터 id=" + id + ", pw=" + pw);
		System.out.println("[LoginAction] DAO 반환 member=" + member);

		boolean loginSuccess = (member != null && member.getNickName() != null && !member.getNickName().isEmpty());

		if (loginSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			System.out.println("[LoginAction] 세션에 사용자 정보 저장 완료");

			// ⭐ 로그인 성공하면 controller?cmd=passwordCheckUI 로 리다이렉트
			return "redirect:controller?cmd=passwordCheck";
		} else {
			return "login.html"; // 로그인 실패 시 로그인 폼으로
		}
	}
}
