$(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const postId = urlParams.get("postId");
  const memberId = "silver99"; // 세션 연동 안 돼 있으니까 하드코딩

  // 북마크 버튼 클릭
  $(document).on("click", ".like-btn", function () {
    if (!postId || !memberId) {
      alert("⚠️ postId 또는 memberId가 없습니다.");
      return;
    }

    $.ajax({
      url: "controller",
      method: "POST",
      data: {
        cmd: "addHeartAction",
        postId: postId,
        memberId: memberId
      },
      dataType: "json",
      success: function (responseText) {
    	  success: function (responseText) {
    		    console.log("responseText 전체:", responseText);
    		    console.log("typeof responseText:", typeof responseText);
    		    console.log("responseText.status:", responseText.status);
    		    console.log("typeof responseText.status:", typeof responseText.status);
    	  console.log(responseText.status)
        if (responseText.status === true || responseText.status === "true") {
          alert("❤️ 좋아요 성공");
          const current = parseInt($(".like-count").text()) || 0;
          $(".like-count").text(current + 1);
        } else {
          alert("이미 좋아요를 누르셨거나 오류가 발생했습니다.");
        }
      },
      error: function () {
        alert("❌ 서버 통신 중 오류 발생");
      }
    });
  });
});
