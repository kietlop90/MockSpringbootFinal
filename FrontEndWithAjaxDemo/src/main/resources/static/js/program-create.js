$(function () {
    $("#btn-create-program").on("click", function () {
        showUpdateContent();
    })
    $("#btn-update-program").on("click", function () {
        let id = $(this).attr("data-id");
        window.location.replace("/trainingProgram/update/" + id);
    })
    $("#btn-copy-program").on("click", function () {
        //TO DO
    })
    $("#btn-delete-program").on("click", function () {
        //TO DO
    })
    $("#btn-back").on("click", function () {
        //TO DO
    })
    $("#btn-cancel").on("click", function () {
        //TO DO
    })
    $("#btn-save").on("click", function () {
        let programName = $("#input-name-program").val();
        let data = {
            name: programName
        };
        addItem("/trainingProgram/add", data, "/trainingProgram/list");
    })

    // event enter
    $(document).on("keypress", function (e) {
        if(e.keyCode === 13 && $("#create-content").css("display") !== "none") {
            showUpdateContent();
        }
    })

    let pathName = window.location.pathname;
    if (pathName.includes("add")) {
        showCreateContent();
    }
    if (pathName.includes("update")) {
        showUpdateContent(true);

    }
});

function showCreateContent() {
    $("#create-content").show();
    $("#update-content").hide();
}

function showUpdateContent(isUpdate = false) {
    $("#create-content").hide();
    $("#update-content").show();

    if (!isUpdate) {
        $(".group-btn-three-dot").hide();

        let programName = $("#input-name-program").val();
        $("#label-name-program").html(programName);

        $("#btn-back").hide();
    }
}