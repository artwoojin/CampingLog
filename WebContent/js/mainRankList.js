console.log("RankList.js 로드됨");
$(document).ready(function () {
	$.ajax({
		url: "controller",
		method: "GET",
		data: { cmd: "memberRankListAction" },
		success: function (responseText) {
			const data = typeof responseText === "string" ? JSON.parse(responseText) : responseText;

			let html = "<div class='rankList'><div class='rankDate'><p>2025.04 기준</p></div>";

			$.each(data, function (index, member) {
				html += ''
					+ '<div class="rankCard">'
					+ '  <div class="rankBadge"><img src="img/' + member.badgeImage + '" alt="' + (index + 1) + '등 뱃지"></div>'
					+ '  <div class="rankInfo">'
					+ '    <p class="rankDetail">' + (index + 1) + '등</p>'
					+ '    <p class="rankNickname">' + member.nickName + '</p>'
					+ '  </div>'
					+ '</div>';
			});

			html += "</div>";
			$(".downRank").html(html);
		},
		error: function () {
			console.error("명예의 캠핑로거 데이터 불러오기 실패");
		}
	});
});
