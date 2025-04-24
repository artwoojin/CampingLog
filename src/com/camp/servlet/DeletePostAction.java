package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.camp.service.PostService;

public class DeletePostAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int postId = Integer.parseInt(request.getParameter("postId"));
        PostService service = new PostService();
        boolean result = service.deletePostWithTags(postId);

        response.setContentType("application/json; charset=UTF-8");
        JsonObject json = new JsonObject();
        json.addProperty("result", result ? "success" : "fail");
        response.getWriter().write(json.toString());

        return null;
    }
}

