$(document).ready(function(){
    let pathName = window.location.pathname;
    if (pathName === "/user/login") {
        $("#group-header-info-user").hide();
    } else {
        $("#group-header-info-user").show();
    }
});

function login() {
    let data = {
        username: $("#username-input").val(),
        password: $("#password-input").val(),
    }
    $.ajax({
        type: "POST",
        url: "/user/login",
        data: JSON.parse(JSON.stringify(data)),
        success: function (result) {
            localStorage.setItem("user_name", result.name);
            localStorage.setItem("user_info", JSON.stringify(result));
            window.location.replace("/user/list");
        }
    });
}