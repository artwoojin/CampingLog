package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSessionFactory;

import com.camp.model.DBCP;
import com.camp.service.DetailService;

public class AddBookmarkAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		SqlSessionFactory factory = DBCP.getSqlSessionFactory();
		DetailService service = new DetailService(factory);
		
		String postIdStr = request.getParameter("postId");
		System.out.println("postId �Ķ����: " + postIdStr);
		if (postIdStr == null) {
		    System.out.println("postId�� ���޵��� �ʾҴ�. ��û URL�� Ȯ���ض�.");
		    return "error.jsp"; // ���� ó���ϰų�, �ϴ� ����
		}
		int postId = Integer.parseInt(request.getParameter("postId"));
		String memberId = request.getParameter("memberId");
		System.out.println("AddBookmarkAction postId = " + postId);
		System.out.println("AddBookmarkAction memberId = " + memberId);
		boolean addCheck = service.InsertBookmark(postId, memberId);
		
		request.setAttribute("addCheck", addCheck);
		System.out.println("addbookmarkaction"+request.getAttribute("addCheck"));
		return "addBookmark.jsp";
	}

}
