'use strict'

function sendInvoice(data) {
    $.ajax({
        type: "POST",
        url: "invoice/save",
        data: data,
        dataType: "HTML",
        success: function (data, textStatus, xhr) {
            console.log("Invoice saved successfully.");
        },
        error: function(data) {
            console.log("Invoice could not have been saved.");
        }
    });
}

function sendInvoiceDetail(data) {
    $.ajax({
        type: "POST",
        url: "invoice-detail/save",
        data: data,
        dataType: "HTML",
        success: function (response) {
            console.log("Detail saved successfully.");
        },
        error: function(data) {
            console.log("Detail could not have been saved.");
        }
    });
}

function getNewId() {
    $.ajax({
        type: "POST",
        url: "invoice/selectMax",
        success: function (data, textStatus, xhr) {
            $('#invoiceId').text(data+1);
        },
        error: function (response) {
            $('#invoiceId').text(1);
        }
    });
}

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

function requestArticle(row) {
    // get the code from the code column
    let code = $(row).find('.code').text();

    $.ajax({
        type: "POST",
        url: "article/select",
        data: {"code": code},
        dataType: "HTML",
        success: function (data, textStatus, xhr) {
            // if status code is 204, means article was not found
            if (xhr.status === 204) {
                // no article returned
                console.log("Article was not found");
                // add warning class to code cell
                $(row).find('.code').addClass('error');
            } else if (xhr.status === 200) {
                let article = JSON.parse(data); // cast json response to js object
                
                // fill the fields with article information
                $(row).find('.code').text(article.code);
                $(row).find('.article').text(article.name);
                $(row).find('.price').text(article.price);

                // remove warning class
                $(row).find('.code').removeClass('error');
            }
        },
        error: function(response) {
            // add warning class to code cell
            $(row).find('.code').addClass('error');
        }
    });
}