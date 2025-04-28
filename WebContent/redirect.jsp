<%@ page contentType="text/html; charset=UTF-8" %>
<%
  // Action에서 이 속성을 세팅해둔다면
  String target = (String) request.getAttribute("target");
  if (target != null) {
%>
    <!-- 즉시 리다이렉트 -->
    <meta http-equiv="refresh" content="0; url=<%= request.getContextPath() + "/" + target %>" />
<%
  } else {
%>
    <!-- target이 없으면 메인 페이지로 -->
    <meta http-equiv="refresh" content="0; url=<%= request.getContextPath() + "/controller?cmd=mainUI" %>" />
<%
  }
%>
