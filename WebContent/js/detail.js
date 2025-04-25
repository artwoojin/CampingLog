$(function () {
  // ✅ input[type=hidden]에서 postId 값 가져오기
  const postId = document.getElementById("postId")?.value;

  if (!postId) {
    console.error("❌ postId가 없습니다. input 태그를 확인하세요.");
    return;
  }

  $.ajax({
    url: `/CampingLog/controller?cmd=detail&postId=${postId}`,
    method: "GET",
    dataType: "json",
    success: function (data) {
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

      let tagHtml = "";
      data.tagList.forEach(tag => {
        tagHtml += `<div>#${tag}</div>`;
      });
      $(".tags").html(tagHtml);

      data.commentList.forEach(comment => {
        const commentHtml = `
          <div class="comment">
            <div class="profile">
              <img src="img/${comment.memberImage}" alt="프로필 이미지" class="comment-profile-img">
              <div class="profile-name-badge">
                <img src="img/${comment.badgeImage}" class="comment-badge">
                <div class="comment-nickName">${comment.nickName}</div>
              </div>
            </div>
            <div class="comment-box">
              <div class="comment-text">${comment.commentContents}</div>
              <div class="comment-date">${comment.commentDate}</div>
            </div>
          </div>
        `;
        $(".pagination").before(commentHtml);
      });

      // ✅ 좋아요 버튼 기능도 같이 적용 (postId와 memberId 필요)
      $(".like-btn").on("click", function () {
        const memberId = $(this).data("memberid"); // 테스트용 memberId

        $.ajax({
          url: "/CampingLog/controller?cmd=addHeartAction",
          method: "POST",
          data: {
            postId: postId,
            memberId: memberId
          },
          success: function (responseText) {
            const res = typeof responseText === "string" ? JSON.parse(responseText) : responseText;
            if (res.result === "success") {
              alert("좋아요 성공!");
              const current = parseInt($(".like-count").text());
              $(".like-count").text(current + 1);
            } else {
              alert("이미 좋아요를 누르셨거나 오류가 발생했습니다.");
            }
          },
          error: function () {
            alert("서버 오류 발생");
          }
        });
      });
    },
    error: function () {
      console.error(" 데이터를 불러오는 데 실패했습니다.");
    }
  });
});
