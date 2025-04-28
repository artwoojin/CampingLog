package com.camp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.camp.model.MemberVO;

public class PasswordOkResultAction implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("[�����] PasswordOkResultAction ���� ����");
        HttpSession session = request.getSession(false);

        if (session == null) {
            System.out.println("[�����] ������ �����ϴ�");
            request.setAttribute("error", "�α��� ���� ����");
            return "passwordOkResult.jsp";
        }

        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            System.out.println("[�����] ���ǿ��� loginUser�� �����ϴ�");
            request.setAttribute("error", "�α��� ���� ����");
            return "passwordOkResult.jsp";
        }

        System.out.println("[�����] loginUser ���ǿ��� ������: " + loginUser.getMemberId());
        request.setAttribute("user", loginUser);
        return "passwordOkResult.jsp";
    }
}
