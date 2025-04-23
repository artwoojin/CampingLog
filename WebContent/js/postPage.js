console.log("postPage.js 로드됨");
$(document).ready(function () {
	// 한 페이지당 보여줄 게시물 수, 한 블록당 페이지 버튼 수
	var pageSize = 4;
	var blockSize = 5;
	var totalCount = 0; // 백엔드에서 내려줄 전체 게시물 수

	// 1) 지정한 페이지 번호로 게시물 + pagination 불러오기
	function loadPage(page) {
		$.ajax({
			url: "controller",
			method: "GET",
			data: {
				cmd: "getPostsByPage",
				page: page
			},
			success: function (resp) {
				// 문자열이면 JSON 파싱
				if (typeof resp === "string") {
					resp = JSON.parse(resp);
				}
				totalCount = resp.totalPostCount;     // 서버에서 오는 총 게시물 수
				renderPostList(resp.postList);           // 서버에서 오는 게시물 목록
				renderPagination(resp.currentPage, totalCount, pageSize, blockSize); //  현재 페이지
				
				console.log(totalCount);
				console.log(renderPostList);
			},
			error: function () {
				console.error("페이지 데이터 로드 실패");
			}
		});
	}

	// 2) 게시물 렌더링
	function renderPostList(list) {
		var $list = $(".postCardList");
		$list.empty();
		$.each(list, function (i, post) {

			var postDate = new Date(post.postDate);
			var formattedDate = postDate.toISOString().split('T')[0];  // '2025-04-21' 형태로 변환
//			2025-04-21 (T15:04:03.000Z)<< 삭제인거임
			var postImage = post.postImage && post.postImage !== "null" ? post.postImage : 'img/postImg1.png';
			
			var html = ''
				+ '<div class="postCard">'
				+ '  <div class="postImg"><img src="' + postImage + '" class="postImg"></div>'
				+ '  <div class="postContent">'
				+ '    <div class="postType">' + post.categoryName + '</div>'
				+ '    <div class="postTitle">' + post.postTitle  + '</div>'
				+ '    <div class="postText">' + post.postContents  + '</div>'
				+ '  </div>'
				+ '  <div class="postMeta">'
				+ '    <div class="up"><div class="dateData"><p>' + formattedDate  + '</p></div></div>'
				+ '    <div class="postStats">'
				+ '      <div class="left"><div class="comment"><p>댓글</p></div><div class="commentData"><p>' + post.commentCount + '</p></div></div>'
				+ '      <div class="middle"><div class="great"><i class="fa-solid fa-fire"></i></div><div class="greatData"><p>' + post.likeCount + '</p></div></div>'
				+ '    </div>'
				+ '  </div>'
				+ '</div>';
			$list.append(html);
		});
	}

	// 3) pagination 버튼 렌더링
	function renderPagination(currentPage, totalCount, pageSize, blockSize) {
//		Math.ceil()은 소수점 이하가 있는 숫자를 올림하여 정수로 반환합니다. 예를 들어, Math.ceil(4.3)은 5를 반환합니다.
//		반대로, Math.floor()는 소수점 이하를 내림하여 가장 큰 정수를 반환합니다. 예를 들어, Math.floor(4.7)은 4를 반환합니다.
//		Math.round()는 소수점 이하를 반올림하여 가장 가까운 정수를 반환합니다. 예를 들어, Math.round(4.5)는 5를 반환합니다.	
		var totalPages = Math.ceil(totalCount / pageSize);
		var currentBlock = Math.floor((currentPage - 1) / blockSize);
		var startPage = currentBlock * blockSize + 1;
		var endPage = Math.min(startPage + blockSize - 1, totalPages);

		var $pg = $(".pagination");
		$pg.empty();

		// << 버튼
		if (currentPage > 1) {
			$pg.append('<button class="first-page">&laquo;</button>');
		}
		// < 버튼
		if (startPage > 1) {
			$pg.append('<button class="prev-page">&lt;</button>');
		}
		// 숫자 버튼들
		for (var i = startPage; i <= endPage; i++) {
			$pg.append('<button class="page-number' + (i === currentPage ? ' active' : '') + '">' + i + '</button>');
		}
		// > 버튼
		if (endPage < totalPages) {
			$pg.append('<button class="next-page">&gt;</button>');
		}
		// >> 버튼
		if (currentPage < totalPages) {
			$pg.append('<button class="last-page">&raquo;</button>');
		}
	}

	// 4) pagination 클릭 핸들러
	$(".pagination").on("click", "button", function () {
		var btn = $(this);
		var selPage;
		if (btn.hasClass("first-page")) {
			selPage = 1;
		} else if (btn.hasClass("prev-page")) {
			selPage = parseInt($(".pagination .active").text()) - blockSize;
		} else if (btn.hasClass("next-page")) {
			selPage = parseInt($(".pagination .active").text()) + blockSize;
		} else if (btn.hasClass("last-page")) {
			selPage = Math.ceil(totalCount / pageSize);
		} else { // 숫자 버튼
			selPage = parseInt(btn.text());
		}
		selPage = Math.max(1, Math.min(selPage, Math.ceil(totalCount / pageSize)));
		loadPage(selPage);
	});

	// 최초 1페이지 로드
	loadPage(1)
});
