$(function(){
    $("#btn-filter-class").on("click", function () {
        //TO DO
    })
    $("#btn-import-class").on("click", function () {
        //TO DO
    })
    $("#btn-add-class").on("click", function () {
        window.location.replace("/class/add");
    })
    $(".btn-update-class").on("click", function () {
        let idClass = $(this).attr("data-id-class");
        window.location.replace("/class/update/" + idClass);
    })
    $("#btn-copy-class").on("click", function () {
        //TO DO
    })
    $("#btn-delete-class").on("click", function () {
        //TO DO
    })
});