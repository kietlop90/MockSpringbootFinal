$(function(){
    $("#btn-filter-class").on("click", function () {
        addKeywordAndSearch();
    })
    $("#btn-import-class").on("click", function () {
        //TO DO
    })
    $("#btn-add-class").on("click", function () {
        window.location.replace("/class/add");
    })
    $(".btn-update-class").on("click", function () {
        let idClass = $(this).attr("data-id");
        window.location.replace("/class/update/" + idClass);
    })
    $("#btn-copy-class").on("click", function () {
        //TO DO
    })
    $("#btn-delete-class").on("click", function () {
        //TO DO
    })


    // event enter
    $(document).on("keypress", function (e) {
        if(e.keyCode === 13) {
            addKeywordAndSearch();
        }
    })
    // init filter
    const urlParams = new URLSearchParams(window.location.search);
    let keywords = urlParams.get("keywords") ? urlParams.get("keywords").split(",") : [];
    if (keywords && keywords.length > 0) {
        searchKeywords = keywords;
    }

    renderKeywords();
});

var searchKeywords = [];

function addKeywordAndSearch() {
    var input = $("#input-filter").val();
    if (searchKeywords.length === 4) {
        searchKeywords.shift();
    }
    if (input) {
        searchKeywords.push(input);
        search();
    }
}

function removeKeywordAndSearch(index) {
    searchKeywords.splice(index, 1);
    search();
}

function search() {
    // Gửi yêu cầu tìm kiếm đến server
    if (searchKeywords.length > 0) {
        window.location.href = `/class/list?keywords=${encodeURIComponent(searchKeywords.join(','))}`;
    } else {
        window.location.href = `/class/list`;
    }
}

function renderKeywords() {
    let groupFilerDom = $("#group-filter");
    groupFilerDom.html("");
    for (const [index, item] of searchKeywords.entries()) {
        let itemFilterDom = $("<span>")
            .addClass("item-filter")
            .addClass("me-2")
            .css('cursor','pointer')
            .on("click", function () {
                searchKeywords.splice(index, 1);
                searchKeywords.push(item);
                search();
            });
        let keywordsContainerDom = $("<div>")
            .html(item)
            .addClass("me-2")
        let btnRemoveDom = $("<div>");
        btnRemoveDom
            .html("X")
            .addClass("btn-x")
            .on("click", function () {
                removeFilter(index);
            });

        itemFilterDom
            .append(keywordsContainerDom)
            .append(btnRemoveDom);

        groupFilerDom
            .append(itemFilterDom);
    }
}

function removeFilter(index) {
    searchKeywords.splice(index, 1);
    renderKeywords();
    search();
}