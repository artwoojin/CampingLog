package com.camp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.camp.model.MemberVO;

public class PasswordOkResultAction implements Action {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("[디버그] PasswordOkResultAction 실행 시작");
        HttpSession session = request.getSession(false);

        if (session == null) {
            System.out.println("[디버그] 세션이 없습니다");
            request.setAttribute("error", "로그인 정보 없음");
            return "passwordOkResult.jsp";
        }

        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            System.out.println("[디버그] 세션에는 loginUser가 없습니다");
            request.setAttribute("error", "로그인 정보 없음");
            return "passwordOkResult.jsp";
        }

        System.out.println("[디버그] loginUser 세션에서 가져옴: " + loginUser.getMemberId());
        request.setAttribute("user", loginUser);
        return "passwordOkResult.jsp";
    }
}
