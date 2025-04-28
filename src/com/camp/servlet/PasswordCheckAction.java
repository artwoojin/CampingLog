package com.camp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;

public class PasswordCheckAction implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean verified = false;

        if (session != null) {
            MemberVO login = (MemberVO) session.getAttribute("loginUser");
            if (login != null) {
                String pw = request.getParameter("pw");

                MemberVO param = new MemberVO();
                param.setMemberId(login.getMemberId());
                param.setPw(pw);

                MemberDAO dao = new MemberDAO();
                verified = dao.pwCheck(param);
            }
        }

        request.setAttribute("verified", verified);

        return "passwordCheckResult.jsp";
    }
}
