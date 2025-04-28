<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.camp.model.MemberVO" %>
<%@ page session="true" %>

<%
  MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
  if (loginUser == null) {
    response.sendRedirect("login.html");
    return;
  } else {
    response.sendRedirect("passwordOkResult.jsp");
    return;
  }
%>
