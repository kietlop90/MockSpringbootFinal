function prepareEvent(ajaxCustomers){
    prepareUpdateDeleteEvent(ajaxCustomers);
    prepareFormSubmit();
}

function prepareUpdateDeleteEvent(ajaxCustomers){
    $('table#list-customer tbody tr').toArray().forEach((child, index) => {
        let id = ajaxCustomers[index].id;
        $('#update' + id).click(() => getCustomerById(id))
        $('#delete' + id).click(() => deleteById(id))
    })
}
function prepareFormSubmit(){
    $('#customer-form').submit(function (event) {
        event.preventDefault()
        let customer = {
            id: $('#customerid').val(),
            firstName: $('#firstname').val(),
            lastName: $('#lastname').val(),
            email: $('#email').val()
        }
        resetInput()
        if(customer.id != null && customer.id.length > 0){ // update
            updateEvent(customer);
        }
        else { // create
           delete customer.id;
           console.log(customer)
           addEvent(customer);
        }
    })
}

function addEvent(customer){
    $.ajax({
        type: "POST",
        url: "/customer/add",
        data: customer,
        success: function (result) {
            let id = result.id;
            let newElement = `<tr id='${"tr" + id}'>
                            <td id='${"fname" + id}'>${result.firstName}</td>
                            <td id='${"lname" + id}'>${result.lastName}</td>
                            <td id='${"email" + id}'>${result.email}</td>
                            <td>
                                <button class="abtn" id ="${'update' + id}">Update</button>
                                <button class="abtn" id ="${'delete' + id}">Delete</button>
                            </td>
                            </tr>`
            $('#list-customer thead').after(newElement)
            $('#update' + id).click(() => getCustomerById(id))
            $('#delete' + id).click(() => deleteById(id))
        }
    });
}
function updateEvent(customer){
    $.ajax({
        type : "POST",
        url : "/customer/update",
        data: customer,
        success : function(result){
            console.log(result)
            let tr = '#list-customer #tr'+ result.id;
            let fName = tr + ' #fname' + result.id
            let lName = tr + ' #lname' + result.id
            let email = tr + ' #email' + result.id
            $(fName).text(result.firstName)
            $(lName).text(result.lastName)
            $(email).text(result.email)
        }
    });
}

function resetInput(){
    $('#customerid').val("")
    $('#firstname').val("")
    $('#lastname').val("")
    $('#email').val("")
}

function deleteById(id) {
    if (!(confirm('Are you sure you want to delete this customer?')))
        return false;
    $.ajax({
        type : "POST",
        url : "/customer/delete/"+id,
        success : function(result){
            if(result >= 0){
                let selector = 'table#list-customer #tr'+result;
                $(selector).remove()
            }
            else{
                alert("Can not delete, wrong id")
            }

        }
    });
}

function getCustomerById(id) {
    if (!(confirm('Are you sure you want to update this customer?')))
        return false;
    $.ajax({
        type : "GET",
        url : "/customer/update/"+id,
        success : function(result){
            $('#customerid').val(result.id)
            $('#firstname').val(result.firstName)
            $('#lastname').val(result.lastName)
            $('#email').val(result.email)
        }
    });
}