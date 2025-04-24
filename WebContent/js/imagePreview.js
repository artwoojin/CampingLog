
function triggerImageUpload() {
  document.getElementById("imageInput").click();
}

document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("imageInput").addEventListener("change", function (e) {
    const file = e.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function (event) {
      const preview = document.getElementById("previewImg");
      preview.src = event.target.result;

      const guide = document.getElementById("uploadGuide");
      if (guide) guide.style.display = "none";
      
      const fileNameDisplay = document.getElementById("imageFileNameDisplay");
      if (fileNameDisplay) fileNameDisplay.value = file.name;
    };
    reader.readAsDataURL(file);
  });
});
