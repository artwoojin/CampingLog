$(document).ready(function () {
  const postId = new URLSearchParams(location.search).get("postId");

  if (!postId) {
    alert("잘못된 접근입니다.");
    location.href = "groundPost.html";
    return;
  }

  $.ajax({
    url: "controller",
    method: "GET",
    data: {
      cmd: "getPostDetail",
      postId: postId
    },
    dataType: "json",
    success: function (data) {
      if (!data || !data.post) {
        alert("게시글 데이터를 불러올 수 없습니다.");
        return;
      }

      const post = data.post;

     
      $("#postId").val(post.postId);
      $("#postTitle").val(post.postTitle);
      $("#postContents").val(post.postContents);
      $("#categoryId").val(post.categoryId);

    
      if (post.postImage) {
        $("#previewImg").attr("src", `upload/${post.postImage}`);
        $("#imageFileNameDisplay").val(post.postImage);

 
        if ($("#originalImageName").length === 0) {
          $("<input>")
            .attr({
              type: "hidden",
              name: "originalImageName",
              id: "originalImageName",
              value: post.postImage
            })
            .appendTo("#editForm");
        } else {
          $("#originalImageName").val(post.postImage);
        }
      }

      
      const allTags = data.allTags;
      const selectedTags = data.selectedTags;

      const $tagContainer = $("#tagContainer").empty();
      const $rowTop = $("<div>").addClass("row row-top");
      const $rowBottom = $("<div>").addClass("row row-bottom");

      allTags.forEach((tag, index) => {
        const $btn = $("<button>")
          .attr("type", "button")
          .addClass("tag")
          .data("tagid", tag.tagId)
          .text(`#${tag.tagName}`);

        if (selectedTags.includes(tag.tagId)) {
          $btn.addClass("selected");

          $("<input>")
            .attr({
              type: "hidden",
              name: "tagId",
              value: tag.tagId,
              class: `hidden-tag-${tag.tagId}`
            })
            .appendTo("#selectedTags");
        }

        (index < 6 ? $rowTop : $rowBottom).append($btn);
      });

      $tagContainer.append($rowTop, $rowBottom);
    },
    error: function () {
      alert("서버 오류로 데이터를 불러올 수 없습니다.");
    }
  });
});
