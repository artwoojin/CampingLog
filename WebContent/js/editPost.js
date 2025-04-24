$("#editForm").on("submit", function (e) {
  e.preventDefault();
  const formData = new FormData(this);

  $.ajax({
    url: "controller?cmd=updatePostAction",
    type: "POST",
    data: formData,
    processData: false,
    contentType: false,
    success: function (res) {
      if (res.result === "success") {
        alert("수정 완료!");
        window.location.href = `detailPost.html?postId=${formData.get("postId")}`;
      } else {
        alert("수정 실패");
      }
    },
    error: function () {
      alert("서버 오류");
    }
  });
});
