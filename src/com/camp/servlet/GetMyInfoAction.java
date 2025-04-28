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
        System.out.println(" GetMyInfoAction ����");

        HttpSession session = request.getSession();
        MemberVO loginMember = (MemberVO) session.getAttribute("loginUser"); //  �α��� �� ������ key ���

        if (loginMember == null) {
            System.out.println(" ���ǿ� loginUser ���� �� �α��� �� �� ����");
            request.setAttribute("error", "�α����� �ʿ��մϴ�.");
            return "login.html"; 
        }

        System.out.println(" ���ǿ��� ������ �α��� ID: " + loginMember.getMemberId());

        // DB���� �ֽ� ���� ��������
        MemberDAO dao = new MemberDAO();
        MemberVO updatedInfo = dao.getMyInfo(loginMember.getMemberId());

        if (updatedInfo == null) {
            System.out.println("DAO���� ����� ���� �������� ����");
        } else {
            System.out.println("DAO���� ������ �ֽ� ����� ����: " + updatedInfo);
        }

        // ���� �̹��� ���
        int likeCount = updatedInfo.getLikeCount();
        System.out.println(" ���ƿ� ��: " + likeCount);

        String badge;
        if (likeCount >= 100) badge = "vipbadge.png";
        else if (likeCount >= 50) badge = "goldenbadge.png";
        else if (likeCount >= 10) badge = "silverbadge.png";
        else badge = "familybadge.png";

        updatedInfo.setBadgeImage(badge);
        System.out.println(" ����� ���� �̹���: " + badge);

        // ���ǰ� request �ֽ�ȭ
        session.setAttribute("loginUser", updatedInfo); // �ٽ� �ֽ� ������ ����
        request.setAttribute("memberInfo", updatedInfo);

        System.out.println(" request�� session�� �ֽ� ����� ���� ���� �Ϸ�");
        return "myPageInfo.jsp"; 
    }
}
