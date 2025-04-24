document.addEventListener("DOMContentLoaded", function () {
  const categorySelect = document.querySelector("select[name='categoryId']");
  const tagContainer = document.querySelector(".tag-grid");

  categorySelect.addEventListener("change", function () {
    const categoryId = this.value;

    tagContainer.innerHTML = "";
    document.getElementById("selectedTags").innerHTML = "";

    if (!categoryId) return;

    fetch(`controller?cmd=tagListAction&categoryId=${categoryId}`)
      .then(res => res.json())
      .then(data => {
        const rowTop = document.createElement("div");
        rowTop.className = "row row-top";
        const rowBottom = document.createElement("div");
        rowBottom.className = "row row-bottom";

        data.forEach((tag, index) => {
          const btn = document.createElement("button");
          btn.type = "button";
          btn.className = "tag";
          btn.dataset.tagid = tag.tagId;
          btn.textContent = `#${tag.tagName}`;
          (index < 6 ? rowTop : rowBottom).appendChild(btn);
        });

        tagContainer.appendChild(rowTop);
        tagContainer.appendChild(rowBottom);
      });
  });
});
