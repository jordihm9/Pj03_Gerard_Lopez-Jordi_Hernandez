'use strict'

function validateForm() {
    $("#invoice-form").submit(function(event) {
        event.preventDefault();
        let date = $('#invoiceDate');
        let nif = $("#nif");
        let name = $("#clientName");
        let town = $("#town");
        let address = $("#address");
        let discount = $('#discount');
        let iva = $('#iva');

        // check date is not before actual date
        let invoiceDate = new Date(date.val());
        let now = new Date(); 
        if ((invoiceDate.getDate() === now.getDate() && invoiceDate.getMonth() === now.getMonth() && invoiceDate.getFullYear() === now.getFullYear()) || now < invoiceDate) {
            $(date).parent().removeClass('error');
        } else {
            $(date).parent().addClass('error');
        }

        // check client information
        nif.text() === '' || !/^[0-9]{8}[A-Z]{1}$/.test(nif.text()) ? nif.addClass('error') : nif.removeClass('error');
        name.text() === '' ? name.addClass('error') : name.removeClass('error');
        address.text() === '' ? address.addClass('error') : address.removeClass('error');
        town.text() === '' ? town.addClass('error') : town.removeClass('error');

        // check discount & iva
        discount.text() === '' || isNaN(discount.text()) ? discount.addClass('error') : discount.removeClass('error');
        iva.text() === '' || isNaN(iva.text()) ? iva.addClass('error') : iva.removeClass('error');

        // check there is at least 1 invoice detail
        if ($('#invoice-lines tr').length === 0) {
            return false;
        } else {
            $('#invoice-lines tr').each(function (index, element) {
                $(this).find('.code').text() === '' ? $(this).find('.code').addClass('error') : $(this).find('.code').removeClass('error');
                $(this).find('.units').text() === '' ? $(this).find('.units').addClass('error') : $(this).find('.units').removeClass('error');
            });
        }

        // check if there is any element with the 'error' class
        if ($('.error').length === 0) {
            console.log('saving invoice');
            // get the invoice id
            let invoiceId = $('#invoiceId').text();

            let data = {
                "nif": nif.text(),
                "clientName": name.text(),
                "address": address.text(),
                "town": town.text(),
                "invoiceId": invoiceId,
                "invoiceDate": date.val(),
                "paid": $('#paid').prop('checked') ? 1 : 0,
                "taxableBase": $('#taxableBase').text(),
                "iva": $('#iva').text(),
                "ivaImport": $('#ivaImport').text(),
                "discount": $('#discount').text(),
                "discountImport": $('#discountImport').text(),
                "total": $('#total').text()
            };

            sendInvoice(data); // save invoice to DB

            // save each detail line
            $('#invoice-lines tr').each(function (index, element) {
                console.log('saving invoice line: '+ (index+1));
                data = {
                    "lineNumber": index+1,
                    "totalArticles": $(this).find('.units').text(),
                    "linePrice": $(this).find('.subtotal').text(),
                    "articleCode": $(this).find('.code').text(),
                    "invoiceId": invoiceId
                };

                sendInvoiceDetail(data);
            });

            $('#invoice-form').hide();
        }
    });

    $('#invoiceDate').on('change focus', function () {
        let date = $(this);
        // check date is not before actual date
        let invoiceDate = new Date(date.val());
        let now = new Date(); 
        if ((invoiceDate.getDate() === now.getDate() && invoiceDate.getMonth() === now.getMonth() && invoiceDate.getFullYear() === now.getFullYear()) || now < invoiceDate) {
            $(date).parent().removeClass('error');
        } else {
            $(date).parent().addClass('error');
        }
    });

    $('#discount').on('input propertychange', function () {
        $(this).text() === '' || isNaN($(this).text()) ? $(this).addClass('error') : $(this).removeClass('error');
    });

    $('#iva').on('input propertychange', function () {
        $(this).text() === '' || isNaN($(this).text()) ? $(this).addClass('error') : $(this).removeClass('error');
    });

    $('#nif').on('input propertychange', function () {
        $(this).text() === '' || !/^[0-9]{8}[A-Z]{1}$/.test($(this).text()) ? $(this).addClass('error') : $(this).removeClass('error');
    });

    $('#clientName').on('input propertychange', function () {
        $(this).text() === '' ? $(this).addClass('error') : $(this).removeClass('error');
    });

    $('#address').on('input propertychange', function () {
        $(this).text() === '' ? $(this).addClass('error') : $(this).removeClass('error');
    });

    $('#town').on('input propertychange', function () {
        $(this).text() === '' ? $(this).addClass('error') : $(this).removeClass('error');
    });
}

function validateEmpties(field) {
    if ($(field).text() == "" || $(field).text() == "0" || isNaN($(field).text())) {
        $(field).addClass("error");
        return false;
    } else {
        $(field).removeClass("error");
        return true;
    }
}