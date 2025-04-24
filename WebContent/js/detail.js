$(function () {
  const postId = new URLSearchParams(window.location.search).get("postId");

  if (!postId) {
    console.error("❌ postId가 없습니다. URL을 확인하세요.");
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
      $(".profile-img").attr("src", "img/"+data.memberImage);
      
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
    	          <img class="comment-badge" src="img+'${comment.badgeImage}'"></div>
    	          <div class="comment-nickName">${comment.nickName}</div>
    	        </div>
    	      </div>
    	      <div class="comment-box">
    	        <div class="comment-text">${comment.commentContents}</div>
    	        <div class="comment-date">${comment.commentDate}</div>
    	      </div>
    	    </div>
    	  `;
    	  $(".comments").append(commentHtml);
    	});
    },
    error: function () {
      console.error(" 데이터를 불러오는 데 실패했습니다.");
    }
  });
});