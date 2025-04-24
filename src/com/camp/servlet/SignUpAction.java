package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;

public class SignUpAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("[SignUpAction] 회원가입 요청 도착");

	    try {
	        // 파라미터 수집
	        String id = request.getParameter("id");
	        System.out.println("[SignUpAction] ID = " + id); // 필수 디버깅

	        // 예외 확인용: DB 저장 시도
	        MemberVO vo = new MemberVO();
	        vo.setMemberId(id);
	        vo.setPw(request.getParameter("pw"));
	        vo.setEmail(request.getParameter("email"));
	        vo.setNickName(request.getParameter("nickName"));
	        vo.setName(request.getParameter("name"));
	        vo.setPhoneNumber(request.getParameter("phoneNumber"));
	        vo.setGradeId(1);

	        MemberDAO dao = new MemberDAO();
	        boolean isAdded = dao.addMember(vo);

	        System.out.println("[SignUpAction] DB 저장 성공 여부: " + isAdded);

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        if (isAdded) {
	            HttpSession session = request.getSession(true);
	            session.setAttribute("loginUser", vo);
	            response.getWriter().write("{\"result\": \"success\"}");
	        } else {
	            response.getWriter().write("{\"result\": \"fail\"}");
	        }

	    } catch (Exception e) {
	        System.out.println("[SignUpAction] 예외 발생");
	        e.printStackTrace();

	        // JSON 에러 응답도 보냄
	        //response.setContentType("application/json");
	        //response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{\"result\": \"error\", \"message\": \"서버 오류\"}");
	    }

	    return null;
	}

}
