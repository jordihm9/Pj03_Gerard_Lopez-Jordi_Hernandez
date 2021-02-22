'use strict'

function validateForm() {
    var code = $(".code");
    /*code.addClass("error").change(function (ev) {
        ev.stopPropagation();
        ev.stopImmediatePropagation();
        // send request to get all information from the current invoice
        validateCode(code);
    });*/
    //code.blur(validateCode(code));


    $("#send").click(function() {
        validateEmpties($("#nif"));
    });
    /*$("#send").click(validateEmpties($("#clientName")));
    $("#send").click(validateEmpties($("#address")));
    $("#send").click(validateEmpties($("#town")));
    $("#send").click(validateCode(code));*/
}

function validateEmpties(field){
    if(field.val() == "") {
        field.addClass("error");
        return false;
    } else {
        console.log("esta be");
        field.removeClass("error");
        return true;
    }      

}


function validateCode(code){
    console.log(code.text());
    if(code.text() == ""){
        code.addClass("error");
        return false;
    } else {
        code.removeClass("error");
        return true;
    }
}

function validar() {
    var nom = $("#clientName");
    if(nom.val() == ""){
        nom.addClass("error");
        return false;
    } else {
        nom.removeClass("error");
        return true;
    }
}