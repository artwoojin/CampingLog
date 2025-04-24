package com.camp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camp.model.PostVO;
import com.camp.service.PostService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.google.gson.JsonObject;

public class WriteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uploadPath = request.getServletContext().getRealPath("/upload");
        int maxSize = 10 * 1024 * 1024; // 10MB

        MultipartRequest multi = new MultipartRequest(
            request,
            uploadPath,
            maxSize,
            "UTF-8",
            new DefaultFileRenamePolicy()
        );

        String image = multi.getFilesystemName("postImageFile"); // ← HTML input name과 맞춰야 함
        String title = multi.getParameter("postTitle");
        String contents = multi.getParameter("postContents");
        String memberId = "user01"; // 추후 세션으로 변경 예정
        int categoryId = Integer.parseInt(multi.getParameter("categoryId"));
        String[] tagIds = multi.getParameterValues("tagId");

        PostVO vo = new PostVO(title, contents, image, memberId, categoryId);

        PostService service = new PostService();
        boolean result = service.registerPostWithTags(vo, tagIds);
        
        JsonObject json = new JsonObject();
        json.addProperty("result", result ? "success" : "fail");

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json.toString());

        return null;

    }
}
