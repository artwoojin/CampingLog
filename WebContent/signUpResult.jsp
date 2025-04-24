<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 파라미터 수집
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String email = request.getParameter("email");
    String nickName = request.getParameter("nickName");
    String name = request.getParameter("name");
    String phoneNumber = request.getParameter("phoneNumber");

    // MemberVO 객체 생성
    com.camp.model.MemberVO vo = new com.camp.model.MemberVO();
    vo.setMemberId(id);
    vo.setPw(pw);
    vo.setEmail(email);
    vo.setNickName(nickName);
    vo.setName(name);
    vo.setPhoneNumber(phoneNumber);
    vo.setGradeId(1); // 기본 등급

    // DAO 처리
    com.camp.model.MemberDAO dao = new com.camp.model.MemberDAO();
    boolean isAdded = dao.addMember(vo);

    // 응답 JSON 출력
%>
{
  "result": "<%= isAdded ? "success" : "fail" %>"
}
