package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.camp.model.PostDAO;

public class AddHeartAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        String memberId = request.getParameter("memberId");

        PostDAO dao = new PostDAO();
        int result = dao.insertLike(postId, memberId);

        request.setAttribute("result", result > 0 ? "success" : "fail");
        return "jsonView";
	}

}
