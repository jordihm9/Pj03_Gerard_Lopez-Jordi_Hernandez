'use strict'

$(document).ready(init);

function init() {
    // check if new invoice button is pressed
    $('#newInvoice').click(()=> {
        invoiceFormPopUp();
        addInvoiceDetailLine(); // add an empty line
    });

    requestInvoices();
}

/**
 * Show the invoice form as a pop up
 * Activate the event listeners necessary to close it.
 * Turn them off when one of them has been activated.
 */
function invoiceFormPopUp() {
    $('#invoice-form').show(); // show form

    // event listeners to close the pop up
    let invoice = $('#invoice-form');
    // hide invoice form on click cancel
    $("#cancel").on("click", function (ev) {
        ev.preventDefault(); // prevent reloading window
        close();
    });
    // hide invoice form on click ESC
    $("body").keydown(function (event) {
        if (event.which == 27) {
            close();
        }
    });

    // hide invoice form on click cross
    /*$(".tancar").on('click', function() {
        close();
    });*/

    // add line on add line click
    $('#add-line').click(addInvoiceDetailLine);

    function close() {
        // remove events listeners
        $('#cancel').off('click');
        $('body').off('keydown');
        $('.tancar').off('click');
        $('#add-line').off('click');
        // clean table
        cleanInvoiceDetails();
        // hide popup
        invoice.hide();
    }
}

/*function setAttributes() {
    $("#nif").attr("pattern", "^[0-9]{8}[A-Za-z]$");
    $("#code").attr("digit", "");
}*/

/**
 * Add an empty row at the bottom of the invoice details table
 */
function addInvoiceDetailLine() {
    $('#invoice-lines').append($('<tr>')
        .append($('<td>').addClass('code text-center'))
        .append($('<td>').addClass('article'))
        .append($('<td>').addClass('units text-right'))
        .append($('<td>').addClass('price text-right euro'))
        .append($('<td>').addClass('subtotal text-right euro'))
        .append($('<td>').addClass('action')
            .append($('<img>').addClass('delete-icon').prop('src', './img/delete.svg').height('20px'))
        )
    );
}

/**
 * Save a list of invoices to the table
 */
function addInvoices(invoices) {
    invoices.forEach(invoice => {
        $('#invoices-lines').append($('<tr>')
            .append($('<td>').addClass('id').text(invoice.id))
            .append($('<td>').addClass('date').text(invoice.date))
            .append($('<td>').addClass('paid').append($('<input>')
                .prop('type', 'checkbox')
                .prop('disabled', true)
                .prop('checked', invoice.paid)
            ))
            .append($('<td>').addClass('client').text(invoice.client.lastname + ', ' + invoice.client.name))
            .append($('<td>').addClass('taxablaIncome text-right euro').text(invoice.taxable_base))
            .append($('<td>').addClass('ivaImport text-right euro').text(invoice.iva))
            .append($('<td>').addClass('total text-right euro').text(invoice.total))
            .append($('<td>').addClass('actions')
                .append($('<img>').addClass('edit-icon').prop('src', './img/edit.svg')
                    .height('20px').on('click', function (ev) {
                        ev.stopPropagation();
                        ev.stopImmediatePropagation();
                        var row = ev.target.parentNode.parentNode;
                        var id = parseInt(row.firstChild.innerText);
                        console.log(id);
                        requestInvoice(id);
                    }))
                .append($('<img>').addClass('delete-icon').prop('src', './img/delete.svg').height('20px'))
            )
        )
    });
}

/**
 * Fill the invoice form with the data passed as argument
 * @param {object} data include client data, invoice and each details data
 */
function fillFieldsInvoice(data) {
    invoiceFormPopUp();

    var invoice = data.invoice;     // get invoice data
    var details = data.details;     // get invoice details data
    var client = invoice.client;    // get client data

    // fill form with all data
    document.querySelector('#invoiceDate').valueAsDate = new Date(invoice.date);
    $('#invoiceId').text(invoice.id);
    $('#paid').prop('checked', invoice.paid);
    $('#nif').text(client.nif);
    $('#clientName').text(client.name + ' ' + client.lastname);
    $('#address').text(client.address);
    $('#town').text(client.town);
    $('#taxableBase').text(invoice.taxable_base);
    $('#total').text(invoice.total);
    $('#ivaImport').text(invoice.iva);
    $('#discountImport').text(invoice.discount);

    // add each invoice detail as a new line
    details.forEach(line => {
        var article = line.article;     // get article data

        // add a new row and append each cell
        $('#invoice-lines').append($('<tr>')
            .append($('<td>').addClass('code text-center').text(article.code))
            .append($('<td>').addClass('article').text(article.name))
            .append($('<td>').addClass('units text-right').text(line.total_articles))
            .append($('<td>').addClass('price text-right euro').text(article.price))
            .append($('<td>').addClass('subtotal text-right euro').text(line.line_price))
            .append($('<td>').addClass('action')
                .append($('<img>').addClass('delete-icon').prop('src', './img/delete.svg').height('20px'))
            )
        );

    });

}

/**
 * Delete every line of the table invoices details
 */
function cleanInvoiceDetails() {
    $('#invoice-lines tr').each(function () {
        $(this).remove();
    });
}