$(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const postId = urlParams.get("postId");
  const memberId = "silver99";

  // 북마크 버튼 클릭
  $(document).on("click", ".bookmark-btn", function () {
    if (!postId || !memberId) {
      alert("⚠️ postId 또는 memberId가 없습니다.");
      return;
    }

    $.ajax({
      url: "controller",
      method: "POST",
      data: {
        cmd: "addBookmark",
        postId: postId,
        memberId: memberId
      },
      success: function (responseText) {
    	  console.log(responseText.status)
        if (responseText.status === "true") {
          alert("⭐ 북마크 완료!");
          const current = parseInt($(".bookmark-count").text()) || 0;
          $(".bookmark-count").text(current + 1);
        } else {
          alert("이미 북마크했거나 오류가 발생했습니다.");
        }
      },
      error: function () {
        alert("❌ 서버 통신 중 오류 발생");
      }
    });
  });
});
