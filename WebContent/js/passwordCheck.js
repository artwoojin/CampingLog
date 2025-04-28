$(document).ready(function() {
  // 1. 세션 체크
  $.getJSON("controller?cmd=passwordOkResult", function(user) {
    if (user && user.memberId) {
      $("#memberId").text(user.memberId);
    } else {
      alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
      window.location.href = "login.html";
    }
  }).fail(function() {
    alert("서버 통신 오류가 발생했습니다. 로그인 페이지로 이동합니다.");
    window.location.href = "login.html";
  });

  // 2. 비밀번호 확인
  $("#pw-form").on("submit", function(e) {
    e.preventDefault();
    const pw = $("#password").val().trim();
    if (!pw) {
      alert("비밀번호를 입력해주세요.");
      return;
    }

    $.ajax({
      url: "controller?cmd=passwordCheckAction",
      method: "POST",
      data: { pw: pw },
      dataType: "json",
      success: function(res) {
        if (res.verified) {
          alert("비밀번호 확인 성공! 수정 페이지로 이동합니다.");
          window.location.href = "myPageEdit.html"; // 회원정보 수정 화면으로 이동
        } else {
          alert("비밀번호가 틀렸습니다.");
        }
      },
      error: function() {
        alert("서버 통신 오류가 발생했습니다.");
      }
    });
  });
});
