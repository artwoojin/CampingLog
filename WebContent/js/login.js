$(document).ready(function() {
  $("#login-form").on("submit", function(e) {
    e.preventDefault(); // 폼 기본 제출 막기

    $.ajax({
      url: "controller?cmd=login", // loginAction 호출
      type: "POST",
      data: $(this).serialize(),
      dataType: "json",
      success: function(res) {
        console.log("[디버깅] 서버 응답:", res); // 응답 확인용

        if (res.loginSuccess === true) {
          alert("로그인 성공했습니다! 메인 페이지로 이동합니다.");
          window.location.href = "mainUI.html"; 
        } else {
          alert("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
          window.location.href = "login.html"; 
        }
      },
      error: function(xhr, status, error) {
        console.error("[서버 통신 오류]", status, error);
        alert("서버 통신 오류가 발생했습니다.");
      }
    });
  });
});

