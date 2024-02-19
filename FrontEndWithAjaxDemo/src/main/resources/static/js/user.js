$(document).ready(function(){
    $("#btn-filter-user").on("click", function () {
        //TO DO
    })
    $("#btn-add-user").on("click", function () {
        let roleId = $("#user-type-input").val();
        let name = $("#name-input").val(); // ten that
        let username = $("#user-name-input").val(); // ten that
        let email = $("#email-address-input").val();
        // let password = $("#password-input").val();
        let phone = $("#phone-input").val();
        let dob = $("#dob-input").val();
        let gender = $("#gender-input").val();
        let data = {
            roleId: "ADMIN",
            name: name,
            username: email,
            password: '123456789',
            email: email,
            phone: phone,
            dob: dob,
            gender: gender,
            status: true,
        };
        addItem("/user/add", data, "/user/list");
    })
    $(".btn-update-user").on("click", function () {
        //TO DO
    })

    document.querySelectorAll('.type').forEach(function(type) {
        if (type.textContent.trim() === 'TRAINER') {
            type.classList.add('type-trainee');
        } else {
            type.classList.add('type-admin');
        }
    });
});
