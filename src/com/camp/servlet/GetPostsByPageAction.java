package com.camp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.camp.model.PostDAO;
import com.camp.model.PostVO;
import com.google.gson.Gson;

public class GetPostsByPageAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// 파라미터 받기
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = 4;

        int start = (page - 1) * pageSize + 1;
        int end = page * pageSize;
        
        System.out.println(start + " " + end);

        PostDAO dao = new PostDAO();
        List<PostVO> posts = dao.getPostPage(start, end);
        int totalPosts = dao.getTotalPostCount();
        int totalPages = (int) Math.ceil(totalPosts / (double) pageSize);
        
        System.out.println(posts);
        System.out.println(totalPosts);
        System.out.println(totalPages);

        Map<String, Object> result = new HashMap<>();
        result.put("postList", posts); // postList로 key명 변경
        result.put("totalPostCount", totalPosts); // totalPostCount로 key명 변경
        result.put("currentPage", page); // currentPage 추가

        // JSON 데이터 문자열로 만들어서 request에 넣어둠
        request.setAttribute("jsonData", new Gson().toJson(result));

        return "json";
    }


}