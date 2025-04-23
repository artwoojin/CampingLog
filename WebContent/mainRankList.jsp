<%@page import="com.camp.model.MemberVO"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	Collection<MemberVO> rankList = (Collection<MemberVO>) request.getAttribute("rankList");
	if (rankList == null) rankList = java.util.Collections.emptyList();
%>
[
  <c:forEach var="m" items="${rankList}" varStatus="status">
    {
      "nickName": "${m.nickName}",
      "badgeImage": "${m.badgeImage}"
    }<c:if test="${!status.last}">,</c:if>
  </c:forEach>
]
