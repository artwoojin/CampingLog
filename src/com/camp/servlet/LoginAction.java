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
    	System.out.println("�� LoginAction ����");
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        MemberVO vo = new MemberVO();
        vo.setMemberId(id);
        vo.setPw(pw);

        String nickName = new LoginDAO().login(vo);
        System.out.println("[LoginAction] �Ķ���� id=" + id + ", pw=" + pw);
        System.out.println("[LoginAction] DAO ��ȯ nickName=" + nickName);
        boolean loginSuccess = (nickName != null && !nickName.isEmpty());
        request.setAttribute("loginSuccess", loginSuccess);

        return "loginResult.jsp";
    }
}
