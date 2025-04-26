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
        // ���ǿ��� �α����� ����� ���� ��������
        HttpSession session = request.getSession();
       
        // �׽�Ʈ�� ���� ����
        if (session.getAttribute("loginMember") == null) {
            MemberVO testUser = new MemberVO();
            testUser.setMemberId("user01"); // ���� DB�� �����ϴ� ID
            session.setAttribute("loginMember", testUser);
        }
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        if (loginMember == null) {
            request.setAttribute("result", "unauthorized");
            return "passwordCheck.html";
        }

        // ������ �� �Ķ���ͷκ��� ��������
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        // VO ����
        MemberVO updatedMember = new MemberVO();
        updatedMember.setMemberId(loginMember.getMemberId()); // �α��� ID ����
        updatedMember.setEmail(email);
        updatedMember.setNickName(nickname);
        updatedMember.setName(name);
        updatedMember.setPhoneNumber(phoneNumber);

        // DB ������Ʈ ����
        MemberDAO dao = new MemberDAO();
        boolean result = dao.updateMemberInfo(updatedMember);

        // ���� �� ���ǵ� ������Ʈ
        if (result) {
            session.setAttribute("loginMember", updatedMember);
        }

        request.setAttribute("result", result ? "success" : "fail");
        return "updateMember.jsp";
    }
}
