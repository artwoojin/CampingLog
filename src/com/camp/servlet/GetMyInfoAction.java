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
        System.out.println(" GetMyInfoAction 진입");

        HttpSession session = request.getSession();
        MemberVO loginMember = (MemberVO) session.getAttribute("loginUser"); //  로그인 시 저장한 key 사용

        if (loginMember == null) {
            System.out.println(" 세션에 loginUser 없음 → 로그인 안 된 상태");
            request.setAttribute("error", "로그인이 필요합니다.");
            return "login.html"; 
        }

        System.out.println(" 세션에서 가져온 로그인 ID: " + loginMember.getMemberId());

        // DB에서 최신 정보 가져오기
        MemberDAO dao = new MemberDAO();
        MemberVO updatedInfo = dao.getMyInfo(loginMember.getMemberId());

        if (updatedInfo == null) {
            System.out.println("DAO에서 사용자 정보 가져오기 실패");
        } else {
            System.out.println("DAO에서 가져온 최신 사용자 정보: " + updatedInfo);
        }

        // 뱃지 이미지 계산
        int likeCount = updatedInfo.getLikeCount();
        System.out.println(" 좋아요 수: " + likeCount);

        String badge;
        if (likeCount >= 100) badge = "vipbadge.png";
        else if (likeCount >= 50) badge = "goldenbadge.png";
        else if (likeCount >= 10) badge = "silverbadge.png";
        else badge = "familybadge.png";

        updatedInfo.setBadgeImage(badge);
        System.out.println(" 적용된 뱃지 이미지: " + badge);

        // 세션과 request 최신화
        session.setAttribute("loginUser", updatedInfo); // 다시 최신 정보로 갱신
        request.setAttribute("memberInfo", updatedInfo);

        System.out.println(" request와 session에 최신 사용자 정보 저장 완료");
        return "myPageInfo.jsp"; 
    }
}
