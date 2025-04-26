document.addEventListener("DOMContentLoaded", function () {
  const logoutBtn = document.getElementById("logoutBtn");

  if (logoutBtn) {
    logoutBtn.addEventListener("click", function () {
      fetch("controller?cmd=logoutAction")
        .then(response => {
          if (response.redirected) {
            window.location.href = response.url;
          } else {
            window.location.href = "mainUI.html";
          }
        })
        .catch(err => {
          alert("로그아웃 실패");
          console.error(err);
        });
    });
  }
});
