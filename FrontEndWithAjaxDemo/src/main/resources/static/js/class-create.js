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
    $("#group-filter-training-program").hide();
    $("#info-training-program").show();

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