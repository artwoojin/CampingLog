console.log("headerCategory.js 로드됨");

$(document).ready(function () {
  function loadHeaderWithNames() {
    $.ajax({
      url: "controller",
      method: "GET",
      data: { cmd: "getCategoryName" },
      success: function (resp) {
        try {
          if (typeof resp === "string") resp = JSON.parse(resp);
          if (!resp.categoryList || !Array.isArray(resp.categoryList)) {
            console.error("categoryList 형식 오류:", resp);
            return;
          }
          renderHeaderList(resp.categoryList);
        } catch (e) {
          console.error("카테고리 JSON 파싱 실패:", e);
        }
      },
      error: function () {
        console.error("페이지 데이터 로드 실패 (ID+Name)");
      }
    });
  }

  function renderHeaderList(list) {
    const $menu = $(".middleMenu").empty();

    $.each(list, function (i, category) {
      const $link = $(`
        <p>
          <a href="javascript:void(0)" class="category-link" data-category-id="${category.categoryId}">
            ${category.categoryName}
          </a>
        </p>
      `);
      $menu.append($link);
    });

    $(".category-link")
      .off("click")
      .on("click", function () {
        const id = $(this).data("categoryId");
        window.location.href = 'controller?cmd=postUI&categoryId=' + id;
      });
  }

  function loadLoginMemberInfo() {
    // 초기 상태로 미리 설정 (깜빡임 방지)
    $("#loginBox").html(`<span style="color:gray; font-size:12px;">로그인 상태 확인 중...</span>`);

    $.ajax({
      url: "controller",
      method: "GET",
      data: { cmd: "getMyInfo" },
      dataType: "json",
      success: function (data) {
        if (!data || !data.memberId) {
          showLoginUI();
          return;
        }

        const profileImg = data.memberImage && data.memberImage !== "null" ? data.memberImage : "defaultProfile.png";
        const html = `
          <div class="logoutButton">
            <button id="logoutBtn">로그아웃</button>
          </div>
          <div class="profilePhoto">
            <a href="myPage.html">
              <img src="upload/${profileImg}" alt="프로필 이미지" class="profile-img" title="${data.nickName}" />
            </a>
          </div>
        `;

        $("#loginBox").html(html);

        $("#logoutBtn").on("click", function () {
          location.href = "controller?cmd=logout";
        });
      },
      error: function () {
        showLoginUI();
      }
    });

    function showLoginUI() {
      const html = `
        <div class="authButton">
          <button onclick="location.href='signUp.html'">회원가입</button>
        </div>
        <div class="loginButton">
          <button onclick="location.href='login.html'">로그인</button>
        </div>
      `;
      $("#loginBox").html(html);
    }
  }

  // 호출 순서
  loadHeaderWithNames();
  loadLoginMemberInfo();
});
