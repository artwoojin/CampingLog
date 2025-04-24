function getPostIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get("postId");
}


document.addEventListener("DOMContentLoaded", function () {
  const postId = getPostIdFromURL();
  if (!postId) {
    alert("잘못된 접근입니다.");
    window.location.href = "groundPost.html";
    return;
  }

  fetch(`controller?cmd=getPostDetail&postId=${postId}`)
    .then((res) => res.json())
    .then((data) => {
      if (!data) return alert("게시글 데이터를 불러올 수 없습니다.");

  
      document.getElementById("postId").value = data.postId;
      document.getElementById("postTitle").value = data.postTitle;
      document.getElementById("postContents").value = data.postContents;
      document.getElementById("categoryId").value = data.categoryId;

    
      if (data.postImage) {
        document.getElementById("previewImg").src = `upload/${data.postImage}`;
      }

      const tagContainer = document.getElementById("tagContainer");
      const rowTop = document.createElement("div");
      rowTop.className = "row row-top";
      const rowBottom = document.createElement("div");
      rowBottom.className = "row row-bottom";

      data.allTags.forEach((tag, index) => {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "tag";
        btn.dataset.tagid = tag.tagId;
        btn.textContent = `#${tag.tagName}`;

        if (data.selectedTags.includes(tag.tagId)) {
          btn.classList.add("selected");

          const hidden = document.createElement("input");
          hidden.type = "hidden";
          hidden.name = "tagId";
          hidden.value = tag.tagId;
          hidden.className = `hidden-tag-${tag.tagId}`;
          document.getElementById("selectedTags").appendChild(hidden);
        }

        (index < 6 ? rowTop : rowBottom).appendChild(btn);
      });

      tagContainer.appendChild(rowTop);
      tagContainer.appendChild(rowBottom);
    })
    .catch((err) => {
      console.error(err);
      alert("서버 오류로 데이터를 불러올 수 없습니다.");
    });
});
