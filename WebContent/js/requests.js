'use strict'

/**
 * Send ajax request to get a list of invoices
 */
function requestInvoices() {
    $.ajax({
        type: "POST",
        url: "invoices/select",
        success: function (data) {
            // check list is not empty
            if (data.length >= 1) {
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
                console.log(data);
            }
        },
        error: function (data) {
            console.log('An error occurred.');
        }
    });
}