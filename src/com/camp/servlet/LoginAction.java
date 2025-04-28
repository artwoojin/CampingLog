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

            // 리다이렉트할 URL을 속성으로 세팅
            request.setAttribute("target", "controller?cmd=mainUI");
        } else {
            // 실패 시 로그인 폼으로
            request.setAttribute("target", "login.html");
        }

        // FrontController는 무조건 이 JSP로만 forward
        return "redirect.jsp";
    }
}
