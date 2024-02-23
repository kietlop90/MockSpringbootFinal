$(document).ready(function () {
    let tabDom = $(".tab");
    if (tabDom.length > 0) {
        for (const index in tabDom) {
            let tabDomElement = tabDom[index];
            let indexTab = Number(index);
            if (typeof tabDomElement === "number") {
                break;
            }
            $(tabDomElement).on("click", function () {
                let tabActiveDom = $(".tab.active");
                for (const tabActiveDomElement of tabActiveDom) {
                    $(tabActiveDomElement).removeClass("active");
                }
                let tabBodyDom = $(".tab-body");
                for (const tabBodyDomElement of tabBodyDom) {
                    $(tabBodyDomElement).hide();
                }
                $(tabDomElement).addClass("active");
                $("#tab-body-" + (indexTab + 1)).show();
            });
            let tabBodyDom = $("#tab-body-" + (indexTab + 1));
            if (!tabBodyDom.hasClass("active")) {
                tabBodyDom.hide();
            }
        }
    }

    // set info user
    let userName = localStorage.getItem("user_name");
    $("#user-name").html(userName);
});

let resultList;

function getList(url, prop) {
    return Promise.resolve(
        $.ajax({
            type: "GET",
            url: url,
            success: function (result) {
                if (prop) {
                    resultList = resultList ? resultList : {};
                    resultList[prop] = result;
                } else {
                    resultList = result;
                }
            }
        })
    ).then(data => {
        return data;
    });
}

let resultGetItemById;

function getItemById(url, id) {
    $.ajax({
        type: "GET",
        url: url + id,
        success: function (result) {
            resultGetItemById = result;
        }
    });
}

let resultAddItem;

function addItem(url, data, redirect) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.parse(JSON.stringify(data)),
        success: function (result) {
            resultAddItem = result;
            console.log(result)
            alert("Add new successfully !!!");
            if (redirect) {
                window.location.replace(redirect);
            }
        }
    });
}

let resultUpdateItem;

function updateItem(url, data, redirect, isAlert = true) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (result) {
            resultUpdateItem = result;
            if (isAlert) {
                alert("Update successfully !!!");
            }
            if (redirect) {
                window.location.replace(redirect);
            }
        }
    });
}

let resultDetailItem;

function getItem(url, data, redirect) {
    return Promise.resolve(
        $.ajax({
            type: "GET",
            url: url,
            data: data,
            success: function (result) {
                resultDetailItem = result;
            }
        })
    ).then(data => {
        return data;
    });
}

let resultDeleteItem;

function deleteItem(url, id, redirect) {
    $.ajax({
        type: "DELETE",
        url: url + "/" + id,
        success: function (result) {
            resultDeleteItem = result;
            alert("Delete successfully !!!");
            if (redirect) {
                window.location.replace(redirect);
            }
        }
    });
}

function logout() {
    localStorage.removeItem("user-item");
    window.location.replace("/user/login");
}