package com.camp.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;
import com.google.gson.Gson;

public class SignUpAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

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

     // JSON �쓳�떟 以�鍮�
        HashMap<String, Object> result = new HashMap<>();
        if (isAdded) {
            request.getSession().setAttribute("loginUser", vo);
            result.put("result", "success");
            result.put("nickname", vo.getNickName());
        } else {
            result.put("result", "fail");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = new Gson().toJson(result);
        response.getWriter().write(json);

        return null;
    }
}
