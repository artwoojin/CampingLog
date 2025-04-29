<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.camp.model.MemberVO" %>
<%
    // Action에서 이 키로 request.setAttribute() 해 주셔야 합니다
    MemberVO member = (MemberVO) request.getAttribute("memberInfo");
    if (member == null) {
        // 로그인 세션이 없으면 unauthorized JSON 리턴
        out.print("{\"error\":\"unauthorized\"}");
        return;
    }
%>
{
  "memberId":   "<%= member.getMemberId()   %>",
  "email":      "<%= member.getEmail()      %>",
  "nickName":   "<%= member.getNickName()   %>",
  "name":       "<%= member.getName()       %>",
  "phoneNumber":"<%= member.getPhoneNumber()%>",
  "memberImage":"<%= member.getMemberImage() %>",
  "badgeImage": "<%= member.getBadgeImage()  %>",
  "inDate":     "<%= member.getInDate()      %>",
  "postCount":  <%= member.getPostCount()    %>,
  "likeCount":  <%= member.getLikeCount()    %>
}
