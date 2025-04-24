console.log("headerCategory.js 로드됨");

$(document).ready(function () {
    function loadHeaderWithNames() {
        $.ajax({
            url: "controller",
            method: "GET",
            data: { cmd: "getCategoryName" },
            success: function (resp) {
                if (typeof resp === "string") resp = JSON.parse(resp);
                renderHeaderList(resp.categoryList);
            },
            error: function () {
                console.error("페이지 데이터 로드 실패 (ID+Name)");
            }
        });
    }

    function loadHeader(categoryId) {
        $.ajax({
            url: "controller",
            method: "GET",
            data: {
                cmd: "getHeaderCategoryId",
                categoryId: categoryId
            },
            success: function (resp) {
                if (typeof resp === "string") resp = JSON.parse(resp);
                renderHeaderList(resp.categoryList);
                console.log("로드된 카테고리 ID:", categoryId);
            },
            error: function () {
                console.error("페이지 데이터 로드 실패");
            }
        });
    }

    function renderHeaderList(list) {
        var $menu = $(".middleMenu");
        $menu.empty();

        $.each(list, function (i, category) {
            var $link = $(
              '<p>' +
                '<a href="javascript:void(0)" class="category-link" ' +
                  'data-category-id="' + category.categoryId + '">' +
                  category.categoryName +
                '</a>' +
              '</p>'
            );
            $menu.append($link);
        });

        $(".category-link")
          .off("click")
          .on("click", function () {
            var id = $(this).data("categoryId");
            window.location.href = 'controller?cmd=postPage&categoryId=' + id;
          });
    }

    // (필요하다면) 초기 실행 호출
    loadHeaderWithNames();

});  // ← 여기 있는 게 핵심! .ready(…) 끝낼 때 필요합니다.
