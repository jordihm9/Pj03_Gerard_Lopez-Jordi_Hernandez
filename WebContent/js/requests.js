'use strict'

/**
 * Send ajax request to get a list of invoices
 */
function requestInvoices() {
    $.ajax({
        type: "POST",
        url: "invoices/select",
        success: function (data, textStatus, xhr) {
            // clear the table body before inserting rows
            clearInvoicesTable();
            // check if returned 204 status code = no invoices found
            if (xhr.status === 204) {
                // no invoices returned
                console.log("No invoices found.");
            } else if (xhr.status === 200){
                addInvoices(data);
            }
        },
        error: function (data) {
            console.log('An error occurred.');
        }
    });
}


/**
 * Send ajax request to get data from an specific invoice
 */
function requestInvoice(id) {
    $.ajax({
        type: "POST",
        url: "invoice/select",
        datatype: "html",
        data: {'id': id},
        success: function (data) {
            // check list
            if (data) {
                fillFieldsInvoice(data);
            }
        },
        error: function (data) {
            console.log('An error occurred.');
        }
    });
}

function requestDelete(id) {
    $.ajax({
        type: "POST",
        url: "invoice/delete",
        data: {"id": id},
        dataType: "HTML",
        success: function (response) {
            console.log("Invoice deleted successfully.");
            requestInvoices();
        },
        error: function (data) {
            console.log("Invoice could not have been deleted.");
        }
    });
}

function requestClient(nif) {
    $.ajax({
        type: "POST",
        url: "client/select",
        data: { "nif": nif },
        dataType: "HTML",
        success: function (data, textStatus, xhr) {
            // if status code is 204, means client was not found
            if (xhr.status === 204) {
                // no client returned
                console.log("Client was not found");
            } else if (xhr.status === 200) {
                let client = JSON.parse(data); // cast json to js object
                
                // fill fields with client information
                $('#nif').text(client.nif);
                $('#clientName').text(client.name +' '+ client.lastname);
                $('#address').text(client.address);
                $('#town').text(client.town);
            }
        }
    });
}