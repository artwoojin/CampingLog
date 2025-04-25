package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;

public class SignUpAction implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String email = request.getParameter("email");
        String nickName = request.getParameter("nickName");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        MemberVO vo = new MemberVO();
        vo.setMemberId(id);
        vo.setPw(pw);
        vo.setEmail(email);
        vo.setNickName(nickName);
        vo.setName(name);
        vo.setPhoneNumber(phoneNumber);
        vo.setGradeId(1);

        MemberDAO dao = new MemberDAO();
        boolean isAdded = dao.addMember(vo);

        if (isAdded) {
            request.getSession().setAttribute("loginUser", vo);
        }

        // 결과를 JSP에서 JSON으로 응답하기 위해 request에 저장
        request.setAttribute("result", isAdded ? "success" : "fail");
        request.setAttribute("nickname", vo.getNickName());

        return "signUpResult.jsp";
    }
}
