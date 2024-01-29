$(function(){
    $("#btn-create-class").on("click", function () {
        let className = $("#input-name-class").val();
        let data = {
            name: className
        };
        addItem("/class/add", data);
    })
});