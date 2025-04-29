console.log("headerAuthUI.js 로드됨");

$(document).ready(function () {
  function updateAuthUI() {
    $.ajax({
      url: "controller",
      method: "GET",
      data: { cmd: "loginCheck" },
      success: function (data) {
        if (data.loggedIn) {
          console.log(data.loggedIn);
          console.log(data.profileImage);
          $('.authButton, .loginButton').hide();
          // 프로필 이미지 설정
          const src = data.profileImage
            ? `/CampingLog/img/${data.profileImage}`
            : `/CampingLog/img/duck.jpg`;
          $('.userInfo .profile-img')
          	.attr('src', src)
          	.attr('data-memberid', data.memberId);
          $('.userInfo').show();
        } else {
          $('.authButton, .loginButton').show();
          $('.userInfo').hide();
        }
      },
      error: function () {
        console.error("로그인 상태 확인 실패");
      }
    });
  }

  updateAuthUI();

  // ⭐ 프로필 클릭 시 POST 방식으로 myPage.html 이동
  $(document).on('click', '.userInfo .profile-img', function () {
    $.ajax({
      url: "controller",
      method: "GET",
      data: { 
    	  cmd: "getMyInfo", 
    	  memberId: $('.userInfo .profile-img').data('memberid')  
      },
      success: function (data) {
    	if (Array.isArray(data) && data.length > 0 && data[0].memberId) {
    	  const member = data[0];
        	
        	// form 생성
          const form = document.createElement("form");
          form.method = "GET";
          form.action = "/CampingLog/myPage.html";
          form.style.display = "none";

          // hidden input으로 memberId 전달
          const input = document.createElement("input");
          input.type = "hidden";
          input.name = "memberId";
          input.value = member.memberId;
          form.appendChild(input);

          document.body.appendChild(form);
          form.submit();
        } else {
          alert('회원 정보를 불러올 수 없습니다.');
        }
      },
      error: function () {
        alert('서버 오류로 이동할 수 없습니다.');
      }
    });
  });

  // 로그아웃 버튼 클릭 이벤트
  $(document).on('click', '#logoutBtn', function () {
    $.ajax({
      url: "controller",
      method: "GET",
      data: { cmd: "logout" },
      success: function () {
        console.log("로그아웃 성공");
        location.reload();
      },
      error: function () {
        alert("로그아웃 실패");
      }
    });
  });
});
