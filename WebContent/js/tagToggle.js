$(document).on("click", ".tag", function () {
  const tagId = $(this).data("tagid");
  const isSelected = $(this).hasClass("selected");

  if (!isSelected && $("#selectedTags input").length >= 6) {
    alert("최대 6개까지만 선택할 수 있습니다.");
    return;
  }

  $(this).toggleClass("selected");

  if (!isSelected) {
    // 선택 → hidden input 추가
    $("#selectedTags").append(
      $("<input>").attr({
        type: "hidden",
        name: "tagId",
        value: tagId,
        class: "hidden-tag-" + tagId
      })
    );
  } else {
    // 해제 → hidden input 제거
    $(".hidden-tag-" + tagId).remove();
  }
});
