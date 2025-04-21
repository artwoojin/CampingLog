async function includeHTML() {
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
}

document.addEventListener("DOMContentLoaded", includeHTML);