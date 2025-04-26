package com.camp.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.camp.model.MainUIDAO;
import com.camp.model.MemberVO;

public class YesterdayRankListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
        MainUIDAO dao = new MainUIDAO();

        // 어제 날짜를 구해서 (yyyy-MM-dd 형식)
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String date = yesterday.format(DateTimeFormatter.ISO_DATE);
        System.out.println("어제 날짜: " + date);
        // 어제 날짜 기준으로 랭킹 조회
        Collection<MemberVO> rankList = dao.getYesterdayTopMembersByLikes(date);
        
        request.setAttribute("rankList", rankList);
        request.setAttribute("rankDate", date); // 기준일도 같이 넘기기
        return "mainRankList.jsp";
	}

}
