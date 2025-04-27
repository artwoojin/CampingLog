$(document).ready(function () {
  $.ajax({
    url: "controller?cmd=getMyInfo",
    method: "GET",
    dataType: "json",
    success: function (data) {
      //  회원 기본 정보 표시
      $(".profile-info").html(`
        <p><strong>아이디</strong> ${data.memberId}</p>
        <p><strong>이메일</strong> ${data.email}</p>
        <p><strong>닉네임</strong> ${data.nickName}</p>
        <p><strong>이름</strong> ${data.name}</p>
        <p><strong>전화번호</strong> ${data.phoneNumber}</p>
      `);

      // 프로필 이미지
      if (data.memberImage) {
        $("#profileImage").attr("src", "upload/" + data.memberImage);
      } else {
        $("#profileImage").attr("src", "img/default.jpg");
      }

      //  1. 뱃지 이미지 표시 (badgeImage는 서버에서 계산된 값)
      $(".summary-icon").attr("src", "img/" + data.badgeImage);

      //  2. 활동 요약 정보 표시
      $(".summary-text").html(`
        <p><strong>내 활동 요약</strong></p>
        <p>작성한 글 : ${data.postCount}개</p>
        <p>받은 좋아요 : ${data.likeCount}개</p>
        <p>가입 일자 : ${data.inDate.split(" ")[0]}</p>
      `);
    },
    error: function () {
      alert("회원 정보를 불러오지 못했습니다.");
    }
  });
});
