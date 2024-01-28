$(document).ready(function () {
    let menu = [
        {
            label: "Home",
            path: "home.html",
            icon: "assets/icon/navigation-menu/home.png",
        },
        {
            label: "Syllabus",
            subMenu: [
                {
                    label: "View syllabus",
                    path: "viewSyllabus.html",
                },
                {
                    label: "Create syllabus",
                    path: "createSyllabus.html",
                },
            ],
            icon: "assets/icon/navigation-menu/book-open.png",
        },
        {
            label: "Training program",
            subMenu: [
                {
                    label: "View program",
                    path: "viewProgram.html",
                },
                {
                    label: "Create program",
                    path: "createprogram.html",
                },
            ],
            icon: "assets/icon/navigation-menu/biotech.png",
        },
        {
            label: "Class",
            subMenu: [
                {
                    label: "View class",
                    path: "viewClass.html",
                },
                {
                    label: "Create class",
                    path: "createClass.html",
                },
            ],
            icon: "assets/icon/navigation-menu/school.png",
        },
        {
            label: "Training calendar",
            path: "trainingCalendar.html",
            icon: "assets/icon/navigation-menu/calendar-today.png",
        },
        {
            label: "User management",
            subMenu: [
                {
                    label: "User list",
                    path: "userList.html",
                },
                {
                    label: "User permission",
                    path: "userPermission.html",
                },
            ],
            icon: "assets/icon/navigation-menu/group.png",
        },
        {
            label: "Learning materials",
            path: "learningMaterials.html",
            icon: "assets/icon/navigation-menu/folder.png",
        },
        {
            label: "Setting",
            subMenu: [
                {
                    label: "Calendar",
                    path: "calendar.html",
                }
            ],
            icon: "assets/icon/navigation-menu/settings.png",
        },
    ]
    let menuDom = $("#menu")
    for (const item of menu) {
        let itemMenu = $("<div>").addClass("item-menu").addClass("mb-3");
        let parentItemsDom = $("<div>").addClass("parent-menu");
        let subItemsDom = $("<div>").addClass("sub-menu");
        parentItemsDom.appendTo(itemMenu);
        subItemsDom.appendTo(itemMenu);

        parentItemsDom
            .append($("<img>").attr("src", "../" + item.icon).addClass("me-2"))
            .append($("<div>").html(item.label))
        if (item.subMenu) {
            parentItemsDom
                .append($("<img>").attr("src", "../assets/icon/navigation-menu/arrow-default.png"))
            for (const subItem of item.subMenu) {
                subItemsDom
                    .append($("<div>").html(subItem.label))
            }
        }
        itemMenu.appendTo(menuDom);
    }
});