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

        // �α��� ���� �� �׽�Ʈ�� ���� ����
        if (loginMember == null) {
            MemberVO testUser = new MemberVO();
            testUser.setMemberId("user02"); // ���� �����ϴ� ID�� ����
            session.setAttribute("loginMember", testUser);
            loginMember = testUser;
        }

        //  DB���� �ֽ� ���� ��������
        MemberDAO dao = new MemberDAO();
        MemberVO updatedInfo = dao.getMyInfo(loginMember.getMemberId());

        // ���� �̹��� ���
        int likeCount = updatedInfo.getLikeCount();  // �̰� �̹� ���� ����� ���ԵǾ� ����
        String badge;
        if (likeCount >= 100) badge = "vipbadge.png";
        else if (likeCount >= 50) badge = "goldenbadge.png";
        else if (likeCount >= 10) badge = "silverbadge.png";
        else badge = "familybadge.png";

        updatedInfo.setBadgeImage(badge);  //  ���� �̹��� ����

        // �ֽ� ȸ�� ������ ���� �� request ����
        session.setAttribute("loginMember", updatedInfo);
        request.setAttribute("memberInfo", updatedInfo);

        return "myPageInfo.jsp";
    }
}
