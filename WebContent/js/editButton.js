function getPostIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("postId");
}

document.addEventListener("DOMContentLoaded", function () {
  const editBtn = document.getElementById("edit-btn");
  //const postId = getPostIdFromURL();
  const postId = document.getElementById("postId").value;

  if (editBtn && postId) {
    editBtn.addEventListener("click", function () {
      window.location.href = `postEdit.html?postId=${postId}`;
    });
  }
});
