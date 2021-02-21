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