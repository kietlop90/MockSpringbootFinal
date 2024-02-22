$(document).ready(function () {
    $("#btn-filter-user").on("click", function () {
        addKeywordAndSearch();
    })
    $("#btn-add-user").on("click", function () {
        let role = $("#user-type-input").val();
        let name = $("#name-input").val(); // ten that
        let email = $("#email-address-input").val();
        let phone = $("#phone-input").val();
        let dob = $("#dob-input").val();
        let gender = $("#gender-input").val();
        let data = {
            role: role,
            name: name,
            username: email,
            password: '123123',
            email: email,
            phone: phone,
            dob: dob,
            gender: gender,
            status: true,
        };
        if (resultDetailItem && resultDetailItem.id) {
            data.id = resultDetailItem.id;
            updateItem("/user/update", data, "/user/list");
        } else {
            addItem("/user/add", data, "/user/list");
        }
    })
    $(".btn-update-user").on("click", async function () {
        let id = $(this).attr("data-id");
        await getItem("/user/getById/" + id);
        // update text in form
        $("#title-model").html("Update a user");
        $("#user-type-input").attr("disabled", "disabled");
        $("#email-address-input").attr("disabled", "disabled");

        // fill data in form
        $("#user-type-input").val(resultDetailItem.role);
        $("#name-input").val(resultDetailItem.name); // ten that
        $("#user-name-input").val(resultDetailItem.username);
        $("#email-address-input").val(resultDetailItem.email);
        $("#phone-input").val(resultDetailItem.phone);
        $("#dob-input").attr("value", moment(resultDetailItem.dob).format('YYYY-MM-DD'));
        $("#gender-input").val(resultDetailItem.gender);

        // trigger show modal
        $("#btn-add").click();
    })
    $(".btn-delete-user").on("click", function () {
        let id = $(this).attr("data-id");
        deleteItem("/user/delete", id, "/user/list");
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
        window.location.href = `/user/list?keywords=${encodeURIComponent(searchKeywords.join(','))}`;
    } else {
        window.location.href = `/user/list`;
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