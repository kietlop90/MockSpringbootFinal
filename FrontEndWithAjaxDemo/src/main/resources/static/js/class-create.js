$(function () {
    $("#btn-create-class").on("click", function () {
        showUpdateContent();
    })
    $("#btn-update-class").on("click", function () {
        let id = $(this).attr("data-id");
        window.location.replace("/class/update/" + id);
    })
    $("#btn-copy-class").on("click", function () {
        //TO DO
    })
    $("#btn-delete-class").on("click", function () {
        //TO DO
    })
    $("#btn-back").on("click", function () {
        //TO DO
    })
    $("#btn-cancel").on("click", function () {
        //TO DO
    })

    $("#btn-save-as-draft").on("click", function () {
        let className = $("#input-name-class").val();
        // let duration = $("#timePicker").val();
        let location = $("#select-location").val();
        let trainer = $("#select-trainer").val();
        let admin = $("#select-admin").val();
        let dateTime = $("#timeFrameDaterangepicker").val().split(' - ');
        let today = new Date();
        let random = Math.floor(Math.random() * 100);
        let code = location + today.getFullYear() + zeroPad(random, 2);
        let data = {
            name: className,
            // duration : duration,
            location: location,
            trainer: trainer,
            admin: admin,
            startDate: dateTime[0],
            endDate: dateTime[1],
            code: code,
            createdBy: JSON.parse(localStorage.getItem("user_info")).id,
            trainingProgramCode: dataTraining.id
        };
        addItem("/class/add", data, "/class/list");
    })
    $("#btn-next").on("click", function () {
        //TO DO
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

    // init daterangepicker
    var picker = $('#timeFrameDaterangepicker').daterangepicker({
        parentEl: "#timeFrameDaterangepicker-container",
        alwaysShowCalendars: true,
        linkedCalendars: false,
        showDropdowns: true,
        startDate: moment().subtract(30, 'days'),
        endDate: moment(),
        autoApply: true,
        locale: {
            format: "MM-DD-YYYY",
            weekLabel: "W",
            daysOfWeek: [
                "Su",
                "Mo",
                "Tu",
                "We",
                "Th",
                "Fr",
                "Sa",
            ],
            monthNames: [
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December",
            ],
            firstDay: 1,
        },
    });
    // range update listener
    picker.on('apply.daterangepicker', function (ev, picker) {
        $("#label-date-range-selected").html(picker.startDate.format('MM-DD-YYYY') + ' to ' + picker.endDate.format('MM-DD-YYYY'));
    });
    // prevent hide after range selection
    picker.data('daterangepicker').hide = function () {
    };
    // always show picker on load
    picker.data('daterangepicker').show();

    // timePicker
    $('#timePicker').daterangepicker({
        timePicker: true,
        timePicker24Hour: true,
        timePickerIncrement: 1,
        timePickerSeconds: false,
        autoApply: true,
        locale: {
            format: 'HH:mm'
        }
    }).on('show.daterangepicker', function (ev, picker) {
        picker.container.find(".calendar-table").hide();
    });

    // input-filter-training-program
    var dataTraining = {};
    var dataSyllabus = {};
    $("#group-filter-training-program").show();
    $("#input-filter-training-program").on("keyup", async function () {
        let val = $(this).val();
        await getListWithKeyWord("/trainingProgram/list-name/" + val);
        // render

        let listAutocompleteDom = $("#list-autocomplete");
        listAutocompleteDom.show();
        listAutocompleteDom.html("");
        for (const [index, item] of resultListWithKeyWord.entries()) {
            let itemDom = $("<div>")
            itemDom
                .addClass("item")
                .on("click", async function () {
                    dataTraining = item;
                    await getListSyllabusWithKeyWord("/syllabus/list-syllabus-program/" + item.code);
                    dataSyllabus = resultListSyllabusWithKeyWord;
                    for (const itemSyllabus of resultListSyllabusWithKeyWord) {
                        let groupProgramDom = $("#group-training-program")
                        let div = $("<div>");
                        let div1 = $("<div>");
                        let div2 = $("<div>")
                        div1.append("<img src='/icon/indicator/supplier.png' alt=''>");
                        let div3 = $("<div>")
                            .addClass("pe-3 me-3");
                        let div4 = $("<div>");
                        let label1 = $("<label>")
                            .addClass("name")
                            .html(itemSyllabus.topicName);
                        let label2 = $("<label>")
                            .addClass("status-active")
                            .html(itemSyllabus.status);
                        let label3 = $("<label>")
                            .html(itemSyllabus.topicCode + itemSyllabus.version);
                        let label4 = $("<label>")
                            .html("4 days (12 hours)");
                        let label5 = $("<label>")
                            .html("on " + itemSyllabus.createdDate + " by "+ itemSyllabus.createdBy);
                        div3.append(label1).append(label2);
                        div4.append(label3).append(label4).append(label5);
                        div2.append(div3).append(div4);
                        div.append(div1).append(div2);
                        groupProgramDom.append(div);
                    }
                    $("#group-filter-training-program").hide();
                    $("#info-training-program").show();
                    $("#group-training-program").show();
                })
            ;

            let titleDom = $("<div>")
            titleDom
                .addClass("item-title")
                .html(item.name);

            let infoDom = $("<div>")
            let modifiedDate = item.modifiedDate ? item.modifiedDate : "23/07/2022"
            let modifiedBy = item.modifiedBy ? item.modifiedBy : "Johny Deep"
            let infoStr = ('... days (... hours) ' + modifiedDate + ' by ' + modifiedBy);
            infoDom
                .addClass("item-info")
                .addClass("small-text")
                .html(infoStr);

            itemDom
                .append(titleDom)
                .append(infoDom);

            listAutocompleteDom
                .append(itemDom);
        }
    })
    $("#input-filter-training-program").on("blur", function () {
        setTimeout(function () {
            $("#list-autocomplete").hide();
        }, 200)
    })

    $("#info-training-program").hide();
    $("#group-training-program").hide();

    getTrainer();
    getAdmin();
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

        let className = $("#input-name-class").val();
        $("#label-name-class").html(className);

        $("#btn-back").hide();
    }
}

async function getTrainer() {
    await getList("/user/list-trainer", "trainer");
    let selectTrainerDom = $("#select-trainer");
    for (const [index, item] of resultList["trainer"].entries()) {
        let option = $("<option>")
            .html(item.name)
            .attr("value", item.id);
        selectTrainerDom.append(option);
    }
}

async function getAdmin() {
    await getList("/user/list-admin", "admin");
    let selectAdminDom = $("#select-admin");
    for (const [index, item] of resultList["admin"].entries()) {
        let option = $("<option>")
            .html(item.name)
            .attr("value", item.id);
        selectAdminDom.append(option);
    }
}

function zeroPad(num, places) {
    var zero = places - num.toString().length + 1;
    return Array(+(zero > 0 && zero)).join("0") + num;
}