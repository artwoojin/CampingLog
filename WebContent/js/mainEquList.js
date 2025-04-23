console.log("mainEquList.js 로드됨");

$(document).ready(function () {
	$.ajax({
		url: "controller",
		method: "GET",
		data: { cmd: "mainEquListAction" },
		success: function (responseText) {
			const data = typeof responseText === "string" ? JSON.parse(responseText) : responseText;

			let html = "";
			for (let i = 0; i < data.length; i++) {
				const post = data[i];
				html += ''
					+ '<div class="downCard">'
					+ '  <div class="downtop">'
					+ '    <div class="campTitle"><p>' + post.postTitle + '</p></div>'
					+ '    <div class="downImg"><img src="img/' + post.postImage + '" alt="장비리뷰' + (i + 1) + '"></div>'
					+ '  </div>'
					+ '  <div class="campData">'
					+ '    <div class="campUser"><p>' + post.nickName + '</p></div>'
					+ '    <div class="loveDate"><p>좋아요</p><p>' + post.likeCount + '</p><p>' + post.postDate + '</p></div>'
					+ '  </div>'
					+ '</div>';
			}
			$(".downList").html(html);
		},
		error: function () {
			console.error("장비 리뷰 불러오기 실패");
		}
	});
});
