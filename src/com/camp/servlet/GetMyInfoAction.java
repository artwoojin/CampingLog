package com.camp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.camp.model.MemberDAO;
import com.camp.model.MemberVO;

public class GetMyInfoAction implements Action {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        // 로그인 구현 전 테스트용 세션 설정
        if (loginMember == null) {
            MemberVO testUser = new MemberVO();
            testUser.setMemberId("user02"); // 실제 존재하는 ID로 설정
            session.setAttribute("loginMember", testUser);
            loginMember = testUser;
        }

        //  DB에서 최신 정보 가져오기
        MemberDAO dao = new MemberDAO();
        MemberVO updatedInfo = dao.getMyInfo(loginMember.getMemberId());

        // 뱃지 이미지 계산
        int likeCount = updatedInfo.getLikeCount();  // 이게 이미 쿼리 결과에 포함되어 있음
        String badge;
        if (likeCount >= 100) badge = "vipbadge.png";
        else if (likeCount >= 50) badge = "goldenbadge.png";
        else if (likeCount >= 10) badge = "silverbadge.png";
        else badge = "familybadge.png";

        updatedInfo.setBadgeImage(badge);  //  뱃지 이미지 세팅

        // 최신 회원 정보로 세션 및 request 갱신
        session.setAttribute("loginMember", updatedInfo);
        request.setAttribute("memberInfo", updatedInfo);

        return "myPageInfo.jsp";
    }
}
