console.log("📄 detail.js 로드됨");

$(document).ready(function () {
	  const urlParams = new URLSearchParams(window.location.search);
	  const postId = urlParams.get("postId");
	  
  if (!postId) {
    console.error("❌ postId 없음. input[type=hidden] 확인 필요.");
    return;
  }

  $.ajax({
    url: "controller",
    method: "GET",
    data: {
      cmd: "detail",
      postId: postId
    },
    success: function (responseText) {
      const data = typeof responseText === "string" ? JSON.parse(responseText) : responseText;

      // 게시글 본문 정보
      $(".post-title").text(data.postTitle);
      $(".nickName").text(data.nickName);
      $(".post-info div").text(data.postDate);
      $(".post-image img").attr("src", "img/" + data.postImage);
      $(".like-count").text(data.likeCount);
      $(".bookmark-count").text(data.bookmarkCount);
      $(".post-body").text(data.postContents);
      $(".page-title").text(data.categoryName);
      $(".profile-name-badge img").attr("src", "img/" + data.badgeImage);
      $(".profile-img").attr("src", "img/" + data.memberImage);

      // 태그
      let tagHtml = "";
      for (let i = 0; i < data.tagList.length; i++) {
        tagHtml += '<div>#' + data.tagList[i] + '</div>';
      }
      $(".tags").html(tagHtml);

      // 댓글
      let commentHtml = "";
      for (let i = 0; i < data.commentList.length; i++) {
        const c = data.commentList[i];
        commentHtml += ''
          + '<div class="comment">'
          + '  <div class="profile">'
          + '    <img src="img/' + c.memberImage + '" alt="프로필 이미지" class="comment-profile-img">'
          + '    <div class="profile-name-badge">'
          + '      <img class="comment-badge" src="img/' + c.badgeImage + '">'
          + '      <div class="comment-nickName">' + c.nickName + '</div>'
          + '    </div>'
          + '  </div>'
          + '  <div class="comment-box">'
          + '    <div class="comment-text">' + c.commentContents + '</div>'
          + '    <div class="comment-date">' + c.commentDate + '</div>'
          + '  </div>'
          + '</div>';
      }
      $(".pagination").before(commentHtml);
    },
    error: function () {
      console.error("❌ 게시글 상세 데이터를 불러오는 데 실패했습니다.");
    }
  });

  // 좋아요 버튼 기능 (동적 이벤트 위임)
  $(document).on("click", ".like-btn", function () {
    const memberId = "silver99";

    if (!memberId) {
      alert("⚠️ memberId가 설정되지 않았습니다.");
      return;
    }

    $.ajax({
      url: "controller",
      method: "POST",
      data: {
        cmd: "addHeartAction",
        postId: postId,
        memberId: "silver99"
      },
      success: function (responseText) {
        if (responseText.status === "true") {
          alert("❤️ 좋아요 성공");
          const current = parseInt($(".like-count").text()) || 0;
          $(".like-count").text(current + 1);
        } else {
          alert("이미 좋아요를 누르셨거나 오류가 발생했습니다.");
        }
      },
      error: function () {
        alert("서버 통신 중 오류 발생");
      }
    });
  });
});
