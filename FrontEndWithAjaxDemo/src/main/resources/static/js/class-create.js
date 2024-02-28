$(async function () {
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

    })
    $("#btn-back").on("click", function () {
        //TO DO
    })
    $("#btn-cancel").on("click", function () {
        f
        //TO DO
    })
    $("#btn-change-training").on("click", function () {
        $("#info-training-program").hide();
        $("#group-training-program").hide();
        $("#group-filter-training-program").show();
    })

    $("#btn-save-as-draft").on("click", function () {
        let dateDatePicker = picker.data('daterangepicker');
        resultDetailItem = resultDetailItem ? resultDetailItem : {};

        let attend = $("#select-attendee").val();
        let className = $("#input-name-class").val();

        let fsu1 = $("#select-fsu").val();
        let fsuContact = $("#select-contact-fsu").val();
        let fu = fsu1 + " - " + fsuContact;
        let location = $("#select-location").val();
        let trainer = $("#select-trainer").val();
        let admin = $("#select-admin").val();

        let today = new Date();
        let random = Math.floor(Math.random() * 100);
        let code = location + today.getFullYear() + zeroPad(random, 2);
        let startDate = dateDatePicker.startDate.format('MM-DD-YYYY');
        let endDate = dateDatePicker.endDate.format('MM-DD-YYYY');
        let startDayForDuration = moment(dateDatePicker.startDate.format('MM-DD-YYYY'), 'MM-DD-YYYY');
        let endDayForDuration = moment(dateDatePicker.endDate.format('MM-DD-YYYY'), 'MM-DD-YYYY');
        let timeDiff = endDayForDuration.diff(startDayForDuration);
        let daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
        let data = {
            location: location,
            attendee: attend,
            code: code,
            createdBy: JSON.parse(localStorage.getItem("user_info")).id,
            days: resultDetailItem.days,
            fsu: fu,
            hours: resultDetailItem.hours,
            id: resultDetailItem.id,
            listOfClass: resultDetailItem.listOfClass,
            modifiedBy: JSON.parse(localStorage.getItem("user_info")).id,
            name: className,
            endDate: endDate,
            startDate: startDate,
            status: "Planning",
            trainingProgramCode: dataTrainingCode,
            // trainer: trainer,
            // admin: admin,
            duration: daysDiff,
        };
        let pathName = window.location.pathname;
        if (pathName.includes("add")) {
            if (!data.trainingProgramCode) {
                showErrorModal(listError.EM48);
                return;
            }
            addItem("/class/add", data, "/class/list");
        }
        if (pathName.includes("update")) {
            data.id = pathName.split("/class/update/")[1];
            data.code = resultDetailItem.code;
            data.name = resultDetailItem.name;
            data.createdBy = resultDetailItem.createdById;
            data.status = resultDetailItem.status;
            data.attendee = resultDetailItem.attendee;
            data.location = resultDetailItem.location;
            data.fsu = resultDetailItem.fsu;
            data.duration = resultDetailItem.duration;
            updateItem("/class/update", data, "/class/list");
        }
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

    // check add or update
    let pathName = window.location.pathname;
    let startDate = moment().subtract(30, 'days');
    let endDate = moment();
    if (pathName.includes("add")) {
        showCreateContent();
    }
    if (pathName.includes("update")) {
        getTrainer();
        getAdmin();

        showUpdateContent(true);
        let idClass = pathName.split("/class/update/")[1];
        await getItem("/class/getById/" + idClass);
        startDate = moment(resultDetailItem.startDate, "DD/MM/YYYY");
        endDate = moment(resultDetailItem.endDate, "DD/MM/YYYY");
        onSelectTrainer(resultDetailItem.trainingProgramCode, resultDetailItem.trainingProgramName);
    }

    // init daterangepicker
    picker = $('#timeFrameDaterangepicker').daterangepicker({
        parentEl: "#timeFrameDaterangepicker-container",
        alwaysShowCalendars: true,
        linkedCalendars: false,
        showDropdowns: true,
        startDate: startDate,
        endDate: endDate,
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
    setLabelDatepicker();
    // range update listener
    picker.on('apply.daterangepicker', function (ev, picker) {
        setLabelDatepicker();
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
    $("#group-filter-training-program").show();

    addProgramOrUpdate();
    $("#input-filter-training-program").on("blur", function () {
        setTimeout(function () {
            $("#list-autocomplete").hide();
        }, 200)
    })

    $("#info-training-program").hide();
    $("#group-training-program").hide();
});
var dataTrainingCode;

function addProgramOrUpdate() {
    $("#input-filter-training-program").on("keyup", async function () {
        let val = $(this).val();
        if (!val) {
            return;
        }
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
                    await onSelectTrainer(item.code, item.name);
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
}

var picker;

function setLabelDatepicker() {
    let dateDatePicker = picker.data('daterangepicker');
    $("#label-date-range-selected").html(dateDatePicker.startDate.format('MM-DD-YYYY') + ' to ' + dateDatePicker.endDate.format('MM-DD-YYYY'));
}

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
    let idClass = window.location.pathname.split("/class/update/")[1];
    await getList("/user/list-trainer/" + idClass, "trainer");
    let selectTrainerDom = $("#select-trainer");
    for (const [index, item] of resultList["trainer"].entries()) {
        let option = $("<option>")
            .html(item.name)
            .attr("value", item.id);
        selectTrainerDom.append(option);
    }
}

async function getAdmin() {
    let idClass = window.location.pathname.split("/class/update/")[1];
    await getList("/user/list-admin/" + idClass, "admin");
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

async function onSelectTrainer(code, name) {
    dataTrainingCode = code;
    let groupProgramDom = $("#group-training-program");
    groupProgramDom.html("");
    await getListSyllabusWithKeyWord("/syllabus/list-syllabus-program/" + code);
    $("#label-training-name").html(name);
    for (const itemSyllabus of resultListSyllabusWithKeyWord) {
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
            .html("on " + itemSyllabus.createdDate + " by " + itemSyllabus.createdBy);
        div3.append(label1).append(label2);
        div4.append(label3).append(label4).append(label5);
        div2.append(div3).append(div4);
        div.append(div1).append(div2);
        groupProgramDom.append(div);
    }
    $("#group-filter-training-program").hide();
    $("#info-training-program").show();
    $("#group-training-program").show();
}