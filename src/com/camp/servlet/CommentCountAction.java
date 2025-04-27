package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSessionFactory;

import com.camp.model.DBCP;
import com.camp.service.DetailService;

public class CommentCountAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		SqlSessionFactory factory = DBCP.getSqlSessionFactory();
		DetailService service = new DetailService(factory);
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		int commentCount = service.getCommentCount(postId);
		
		request.setAttribute("commentCount", commentCount);
		System.out.println(commentCount);
		return null;
	}

}
