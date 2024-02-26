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
    if (!data.username || !data.password) {
        alert("Email or password cannot be empty");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/user/login",
        data: JSON.parse(JSON.stringify(data)),
        success: function (result) {
            if (result) {
                localStorage.setItem("user_name", result.name);
                localStorage.setItem("user_info", JSON.stringify(result));
                alert("Login successfully !!!");
                window.location.replace("/user/list");
            } else {
                alert("Email or password is incorrect. Please try again.");
            }
        },
        error: function(xhr, status, error) {
              throwError(parseInt(xhr.responseJSON.message));
        }
    });
}