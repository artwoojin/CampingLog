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
        System.out.println(" GetMyInfoAction 吏꾩엯");

        HttpSession session = request.getSession();
        MemberVO loginMember = (MemberVO) session.getAttribute("loginUser"); //  濡쒓렇�씤 �떆 ���옣�븳 key �궗�슜

        if (loginMember == null) {
            System.out.println(" �꽭�뀡�뿉 loginUser �뾾�쓬 �넂 濡쒓렇�씤 �븞 �맂 �긽�깭");
            request.setAttribute("error", "로그인이 필요합니다.");
            return "login.html"; 
        }

        System.out.println(" �꽭�뀡�뿉�꽌 媛��졇�삩 濡쒓렇�씤 ID: " + loginMember.getMemberId());

        // DB�뿉�꽌 理쒖떊 �젙蹂� 媛��졇�삤湲�
        MemberDAO dao = new MemberDAO();
        MemberVO updatedInfo = dao.getMyInfo(loginMember.getMemberId());

        if (updatedInfo == null) {
            System.out.println("DAO�뿉�꽌 �궗�슜�옄 �젙蹂� 媛��졇�삤湲� �떎�뙣");
        } else {
            System.out.println("DAO�뿉�꽌 媛��졇�삩 理쒖떊 �궗�슜�옄 �젙蹂�: " + updatedInfo);
        }

        // 諭껋� �씠誘몄� 怨꾩궛
        int likeCount = updatedInfo.getLikeCount();
        System.out.println(" 醫뗭븘�슂 �닔: " + likeCount);

        String badge;
        if (likeCount >= 100) badge = "vipbadge.png";
        else if (likeCount >= 50) badge = "goldenbadge.png";
        else if (likeCount >= 10) badge = "silverbadge.png";
        else badge = "familybadge.png";

        updatedInfo.setBadgeImage(badge);
        System.out.println(" �쟻�슜�맂 諭껋� �씠誘몄�: " + badge);

        // �꽭�뀡怨� request 理쒖떊�솕
        session.setAttribute("loginUser", updatedInfo); // �떎�떆 理쒖떊 �젙蹂대줈 媛깆떊
        request.setAttribute("memberInfo", updatedInfo);

        System.out.println(" request�� session�뿉 理쒖떊 �궗�슜�옄 �젙蹂� ���옣 �셿猷�");
        return "myPageInfo.jsp"; 
    }
}
