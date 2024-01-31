let resultGetItemById;
function getItemById(url, id) {
    $.ajax({
        type : "GET",
        url : url + id,
        success : function(result){
            resultGetItemById = result;
        }
    });
}

let resultAddItem;
function addItem(url, data, redirect){
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (result) {
            resultAddItem = result;
            if (redirect) {
                window.location.replace(redirect + resultAddItem.id);
            }
        }
    });
}

let resultUpdateItem;
function updateItem(url, data){
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (result) {
            resultUpdateItem = result;
        }
    });
}

let resultDeleteItem;
function deleteItem(url, id){
    $.ajax({
        type: "POST",
        url: url + id,
        success: function (result) {
            resultDeleteItem = result;
        }
    });
}