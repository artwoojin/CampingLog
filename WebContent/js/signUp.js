console.log("SignUp.js 로드됨");
$(document).ready(function () {
	$(".form-box").on("submit", function (e) {
		e.preventDefault(); // 기본 제출 막기

		const data = {
				id: $("#userid").val(),
				pw: $("#password").val(),
				email: $("#email").val(),
				nickName: $("#nickname").val(),
				name: $("#name").val(),
				phoneNumber: $("#phoneNumber").val()
		};

		$.ajax({
			type: "POST",
			url: "/CampingLog/signUpResult.jsp",
			data: data,
			success: function (response) {
				try {
					const json = typeof response === "string" ? JSON.parse(response) : response;
					if (json.result === "success") {
						window.location.href = "/CampingLog/mainUI.html";
					} else {
						alert("회원가입 실패: 다시 시도해주세요.");
					}
				} catch (e) {
					console.error("응답 파싱 실패:", e);
					alert("서버 응답 오류");
				}
			}
		});
	});
});
