<%@ page contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${verified}">
{"verified": true}
  </c:when>
	<c:otherwise>
{"verified": false}
  </c:otherwise>
</c:choose>
