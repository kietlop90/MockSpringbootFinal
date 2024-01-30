$(function () {
    $("#btn-create-class").on("click", function () {
        let className = $("#input-name-class").val();
        let data = {
            name: className
        };
        addItem("/class/add", data, "/class/update/");
    })

    let pathName = window.location.pathname;
    if (pathName.includes("add")) {
        $("#create-content").show();
        $("#update-content").hide();
    }
    if (pathName.includes("update")) {
        $("#create-content").hide();
        $("#update-content").show();
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
    // show picker on load
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
});