'use strict'

function validateForm() {
    $("#send").click(function() {
        console.log("click send");
        var nif = $("#nif");
        var name = $("#clientName");
        var town = $("#town");
        var address = $("#address");

        if (nif.val() == "" || name.val() == "" || town.val() == "" || address.val() == "") {
            console.log("uep");
            nif.removeClass("error").addClass("error");
            name.removeClass("error").addClass("error");
            town.removeClass("error").addClass("error");
            address.removeClass("error").addClass("error");
        }
        if ($(":input").hasClass("error")) {
            alert("false");
        } else {
            alert("true");
        }
    });
}


function validateEmpties(field) {
    if ($(field).text() == "") {
        $(field).addClass("error");
        return false;
    } else {
        $(field).removeClass("error");
        return true;
    }
}


function validateCode(code) {
    if ($(code).val() == "") {
        $(code).addClass("error");
        return false;
    } else {
        $(code).removeClass("error");
        return true;
    }
}