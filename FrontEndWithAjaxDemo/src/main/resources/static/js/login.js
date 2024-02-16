$(document).ready(function(){
    let pathName = window.location.pathname;
    if (pathName === "/user/login") {
        $("#group-header-info-user").hide();
    } else {
        $("#group-header-info-user").show();
    }
});

function setUserName() {
    localStorage.setItem("user_name", $("#username-input").val());
}