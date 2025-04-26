  $(function () {
    $("#comment-btn").click(function () {
      const commentContents = $("#comment-input").val();
      const postId = new URLSearchParams(window.location.search).get("postId");
      const memberId = "silver99"; // 실제 로그인 연동 시 세션  가져오기

      if (!commentContents.trim()) {
        alert("댓글을 입력해주세요.");
        return;
      }

      $.ajax({
        url: `/CampingLog/controller?cmd=addComment`,
        method: "POST",
        data: {
          postId: postId,
          memberId: memberId,
          commentContents: commentContents
        },
        success: function (data) {
          alert("댓글이 등록되었습니다.");
          location.reload(); // 댓글 등록 후 페이지 새로고침
        },
        error: function () {
          alert("댓글 등록 실패");
        }
      });
    });
  });