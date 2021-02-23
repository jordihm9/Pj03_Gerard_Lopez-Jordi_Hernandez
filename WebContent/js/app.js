'use strict'

$(document).ready(init);

function init() {
    // check if new invoice button is pressed
    $('#newInvoice').click(() => {
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
    resetInvoiceForm();
    $('#invoice-form').show(); // show form

    // event listeners to close the pop up
    let invoice = $('#invoice-form');
    // hide invoice form on click cancel
    $("#cancel").on("click", function(ev) {
        ev.preventDefault(); // prevent reloading window
        close();
    });
    // hide invoice form on click ESC
    $("body").keydown(function(event) {
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

    //validateForm();

    //add content editable and today's date
    addContentEditable();

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

/**
 * Add an empty row at the bottom of the invoice details table
 */
function addInvoiceDetailLine() {
    if (!$('#paid').prop('checked')) {
        $('#invoice-lines').append($('<tr>')
            .append($('<td>').addClass('code text-center').attr('contenteditable', 'true')
                .attr('required', '')
                .on("input propertychange", function() {
                    validateEmpties(this);
                }))
            .append($('<td>').addClass('article'))
            .append($('<td>').addClass('units text-right').attr('contenteditable', 'true')
                .attr('type', 'number')
                .on("input propertychange", function() {
                    validateEmpties(this);
                    recalculatePrices(this);
                }))
            .append($('<td>').addClass('price text-right euro'))
            .append($('<td>').addClass('subtotal text-right euro'))
            .append($('<td>').addClass('action')
                .append($('<img>').addClass('delete-icon').prop('src', './img/delete.svg').height('20px')
                    .on('click', function(ev) { removeInvoiceDetailLine(ev); }))
            )
        );
    }
}

/**
 * Remove a invoice detail line
 * if invoice is paid, block action
 * @param {*} ev 
 */
function removeInvoiceDetailLine(ev) {
    // check if invoice is paid
    if (!$('#paid').prop('checked')) {
        // delete the row
        $(ev.target).parent().parent().remove();
    }
}

/**
 * Add contenteditable attribute in the create invoice option
 */
function addContentEditable() {
    if (!$('#paid').prop('checked')) {
        document.querySelector('#invoiceDate').valueAsDate = new Date();
        $('#nif').attr('contenteditable', true);
        $('#clientName').attr('contenteditable', true);
        $('#address').attr('contenteditable', true);
        $('#town').attr('contenteditable', true);
    }
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
            .append($('<td>').addClass('taxableBase text-right euro').text(invoice.taxableBase))
            .append($('<td>').addClass('ivaImport text-right euro').text(invoice.iva))
            .append($('<td>').addClass('total text-right euro').text(invoice.total))
            .append($('<td>').addClass('actions')
                .append($('<img>').addClass('edit-icon').prop('src', './img/edit.svg').height('20px')
                    .on('click', function(ev) {
                        ev.stopPropagation();
                        ev.stopImmediatePropagation();
                        // send request to get all information from the current invoice
                        requestInvoice(invoice.id);
                    }))
                .append($('<img>').addClass('delete-icon').prop('src', './img/delete.svg').height('20px'))
                .on('click', function(ev) {
                    ev.stopPropagation();
                    ev.stopImmediatePropagation();
                    // send request to delete the current invoice
                    if (confirm("Are you sure to delete this invoice?")) {
                        requestDelete(invoice.id);
                    }
                })
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

    var invoice = data.invoice; // get invoice data
    var details = data.details; // get invoice details data
    var client = invoice.client; // get client data

    var checkDisabled = false;
    if ($('#paid').prop('checked')) { checkDisabled = true; }

    // fill form with all data
    document.querySelector('#invoiceDate').valueAsDate = new Date(invoice.date);
    $('#invoiceId').text(invoice.id);
    $('#paid').prop('checked', invoice.paid).prop('disabled', checkDisabled);
    $('#nif').text(client.nif);
    $('#clientName').text(client.name + ' ' + client.lastname);
    $('#address').text(client.address);
    $('#town').text(client.town);
    $('#taxableBase').text(invoice.taxableBase);
    $('#total').text(invoice.total);
    $('#iva').text(invoice.iva);
    $('#ivaImport').text(invoice.ivaImport);
    $('#discount').text(invoice.discount);
    $('#discountImport').text(invoice.discountImport);

    // add each invoice detail as a new line
    details.forEach(line => {
        var article = line.article; // get article data

        // add a new row and append each cell
        $('#invoice-lines').append($('<tr>')
            .append($('<td>').addClass('code text-center').text(article.code)
                .attr('contenteditable',!invoice.paid)
                .on("input propertychange", function() {
                    validateEmpties(this);
                }))
            .append($('<td>').addClass('article').text(article.name))
            .append($('<td>').addClass('units text-right').text(line.total_articles)
                .attr('contenteditable',!invoice.paid)
                .on("input propertychange", function() {
                    validateEmpties(this);
                    recalculatePrices(this);
                }))
            .append($('<td>').addClass('price text-right euro').text(article.price))
            .append($('<td>').addClass('subtotal text-right euro').text(line.line_price))
            .append($('<td>').addClass('action')
                .append($('<img>').addClass('delete-icon').prop('src', './img/delete.svg').height('20px')
                    .on('click', function(ev) { removeInvoiceDetailLine(ev); }))
            )
        );

    });

}

/**
 * Reset the invoice form data
 */
function resetInvoiceForm() {
    $('#invoiceId').text(null);
    $('#invoiceDate').val(null);
    $('#nif').text(null);
    $('#clientName').text(null);
    $('#address').text(null);
    $('#town').text(null);
    $('#totalArticles').text(null);
    $('#discount').text(null);
    $('#discountImport').text(null);
    $('#iva').text(null);
    $('#ivaImport').text(null);
    $('#taxableBase').text(null);
    $('#total').text(null);
    cleanInvoiceDetails();
}

/**
 * Delete every line of the table invoices details
 */
function cleanInvoiceDetails() {
    $('#invoice-lines tr').each(function() {
        $(this).remove();
    });
}

/**
 * Delete every line of the invoices table
 */
function clearInvoicesTable() {
    $('#invoices-lines tr').each(function() {
        $(this).remove();
    })
}


/**
 * Calculate total articles price
 */
function recalculatePrices(val){
    var subtotal = $(val).parents("tr").find(".subtotal");
    var total = $('#totalArticles');
    total.text("0");
    subtotal.text(parseFloat($(val).parents("tr").find(".price").text()) * parseFloat($(val).text()).toFixed(2));
    if(subtotal.text() == 'NaN') subtotal.text("0");
    
    
    subtotal.on("input propertychange",function(){
        console.log("subtotal canviat!");
    });
    
}