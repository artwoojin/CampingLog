package com.camp.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.camp.service.PostService;

public class GetPostDetailAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	int postId;
    	if (request.getParameter("postId") != null) {
    	    postId = Integer.parseInt(request.getParameter("postId"));
    	} else {
    	    postId = 45; // 없으면 테스트용 기본값
    	}


        PostService service = new PostService();
        Map<String, Object> detail = service.getPostDetail(postId);

        response.setContentType("application/json; charset=UTF-8");
        String json = new Gson().toJson(detail);
        response.getWriter().write(json);

        return null; 
    }
}
