$(document).ready(function(){
    let pathName = window.location.pathname;
    if (pathName === "/user/login") {
        $("#group-header-info-user").hide();
    } else {
        $("#group-header-info-user").show();
    }
});
