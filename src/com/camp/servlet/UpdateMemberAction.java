package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;

public class UpdateMemberAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        // 세션에서 로그인한 사용자 정보 가져오기
        HttpSession session = request.getSession();
       
        // 테스트용 세션 주입
        if (session.getAttribute("loginMember") == null) {
            MemberVO testUser = new MemberVO();
            testUser.setMemberId("user01"); // 실제 DB에 존재하는 ID
            session.setAttribute("loginMember", testUser);
        }
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        if (loginMember == null) {
            request.setAttribute("result", "unauthorized");
            return "passwordCheck.html";
        }

        // 수정할 값 파라미터로부터 가져오기
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        // VO 구성
        MemberVO updatedMember = new MemberVO();
        updatedMember.setMemberId(loginMember.getMemberId()); // 로그인 ID 유지
        updatedMember.setEmail(email);
        updatedMember.setNickName(nickname);
        updatedMember.setName(name);
        updatedMember.setPhoneNumber(phoneNumber);

        // DB 업데이트 수행
        MemberDAO dao = new MemberDAO();
        boolean result = dao.updateMemberInfo(updatedMember);

        // 성공 시 세션도 업데이트
        if (result) {
            session.setAttribute("loginMember", updatedMember);
        }

        request.setAttribute("result", result ? "success" : "fail");
        return "updateMember.jsp";
    }
}
