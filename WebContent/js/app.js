'use strict'

$(document).ready(init);

function init() {
    // show invoice form when new invoice button is pressed
    $('#newInvoice').click(invoiceFormPopUp);
    closeInvoiceFormPopUp();
    addLine();

    requestInvoices();
}

function invoiceFormPopUp() {
    $('#invoice-form').show();
    $('#nif').attr('contenteditable', '');
    //setAttributes();
}

function closeInvoiceFormPopUp() {
    let invoice = $('#invoice-form');
    // hide invoice form on click cancel
    $("#cancel").on("click", function() {
        invoice.hide();
    });
    // hide invoice form on click ESC
    $("body").keydown(function(event) {
        if (event.which == 27) {
            invoice.hide();
        }
    });

    // hide invoice form on click cross
    /*$(".tancar").on('click', function() {
        invoice.hide();
    });*/
}

/*function setAttributes() {
    $("#nif").attr("pattern", "^[0-9]{8}[A-Za-z]$");
    $("#code").attr("digit", "");
}*/

// Add new invoice line
function addLine() {
    $("#add-line").on("click", function() {
        $('#invoice-lines').append('<tr><td class="code text-center" contenteditable></td>' +
            '<td class="article"></td>' +
            '<td class="units text-right" contenteditable></td>' +
            '<td class="price text-right euro"></td>' +
            '<td class="subtotal text-right euro"></td>' +
            '<td class="action"><img class="delete-icon" src="./img/delete.svg" height="20px"></td></tr>');
    });
}

/**
 * Save a list of invoices to the table
 */
function addInvoices(invoices) {
    invoices.forEach(invoice => {
        var paid = "";
        if(invoice.paid) paid = "checked";
        $('#invoices-lines').append('<tr><td class="id">' + invoice.id + '</td>' +
                '<td class="date">' + invoice.date + '</td>'+
                '<td class="paid"><input type="checkbox" disabled ' + paid + '></td>'+
                '<td class="client">' + invoice.client_id + '</td>'+
                '<td class="taxableIncome text-right euro">' + invoice.taxable_base + '</td>'+
                '<td class="ivaImport text-right euro">' + invoice.iva + '</td>'+
                '<td class="total text-right euro">' + invoice.total + '</td>'+
                '<td class="actions"><img class="edit-icon" src="./img/edit.svg" height="20px">' +
                '<img class="delete-icon" src="./img/delete.svg" height="20px"></td></tr>');
    });
}