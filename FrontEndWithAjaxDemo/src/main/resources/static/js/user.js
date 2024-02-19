$(function () {
    $("#btnSave").on("click", function () {
        let userName = $("#input-user-name").val();
        let userEmail = $("#input-user-email").val();
        let userPhone = $("#input-user-phone").val();
        let userType = $("#user-type-input").val();
        let userDob = $("#input-user-dob").val();
        let userStatus = true;
        let userGender = $('input[name="gender"]:checked').val();

        if (!userName || !userEmail || !userPhone || !userType || !userDob || !userGender) {
            alert("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        let data = {
            name: userName,
            email: userEmail,
            phone: userPhone,
            role: userType,
            dob: userDob,
            gender: userGender,
            status: userStatus
        };

        console.log("Data to be sent:", data);

        addItem("/user/add", data, "/user/list", function (response) {
            if (response && response.error) {
                alert("Lỗi khi thêm dữ liệu: " + response.message);
            }
        });
    })
})