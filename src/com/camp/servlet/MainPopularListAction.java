package com.camp.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camp.model.MainUIDAO;
import com.camp.model.PostVO;
import com.google.gson.Gson;

public class MainPopularListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		// 인기상승캠핑조회 홈화면 -> 상위게시글3개조회
	    MainUIDAO dao = new MainUIDAO();
	    Collection<PostVO> list = dao.getPopularCampingList(3);
        request.setAttribute("popularList", list);  // JSTL로 넘어감
        return "mainPopularList.jsp";  // FrontController가 포워딩
	}

}
