document.addEventListener("DOMContentLoaded", function () {
  const deleteBtn = document.getElementById("delete-btn");
  const postId = new URLSearchParams(location.search).get("postId");

  deleteBtn.addEventListener("click", function () {
    if (confirm("정말 삭제하시겠습니까?")) {
   
      fetch(`controller?cmd=deletePostAction&postId=${postId}`, {
        method: "POST"
      })
        .then((res) => res.json())
        .then((data) => {
          if (data.result === "success") {
            alert("삭제가 완료되었습니다.");
            window.location.href = "mainUI.html";
          } else {
            alert("삭제 실패");
          }
        })
        .catch(() => alert("서버 오류"));
    }
  
  });
});
