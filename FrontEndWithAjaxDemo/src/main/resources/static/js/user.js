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
        let gender = $(".gender-input:checked").val();
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

            let errorName = $("#error-name");
            let errorPhone = $("#error-phone");
            let errorDob = $("#error-dob");
            let nameRegex = /^[a-zA-Z\u00C0-\u017F\s]{1,50}$/;
            let phoneRegex = /^[0-9]{9}$/;

            if (!name) {
                errorName.html(listError.EM02);
            } else if (!nameRegex.test(name)) {
                errorName.html("Invalid name format. Please enter from a-z or A-Z and limit 50 characters");
                return;
            }

            if (!phone) {
                errorPhone.html(listError.EM06);
            } else if (!phoneRegex.test(phone)) {
                errorPhone.html("Invalid phone number format. Please enter 9 digits from 0 to 9.");
                return;
            }

            if (!dob) {
                errorDob.html(listError.EM08);
                return;
            }
            updateItem("/user/update", data, "/user/list");
        } else {
            let errorRole = $("#error-role");
            let errorName = $("#error-name");
            let errorEmail = $("#error-email");
            let errorPhone = $("#error-phone");
            let errorDob = $("#error-dob");
            let phoneRegex = /^[0-9]{9}$/;
            let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            let nameRegex = /^[a-zA-Z\u00C0-\u017F\s]{1,50}$/;

            if (!role) {
                errorRole.html("Role is required")
            }
            if (!name) {
                errorName.html(listError.EM02);
            } else if (!nameRegex.test(name)) {
                errorName.html("Invalid name format. Please enter from a-z or A-Z and limit 50 characters");
                return;
            }

            if (!email) {
                errorEmail.html(listError.EM03);
            } else if (!emailRegex.test(email)) {
                errorEmail.html(listError.EM05);
                return;
            }

            if (!phone) {
                errorPhone.html(listError.EM06);
            } else if (!phoneRegex.test(phone)) {
                errorPhone.html("Invalid phone number format. Please enter 9 digits from 0 to 9.");
                return;
            }

            if (!dob) {
                errorDob.html(listError.EM08);
                return;
            }

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
        $("#dob-input").attr("value", moment(resultDetailItem.dob, "DD/MM/YYYY").format('YYYY-MM-DD'));
        $("#gender-input").val(resultDetailItem.gender);
        $("#switch-status").attr("checked", resultDetailItem.status);

        // trigger show modal
        $("#btn-add").click();
    })
    $(".btn-de-activate-user").on("click", async function () {
        let id = $(this).attr("data-id");
        await getItem("/user/getById/" + id);
        resultDetailItem.status = !resultDetailItem.status;
        resultDetailItem.dob = moment(resultDetailItem.dob, "DD/MM/YYYY").format('YYYY-MM-DD');
        updateItem("/user/update", resultDetailItem, "/user/list", false);
    });
    $(".btn-delete-user").on("click", function () {
        let id = $(this).attr("data-id");
        deleteItem("/user/delete", id, "/user/list");
    })
    $(".btn-change-role").on("click", async function () {
        let id = $(this).attr("data-id");
        let role = $(this).attr("data-role");
        await getItem("/user/getById/" + id);
        resultDetailItem.role = role;
        resultDetailItem.dob = moment(resultDetailItem.dob, "DD/MM/YYYY").format('YYYY-MM-DD');
        updateItem("/user/update", resultDetailItem, "/user/list", false);
    });

    var myDropdownThreeDot = document.getElementsByClassName('btn-three-dot');
    for (const ele of myDropdownThreeDot) {
        ele.addEventListener('show.bs.dropdown', function (e) {
            checkShowChangeRole(e);
        })
    }

    // event enter
    $(document).on("keypress", function (e) {
        if (e.keyCode === 13) {
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
            .css('cursor', 'pointer')
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

function checkShowChangeRole(item) {
    let id = $(item.target).attr("data-id");
    let role = $(item.target).attr("data-role");
    let userInfo = JSON.parse(localStorage.getItem("user_info"));
    if (userInfo.role !== 'SUPERADMIN') {
        let myDropdownChangeRole = document.getElementById('btn-change-role-' + id);
        myDropdownChangeRole.addEventListener('show.bs.dropdown', function (e) {
            $("#item-role-" + id).hide();
        })
    }
    console.log(id, role);
}