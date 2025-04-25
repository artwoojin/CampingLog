package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.camp.model.MemberDAO;

public class CheckDuplicateAction implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String field = request.getParameter("field"); // id, email, nickname
        String value = request.getParameter("value");
        
        // 파라미터 유효성 검사
        if (field == null || value == null || value.trim().isEmpty()) {
            request.setAttribute("error", "Invalid parameters");
            return "signUpResult.jsp";
        }

        boolean isDuplicate = false;
        MemberDAO dao = new MemberDAO();

        try {
            switch (field) {
                case "id":
                    isDuplicate = dao.isDuplicateId(value);
                    break;
                case "email":
                    isDuplicate = dao.isDuplicateEmail(value);
                    break;
                case "nickname":
                    isDuplicate = dao.isDuplicateNickName(value);
                    break;
                default:
                    request.setAttribute("error", "Invalid field type");
                    return "signUpResult.jsp";
            }
            
            request.setAttribute("isDuplicate", isDuplicate);
            
        } catch (Exception e) {
            e.printStackTrace(); // 로그에 에러 출력
            request.setAttribute("error", "Database error occurred");
        }
        
        return "signUpResult.jsp";
    }
} 