let listStringSyllabusCode = [];
$(function () {
    addSyllabus();
    $("#select-syllabus").on("blur", function () {
        setTimeout(function () {
            $("#list-autocomplete").hide();
        }, 200)
    })
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
        let programName = $("#input-name-program").val()
        let data = {
            name: programName,
            email: JSON.parse(localStorage.getItem("user_info")).email,
            listSyllabusCode: listStringSyllabusCode
        };
        addItem("/trainingProgram/add", data, "/trainingProgram/list");
    })

    // event enter
    $(document).on("keypress", function (e) {
        if (e.keyCode === 13 && $("#create-content").css("display") !== "none") {
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


function addSyllabus() {
    $("#select-syllabus").on("keyup", async function () {
        let val = $(this).val();
        if (!val) {
            return;
        }
        await getListSyllabusWithKeyWord("/syllabus/list-all/" + val);
        // render
        let listAutocompleteDom = $("#list-autocomplete");
        listAutocompleteDom.show();
        listAutocompleteDom.html("");
        console.log(resultListSyllabusWithKeyWord)
        for (const [index, item] of resultListSyllabusWithKeyWord.entries()) {
            let syllabusItem = $("<div>")
                .addClass("item-syllabus")
                .addClass("p-2");
            syllabusItem.on("click", function () {
                console.log(item);
                onSelectSyllabus(item);
            })

            // Create name and status elements
            let nameStatusDiv = $("<div>")
                .addClass("name-syllabus d-flex justify-content-between")
                .addClass("item-title");
            nameStatusDiv
                .append($("<div>")
                    .addClass("ms-2")
                    .html(`
                        <label>${item.topicName}</label>
                        <label class="status-${item.status}">${item.status}</label>
                    `));
            syllabusItem.append(nameStatusDiv);

            // Create info element
            let infoDiv = $("<div>")
                .addClass("info-syllabus")
                .addClass("item-info")
                .addClass("small-text");
            let modifiedDate = item.modifiedDate ? item.modifiedDate : "23/07/2022";
            let modifiedBy = item.modifiedBy ? item.modifiedBy : "Johny Deep";
            let infoStr = (`<label class="me-2">${item.version}</label>|<label class="me-2 mx-2">${item.duration}</label>|<label class="mx-2">Modified on ${modifiedDate} by ${modifiedBy}</label>`);
            infoDiv.append(infoStr);
            syllabusItem.append(infoDiv);

            // Add click event for syllabus selection

            // Append the syllabus item to the list
            listAutocompleteDom.append(syllabusItem);
        }
    });
}

function toProperCase(str) {
    return str.replace(
        /\w\S*/g,
        function (txt) {
            return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
        }
    );
}

function onSelectSyllabus(syllabus) {
    listStringSyllabusCode.push(syllabus.topicCode)
    $(".group-syllabus").append(`
        <div class="item-syllabus">
            <div class="name-syllabus d-flex justify-content-between">
                <div>
                    <label>${syllabus.topicName}</label>
                    
                    <label class="status-${toProperCase(syllabus.status)}">${syllabus.status}</label>
                </div>
                <div>X</div>
            </div>
            <div class="info-syllabus">
                <label class="me-2">${syllabus.topicCode}</label>|
                <label class="me-2 mx-2">4 days (12 hours)</label>|
                <label class="mx-2">Modified on ${syllabus.moda} by ${syllabus.modifiedBy}</label>
            </div>
        </div>
    `);
}
