<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="com.camp.model.MemberVO" %>
<%
    MemberVO member = (MemberVO) request.getAttribute("memberInfo");
%>
{
  "memberId": "<%= member.getMemberId() %>",
  "email": "<%= member.getEmail() %>",
  "nickName": "<%= member.getNickName() %>",
  "name": "<%= member.getName() %>",
  "phoneNumber": "<%= member.getPhoneNumber() %>",
  "memberImage": "<%= member.getMemberImage() %>",
  "badgeImage": "<%= member.getBadgeImage() %>",
  "inDate": "<%= member.getInDate() %>",
  "postCount": <%= member.getPostCount() %>,
  "likeCount": <%= member.getLikeCount() %>
}
