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
        let data = {
            name: className
        };
        addItem("/class/add", data, "/class/update/");
    })
    $("#btn-next").on("click", function () {
        //TO DO
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
        timePicker : true,
        timePicker24Hour : true,
        timePickerIncrement : 1,
        timePickerSeconds : false,
        autoApply: true,
        locale : {
            format : 'HH:mm'
        }
    }).on('show.daterangepicker', function(ev, picker) {
        picker.container.find(".calendar-table").hide();
    });

    // input-filter-training-program
    $("#group-filter-training-program").hide();
    $("#info-training-program").show();
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