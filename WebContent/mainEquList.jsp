<%@page import="com.camp.model.PostVO"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Collection<PostVO> equList = (Collection<PostVO>) request.getAttribute("equList");
    if (equList == null) equList = java.util.Collections.emptyList();
%>
[
<c:forEach var="post" items="${equList}" varStatus="status">
  {
    "postId": "<c:out value='${post.postId}'/>",
    "postTitle": "<c:out value='${post.postTitle}'/>",
    "postImage": "<c:out value='${post.postImage}'/>",
    "postDate": "<c:out value='${post.postDate}'/>",
    "nickName": "<c:out value='${post.nickName}'/>",
    "likeCount": <c:out value='${post.likeCount}'/>
  }<c:if test="${!status.last}">,</c:if>
</c:forEach>
]
