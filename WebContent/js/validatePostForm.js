
function validatePostForm() {
  const category = document.querySelector('select[name="categoryId"]').value;
  if (category === "") {
    alert("카테고리를 선택해주세요!");
    return false;
  }
  return true;
}
