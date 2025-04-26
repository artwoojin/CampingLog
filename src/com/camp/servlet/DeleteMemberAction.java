package com.camp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;

public class DeleteMemberAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // 기존 세션 체크 (실제 사용 시)
        // MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        // 테스트용 세션 객체 주입
        MemberVO loginMember = new MemberVO();
        loginMember.setMemberId("user01"); // 테스트용 ID
        session.setAttribute("loginMember", loginMember);

        String inputPw = request.getParameter("pw");
        String memberId = loginMember.getMemberId();

        MemberDAO dao = new MemberDAO();
        boolean deleted = dao.deleteMember(memberId, inputPw);

        if (deleted) {
            session.invalidate(); // 세션 제거
            request.setAttribute("result", "success");
        } else {
            request.setAttribute("result", "wrongPassword");
        }

        return "deleteMember.jsp";
    }
}
