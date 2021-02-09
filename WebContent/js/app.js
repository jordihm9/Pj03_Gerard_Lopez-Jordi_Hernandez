'use strict'

$(document).ready(init);

function init() {
    // show invoice form when new invoice button is pressed
    $('#newInvoice').click(invoiceFormPopUp);
}

function invoiceFormPopUp() {
    $('#invoice-form').show();
}