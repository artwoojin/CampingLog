$(document).ready(function () {
  $("#updateForm").on("submit", function (e) {
    e.preventDefault();

    const formData = $(this).serialize(); // name 속성 기준으로 자동 직렬화

    $.ajax({
      url: "controller?cmd=updateMemberAction",
      method: "POST",
      data: formData,
      dataType: "json",
      success: function (data) {
        console.log("서버 응답:", data);
        if (data.result === "success") {
          alert("회원 정보가 성공적으로 수정되었습니다.");
          window.location.href = "myPage.html"; // 마이페이지로 이동
        } else if (data.result === "unauthorized") {
          alert("로그인 정보가 없습니다. 다시 로그인 해주세요.");
          window.location.href = "login.html";
        } else {
          alert("회원 정보 수정에 실패했습니다.");
        }
      },
      error: function () {
        alert("서버와 통신 중 오류가 발생했습니다.");
      }
    });
  });
});
