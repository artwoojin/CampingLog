<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
  <%-- 중복 확인 응답 --%>
  <c:when test="${isDuplicate ne null}">
    { "result": <c:out value="${not isDuplicate}" /> }
  </c:when>

  <%-- 회원가입 응답 --%>
  <c:otherwise>
    {
      "result": "<c:out value='${result}'/>",
      "nickname": "<c:out value='${nickname}'/>"
    }
  </c:otherwise>
</c:choose>
 