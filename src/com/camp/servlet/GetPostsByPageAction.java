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
		// �Ķ���� �ޱ�
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
        result.put("postList", posts); // postList�� key�� ����
        result.put("totalPostCount", totalPosts); // totalPostCount�� key�� ����
        result.put("currentPage", page); // currentPage �߰�

        // JSON ������ ���ڿ��� ���� request�� �־��
        request.setAttribute("jsonData", new Gson().toJson(result));

        return "json";
    }


}