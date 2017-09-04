$(document).ready(function () {
    //add events onclick
    $("#addPersonalNumberBtn").on("click", addPersonalPhone);
    $("#addWorkNumberBtn").on("click", addWorkPhone);
    $("#createBtn").on("click", createAccount);

    //add datepicker for pick date
    $("#DateOfBirth").datepicker({
        dateFormat: "yy-mm-dd"
    });
});

var psPhoneId = 0;
function addPersonalPhone() {
    psPhoneId++;
    var id = 'psPhone' + psPhoneId;
    var html = '<div class="input-group phone_param"> <input id="PersonalPhoneNumber' + psPhoneId + '" name="personalPhoneNumbers"' +
        'type="text" class="form-control" placeholder="Enter personal phone number"><span class="input-group-btn">' +
        '<button name="psPhoneDeleteBtn" id="psPhoneDeleteBtn' + psPhoneId + '" class="btn btn-success" type="button" ' +
        'onclick="javascript:removeElementsOrElement(' + id + '); return false;">Delete' + '</button></span></div>';
    addElement('addPersonalNumberBtn', id, html);
};

var wPhoneId = 0;
function addWorkPhone() {
    wPhoneId++;
    var id = 'wPhone' + wPhoneId;
    var html = '<div class="input-group phone_param"> <input id="WorkPhoneNumber' + wPhoneId + '" name="workPhoneNumbers"' +
        'type="text" class="form-control" placeholder="Enter work phone number"><span class="input-group-btn">' +
        '<button name="workPhoneDeleteBtn" id="workPhoneDeleteBtn' + wPhoneId + '" class="btn btn-success" type="button" ' +
        'onclick="javascript:removeElementsOrElement(' + id + '); return false;">Delete' + '</button></span></div>';
    addElement('addWorkNumberBtn', id, html);
};

function addElement(idOfElInsertBefore, elementId, html) {
    // Adds an element to the document
    var btn = document.getElementById(idOfElInsertBefore);
    var newElement = document.createElement('p');
    newElement.className = "phone_param";
    newElement.id = elementId;
    newElement.innerHTML = html;
    btn.parentNode.insertBefore(newElement, btn);
};

function removeElementsOrElement(elements) {
    if (Array.isArray(elements)) {
        var i = 0;
        while (elements[i]) {
            elements[i++].remove();
        }
    } else {
        elements.remove();
    }
};

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#changedAvatar')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
};

function createAccount() {
    var isCorrect = validatePhones();

    if (isCorrect) {
        $("#accountForm").submit();
    }
};

function validatePhones() {
    deleteOldDangers("incorrectPhone");
    var regEx = /^\+[0-9][(]([0-9]{3})[)]([0-9]{3})[-]([0-9]{2})[-]([0-9]{2})$/;
    var persPhones = document.getElementsByName("personalPhoneNumber");
    var workPhones = document.getElementsByName("workPhoneNumber");
    var isCorrect = true;
    for (var i = 0; i < persPhones.length; i++) {
        if (persPhones[i].value.length > 0 && !persPhones[i].value.match(regEx)) {
            isCorrect = false;
            var p = prepareDangerText("Incorrect phone. Pattern is +X(XXX)XXX-XX-XX");
            $(p).insertAfter(persPhones[i].parentNode);
        }
    }
    for (var i = 0; i < workPhones.length; i++) {
        if (workPhones[i].value.length > 0 && !workPhones[i].value.match(regEx)) {
            isCorrect = false;
            var p = prepareDangerText("Incorrect phone. Pattern is +X(XXX)XXX-XX-XX");
            $(p).insertAfter(workPhones[i].parentNode);
        }
    }

    if (!isCorrect) {
        $.dialog({
            title: "Incorrect data!",
            content: "Incorrect phones.",
            type: "red",
        });
    }

    return isCorrect;
};

function deleteOldDangers(name) {
    var elements = document.getElementsByName(name);
    var length = elements.length;
    for (var i = 0; i < length; i++) {
        elements[0].remove();
    }
};

function prepareDangerText(txt) {
    var p = document.createElement("p");
    p.className = ("bg-danger");
    p.innerHTML = txt;
    p.setAttribute("name", "incorrectPhone");
    return p;
};