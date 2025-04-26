<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String result = (String) request.getAttribute("result");

if (result == null) {
    result = "fail"; // 혹시라도 null이면 fail 처리
}

String json = "{\"result\":\"" + result + "\"}";
out.print(json);
%>