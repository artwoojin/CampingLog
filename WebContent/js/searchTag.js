console.log("searchTag.js 로드됨");

$(document).ready(function () {
	const $input = $(".inputTag input");
	const $tagList = $(".tagList");
	const $submitBtn = $(".applyTagBtn");
	const params = new URLSearchParams(window.location.search);
	const categoryId = params.get("categoryId");
	console.log("tag categoryId:", categoryId);

	if (!$input.length || !$tagList.length || !$submitBtn.length) {
		console.warn("searchTag: .inputTag, .tagList, .applyTagBtn 중 일부가 없습니다.");
		return;
	}

	if (categoryId === "5") {
		console.log("카테고리 5번이므로 태그 입력창 숨김");
		$(".inputTag").hide();
		return;
	}

	$tagList.hide();
	const selectedTags = [];

	function loadTags() {
		if (!categoryId) {
			console.error("searchTag: categoryId 없음");
			return;
		}

		$.ajax({
			url: "controller",
			method: "GET",
			data: {
				cmd: "getTagList",
				categoryId: categoryId
			},
			dataType: "json",
			success: function (resp) {
				try {
					if (typeof resp === "string") {
						resp = JSON.parse(resp);
					}
				} catch (e) {
					console.error("searchTag: JSON 파싱 오류", e);
					return;
				}

				$tagList.empty();

				$.each(resp.tagList, function (i, tag) {
					const $pill = $("<p>")
					.addClass("tag-pill")
					.text(tag.tagName)
					.attr("data-tag-id", tag.tagId)
					.on("click", function () {
						const index = selectedTags.indexOf(tag.tagName);

						if (index > -1) {
							selectedTags.splice(index, 1);
							$pill.removeClass("selected");
						} else {
							if (selectedTags.length >= 6) {
								alert("태그는 최대 6개까지만 선택할 수 있습니다.");
								return;
							}
							selectedTags.push(tag.tagName);
							$pill.addClass("selected");
						}
					});

					$tagList.append($pill);
				});

				console.log("✅ 태그 로드 완료:", resp.tagList);
			},
			error: function (xhr, status, error) {
				console.error("❌ 태그 로드 실패", error);
			}
		});
	}

	$input.on("click", function (e) {
		e.stopPropagation();
		$tagList.css("display", "flex");
		loadTags();
	});

	$(document).on("click", function (e) {
		const $section1 = $("#section1");
		if (!$section1.has(e.target).length) {
			$tagList.hide();
		}
	});

	$submitBtn.on("click", function () {
		if (selectedTags.length === 0) {
			alert("먼저 태그를 선택해주세요!");
			return;
		}

		console.log("🔵 선택한 태그들:", selectedTags);

		$.ajax({
			url: "controller",
			method: "GET",
			data: {
				cmd: "filterPostsByTags",
				tags: selectedTags.join(",")
			},
			traditional: true,
			success: function (resp) {
				console.log("✅ 태그 필터 전송 성공:", resp);
				alert("태그에 맞는 게시글을 불러옵니다!");
				// TODO: resp로 게시글 렌더링 로직 연결할 수 있음
			},
			error: function (xhr, status, error) {
				console.error("❌ 태그 필터 전송 실패", error);
				alert("태그 필터 요청에 실패했습니다.");
			}
		});
	});
});
