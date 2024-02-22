$(document).ready(function () {
    let menu = [
        {
            label: "Home",
            path: "",
            icon: "icon/navigation-menu/home.png",
        },
        {
            label: "Syllabus",
            subMenu: [
                {
                    label: "View syllabus",
                    path: "/syllabus/list",
                    pathGroup: ["/syllabus/list", "/syllabus/detail"],
                },
                {
                    label: "Create syllabus",
                    path: "/syllabus/create",
                    pathGroup: ["/syllabus/create","/syllabus/update"],
                },
            ],
            icon: "icon/navigation-menu/book-open.png",
        },
        {
            label: "Training program",
            subMenu: [
                {
                    label: "List program",
                    path: "/trainingProgram/list",
                    pathGroup: ["/trainingProgram/list"],
                },
                {
                    label: "Create program",
                    path: "/trainingProgram/add",
                    pathGroup: ["/trainingProgram/add", "/trainingProgram/update"],
                },
            ],
            icon: "icon/navigation-menu/group.png"
        },
        {
            label: "Class",
            subMenu: [
                {
                    label: "View class",
                    path: "/class/list",
                    pathGroup: ["/class/list", "/class/detail"],
                },
                {
                    label: "Create class",
                    path: "/class/add",
                    pathGroup: ["/class/add", "/class/update"],
                },
            ],
            icon: "icon/navigation-menu/school.png",
        },
        {
            label: "Training calendar",
            path: "",
            icon: "icon/navigation-menu/calendar-today-blue.png",
        },
        {
            label: "User management",
            subMenu: [
                {
                    label: "User list",
                    path: "/user/list",
                    pathGroup: ["/user/list"],
                },
                {
                    label: "User permission",
                    path: "",
                },
            ],
            icon: "icon/navigation-menu/group.png",
        },
        {
            label: "Learning materials",
            path: "",
            icon: "icon/navigation-menu/folder.png",
        },
        {
            label: "Setting",
            subMenu: [
                {
                    label: "Calendar",
                    path: "",
                }
            ],
            icon: "icon/navigation-menu/settings.png",
        },
    ]
    let pathName = window.location.pathname;
    let menuDom = $("#menu")
    for (const item of menu) {
        let itemMenu = $("<div>").addClass("item-menu").addClass("mb-3");
        let parentItemsDom = $("<div>").addClass("parent-menu");
        parentItemsDom
            .appendTo(itemMenu);
        parentItemsDom
            .append($("<img>").attr("src", "/" + item.icon).addClass("me-2"))
            .append($("<div>").html(item.label))

        // check curren page to active item menu
        if (checkActiveItemMenu(item.pathGroup, pathName)) {
            parentItemsDom.addClass("active");
        }

        if (item.subMenu) {
            let subItemsDom = $("<div>").addClass("sub-menu");
            subItemsDom
                .appendTo(itemMenu);
            for (const subItem of item.subMenu) {
                let subItemDom = $("<div>").html(subItem.label)
                subItemDom.on("click", function () {
                    window.location.replace(subItem.path);
                });
                // check curren page to active item menu
                if (checkActiveItemMenu(subItem.pathGroup, pathName)) {
                    parentItemsDom.toggleClass("open");
                    subItemsDom.addClass("open");
                    subItemDom.addClass("active");
                }
                subItemsDom
                    .append(subItemDom)
            }

            parentItemsDom.on("click", function () {
                parentItemsDom.toggleClass("open");
                subItemsDom.toggleClass("open");
            });
            parentItemsDom
                .append($("<img>")
                    .addClass("icon-arrow")
                    .attr("src", "/icon/navigation-menu/arrow-default.png"))
        } else {
            parentItemsDom.on("click", function () {
                window.location.replace(item.path);
            });
        }
        itemMenu
            .appendTo(menuDom);
    }
});

function checkActiveItemMenu(pathGroup, pathName) {
    if (pathGroup) {
        for (const path of pathGroup) {
            if (pathName.includes(path)) {
                return true;
            }
        }
    }
    return false;
}


