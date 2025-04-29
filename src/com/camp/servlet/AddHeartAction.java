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
        System.out.println("addheartAction: "+memberId);
        System.out.println("addheartAction: "+postId);
        PostDAO dao = new PostDAO();
        boolean addCheck = dao.insertLike(postId, memberId);
        System.out.println("addheartaction: "+addCheck);
        request.setAttribute("addCheck", addCheck);
        System.out.println("addHeartaction: "+request.getAttribute("addCheck"));
        return "addHeart.jsp";
	}

}
