'use strict'

$(document).ready(init);

function init() {
    // show invoice form when new invoice button is pressed
    $('#newInvoice').click(invoiceFormPopUp);
    //console.log("hola?");
    //setAttributes();
    //closeInvoiceFormPopUp();
    
}

function invoiceFormPopUp() {
    $('#invoice-form').show();
}

/*function closeInvoiceFormPopUp(){
	let invoice = $('#invoice-form');
    $("#cancel").on("click", function(){
        invoice.hide();
    });
    $("#invoices-list").on('click',function(){
        invoice.hide();
    });
    $("body").keydown(function(event){
        if (event.which == 27) {
            invoice.hide();
        }
    });
    $(".tancar").on('click',function(){
        invoice.hide();
    });
}*/

/*function setAttributes () {
    document.getElementsById("nif").setAttribute("pattern","^[0-9]{8}[A-Za-z]$");
    document.getElementsByClassName("code").setAttribute("digit","");
}*/