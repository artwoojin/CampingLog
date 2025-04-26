$(document).ready(function () {
  $("#deleteForm").on("submit", function (e) {
    e.preventDefault(); 

    const pw = $("#pw").val().trim();
    if (!pw) {
      alert("비밀번호를 입력해주세요.");
      return;
    }

    $.ajax({
      url: "controller?cmd=deleteMemberAction",
      method: "POST",
      data: { pw: pw },
      dataType: "json",
      success: function (data) {
        if (data.result === "success") {
          alert("탈퇴가 완료되었습니다.");
          window.location.href = "mainUI.html"; // 또는 로그인 페이지
        } else if (data.result === "wrongPassword") {
          alert("비밀번호가 일치하지 않습니다.");
        } else {
          alert("탈퇴 처리 중 오류가 발생했습니다.");
        }
      },
      error: function () {
        alert("서버와의 통신에 실패했습니다.");
      }
    });
  });
});
