async function includeHTML(callback) {
  const includeTargets = document.querySelectorAll('[data-include]');
  for (const el of includeTargets) {
    const file = el.getAttribute('data-include');
    try {
      const res = await fetch(file);
      if (!res.ok) throw new Error('파일 불러오기 실패');
      const html = await res.text();
      el.innerHTML = html;
    } catch (err) {
      console.error(`"${file}" 로딩 실패:`, err);
    }
  }
  if (callback) callback(); // 다 끝나고 실행
}

document.addEventListener("DOMContentLoaded", function () {
  includeHTML(function () {
    // include 끝난 뒤 JS 실행
    const s1 = document.createElement('script');
    s1.src = "js/mainPopularList.js";
    document.body.appendChild(s1);

    const s2 = document.createElement('script');
    s2.src = "js/mainRankList.js";
    document.body.appendChild(s2);
    
    const s3 = document.createElement('script');
    s3.src = "js/mainEquList.js";
    document.body.appendChild(s3);

  });
});
