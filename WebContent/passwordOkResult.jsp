<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
  <c:when test="${not empty user}">
    {
      "memberId": "<c:out value='${user.memberId}'/>",
      "nickName": "<c:out value='${user.nickName}'/>",
      "email": "<c:out value='${user.email}'/>"
    }
  </c:when>
  <c:otherwise>
    {
      "error": "세션 없음"
    }
  </c:otherwise>
</c:choose>
