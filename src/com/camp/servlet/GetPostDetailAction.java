package com.camp.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.camp.service.PostService;

public class GetPostDetailAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        int postId;
        if (request.getParameter("postId") != null) {
            postId = Integer.parseInt(request.getParameter("postId"));
        } else {
            postId = 45; // 테스트용 기본값
        }

        PostService service = new PostService();
        Map<String, Object> detail = service.getPostDetail(postId);

        request.setAttribute("result", detail);
        return "getPostDetail.jsp"; 
    }
}
