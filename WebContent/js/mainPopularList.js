console.log("popularPost.js 로드됨");
$(document).ready(function () {
	$.ajax({
		url: "controller",
		method: "GET",
		data: { cmd: "mainPopularListAction" },
		success: function (responseText) {
			const data = typeof responseText === "string" ? JSON.parse(responseText) : responseText;

			if (data.length >= 1) {
				const post = data[0];
				let html = ''
					+ '<div class="topCard">'
					+ '  <div class="topImg1"><img src="img/' + post.postImage + '" alt="1위"></div>'
					+ '  <div class="campTitle"><p>' + post.postTitle + '</p></div>'
					+ '  <div class="campData">'
					+ '    <div class="campUser"><p>' + post.nickName + '</p></div>'
					+ '    <div class="loveDate"><p>좋아요</p><p>' + post.likeCount + '</p><p>' + post.postDate + '</p></div>'
					+ '  </div>'
					+ '</div>';
				$(".topLeft").html(html);
			}

			let htmlRight = "";
			for (let i = 1; i < data.length; i++) {
				const post = data[i];
				htmlRight += ''
					+ '<div class="topCard">'
					+ '  <div class="topImg2"><img src="img/' + post.postImage + '" alt="인기"></div>'
					+ '  <div class="campTitle"><p>' + post.postTitle + '</p></div>'
					+ '  <div class="campData">'
					+ '    <div class="campUser"><p>' + post.nickName + '</p></div>'
					+ '    <div class="loveDate"><p>좋아요</p><p>' + post.likeCount + '</p><p>' + post.postDate + '</p></div>'
					+ '  </div>'
					+ '</div>';
			}
			$(".topRight").html(htmlRight);
		},
		error: function () {
			console.error("인기 게시글 불러오기 실패");
		}
	});
});