console.log("searchTag.js 로드됨");
document.addEventListener("DOMContentLoaded", () => {
	const input = document.querySelector(".inputTag input");
	const tagList = document.querySelector(".tagList");

	// input 클릭 시: 열려있으면 닫히고, 닫혀있으면 열림 (토글)
	input.addEventListener("click", (e) => {
		e.stopPropagation(); // 상위 전파 방지
		tagList.classList.toggle("active");
	});

	// 문서 전체 클릭 시: input 영역 외 클릭하면 닫힘
	document.addEventListener("click", (e) => {
		const inputArea = document.querySelector(".inputTag");
		if (!inputArea.contains(e.target)) {
			tagList.classList.remove("active");
		}
	});
});
