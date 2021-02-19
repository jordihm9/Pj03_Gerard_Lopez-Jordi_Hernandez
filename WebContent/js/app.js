'use strict'

$(document).ready(init);

function init() {
    invoiceFormPopUp(); // detect when to open or close form pop up

    requestInvoices();
}

function invoiceFormPopUp() {
    // check if new invoice button is pressed
    $('#newInvoice').click(function (e) {
        // show form
        $('#invoice-form').show();
        addInvoiceDetailLine();

        // event listeners to close the pop up
        let invoice = $('#invoice-form');
        // hide invoice form on click cancel
        $("#cancel").on("click", function () {
            invoice.hide();
            removeCloseEvents();
        });
        // hide invoice form on click ESC
        $("body").keydown(function (event) {
            if (event.which == 27) {
                invoice.hide();
                removeCloseEvents();
            }
        });

        // hide invoice form on click cross
        /*$(".tancar").on('click', function() {
            invoice.hide();
            removeCloseEvents();
        });*/

        // add line on add line click
        $('#add-line').click(addInvoiceDetailLine);
    });

    function removeCloseEvents() {
        $('#cancel').off('click');
        $('body').off('keydown');
        $('.tancar').off('click');
        $('#add-line').off('click');
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
                .height('20px').on('click', function(ev){
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


function fillFieldsInvoice(data) {
    var invoice = data.invoice;
    var details = data.details;
    var client = invoice.client;
    $('#invoice-form').show();

    // add line on add line click
    $('#add-line').click(addInvoiceDetailLine);
    $('#invoiceId').text(invoice.id);
    $('#paid').prop('checked', invoice.paid);
    $('#nif').text(client.nif);
    $('#clientName').text(client.name + ' ' + client.lastname);
    $('#address').text(client.address);
    $('#town').text(client.town);
    $('#taxableBase').text(invoice.taxable_base);
    $('#total').text(invoice.total);
    $('#ivaImport').text(invoice.iva);
    //$('#invoiceDate').val(invoice.date.replace(/^(\d{2})\.(\d{2})\.(\d{4})$/g,'$3-$2-$1'));
    $('#discountImport').text(invoice.discount);

    details.forEach(line => {
        var article = line.article;
        $('#invoice-lines').append($('<tr>')
        .append($('<td>').addClass('code text-center').text(article.code))
        .append($('<td>').addClass('article').text(article.name))
        .append($('<td>').addClass('units text-right'))
        .append($('<td>').addClass('price text-right euro').text(article.price))
        .append($('<td>').addClass('subtotal text-right euro').text(line.line_price))
        .append($('<td>').addClass('action')
            .append($('<img>').addClass('delete-icon').prop('src', './img/delete.svg').height('20px'))
        )
    );
        
    });

}