console.log("postPage.js 로드됨");

$(document).ready(function () {
	const params = new URLSearchParams(window.location.search);
	const categoryId = params.get('categoryId');
	console.log("읽어온 categoryId:", categoryId);

	if (!categoryId) {
		console.error("categoryId 파라미터가 없습니다!");
		return;
	}

	const pageSize = 4;
	const blockSize = 5;
	let totalCount = 0;

	function loadPage(page) {
		console.log("제발페이지", page);

		$.ajax({
			url: "controller",
			method: "GET",
			data: {
				cmd: "getPostsByPage",
				page: page,
				categoryId: categoryId
			},
			success: function (resp) {
				try {
					// 항상 JSON.parse 하도록 (JSP 출력 방식이라 string일 확률 높음)
					if (typeof resp === "string") {
						resp = JSON.parse(resp);
					}

					totalCount = resp.totalPostCount;
					renderPostList(resp.postList);
					renderPagination(resp.currentPage, totalCount, pageSize, blockSize);
					console.log("✅ 응답 게시글:", resp.postList);
				} catch (e) {
					console.error("❌ JSON 파싱 오류:", e);
				}
			},
			error: function () {
				console.error("❌ 페이지 데이터 로드 실패");
			}
		});
	}

	function renderPostList(list) {
		console.log("✅ renderPostList 실행됨! 리스트 길이:", list.length);
		const $list = $(".postCardList").empty();

		$.each(list, function (i, post) {
			const formattedDate = post.postDate
				? new Date(post.postDate).toISOString().split('T')[0]
				: "날짜 없음";

			const postImage = post.postImage && post.postImage !== "null"
				? post.postImage
				: 'img/postImg1.png';

			const html = `
				<div class="postCard">
					<div class="postImg"><img src="${postImage}" /></div>
					<div class="postContent">
						<div class="postType">${post.categoryName || ''}</div>
						<div class="postTitle">${post.postTitle || ''}</div>
						<div class="postText">${post.postContents || ''}</div>
					</div>
					<div class="postMeta">
						<div class="up">
							<div class="dateData"><p>${formattedDate}</p></div>
						</div>
						<div class="postStats">
							<div class="left">
								<div class="comment"><p>댓글</p></div>
								<div class="commentData"><p>${post.commentCount ?? 0}</p></div>
							</div>
							<div class="middle">
								<div class="great"><i class="fa-solid fa-fire"></i></div>
								<div class="greatData"><p>${post.likeCount ?? 0}</p></div>
							</div>
						</div>
					</div>
				</div>
			`;

			$list.append(html);
		});
	}

	function renderPagination(currentPage, totalCount, pageSize, blockSize) {
		const totalPages = Math.ceil(totalCount / pageSize);
		const currentBlock = Math.floor((currentPage - 1) / blockSize);
		const startPage = currentBlock * blockSize + 1;
		const endPage = Math.min(startPage + blockSize - 1, totalPages);
		const $pg = $(".pagination").empty();

		if (currentPage > 1) $pg.append('<button class="first-page">&laquo;</button>');
		if (startPage > 1) $pg.append('<button class="prev-page">&lt;</button>');
		for (let i = startPage; i <= endPage; i++) {
			$pg.append(`<button class="page-number${i === currentPage ? ' active' : ''}">${i}</button>`);
		}
		if (endPage < totalPages) $pg.append('<button class="next-page">&gt;</button>');
		if (currentPage < totalPages) $pg.append('<button class="last-page">&raquo;</button>');
	}

	// pagination 버튼 클릭 이벤트
	$(".pagination").on("click", "button", function () {
		const btn = $(this);
		let selPage;

		if (btn.hasClass("first-page")) selPage = 1;
		else if (btn.hasClass("prev-page")) selPage = parseInt($(".pagination .active").text()) - blockSize;
		else if (btn.hasClass("next-page")) selPage = parseInt($(".pagination .active").text()) + blockSize;
		else if (btn.hasClass("last-page")) selPage = Math.ceil(totalCount / pageSize);
		else selPage = parseInt(btn.text());

		selPage = Math.max(1, Math.min(selPage, Math.ceil(totalCount / pageSize)));
		loadPage(selPage);
	});

	// 최초 1페이지 로딩
	loadPage(1);
});
