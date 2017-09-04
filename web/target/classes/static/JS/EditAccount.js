$(document).ready(function () {
    //add events onclick
    $("#addPersonalNumberBtn").on("click", addPersonalPhone);
    $("#addWorkNumberBtn").on("click", addWorkPhone);
    $("#editAccBtn").on("click", saveChanges);

    //add events onchange
    $("#UploadXML").on("change", uploadXml);

    //add datepicker for pick date
    $("#DateOfBirth").datepicker({
        dateFormat: "yy-mm-dd"
    });

    createAndAppointXMLForDownload();
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

function saveChanges() {
    var isCorrect = validatePhones();

    if (isCorrect) {
        $.confirm({
            title: "SAVE CHANGES?",
            content: "Do you want to save changes?",
            type: "green",
            buttons: {
                yes: {
                    text: "Yes",
                    btnClass: "btn-success",
                    action: function () {
                        $("#accountForm").submit();
                    }
                },
                cancel: {
                    text: "Cancel",
                    action: function () {

                    }
                }
            }
        });
    }
};

function uploadXml(e) {
    var files = e.target.files;
    var reader = new FileReader();

    reader.onload = function () {
        try {
            var parsed = new DOMParser().parseFromString(this.result, "text/xml");
            $("#Name").val(parsed.getElementsByTagName("name")[0].childNodes[0].nodeValue);
            $("#Surname").val(parsed.getElementsByTagName("surname")[0].childNodes[0].nodeValue);
            $("#MiddleName").val(parsed.getElementsByTagName("middleName")[0].childNodes[0].nodeValue);
            $("#DateOfBirth").val(parsed.getElementsByTagName("dateOfBirth")[0].childNodes[0].nodeValue);

            //work with phones
            var personalPhones = parsed.getElementsByTagName("personalPhone");
            deletePhones(document.getElementsByName("psPhoneDeleteBtn"));
            psPhoneId = 0;
            for (var i = 0, length = personalPhones.length; i < length; i++) {
                addPersonalPhone();
                var idOfAddedPersPhone = "#PersonalPhoneNumber" + psPhoneId;
                $(idOfAddedPersPhone).val(personalPhones[i].childNodes[0].nodeValue);
            }
            var workPhones = parsed.getElementsByTagName("workPhone");
            deletePhones(document.getElementsByName("workPhoneDeleteBtn"));
            wPhoneId = 0;
            for (var i = 0, length = workPhones.length; i < length; i++) {
                addWorkPhone();
                var idOfAddedWorkPhone = "#WorkPhoneNumber" + wPhoneId;
                $(idOfAddedWorkPhone).val(workPhones[i].childNodes[0].nodeValue);
            }

            $("#HomeAddress").val(parsed.getElementsByTagName("homeAddress")[0].childNodes[0].nodeValue);
            $("#WorkAddress").val(parsed.getElementsByTagName("jobAddress")[0].childNodes[0].nodeValue);
            $("#Email").val(parsed.getElementsByTagName("email")[0].childNodes[0].nodeValue);
            $("#ICQ").val(parsed.getElementsByTagName("icq")[0].childNodes[0].nodeValue);
            $("#Skype").val(parsed.getElementsByTagName("skype")[0].childNodes[0].nodeValue);
            $("#AddInfo").val(parsed.getElementsByTagName("additionalInfo")[0].childNodes[0].nodeValue);
        } catch (e) {
            $.dialog({
                title: "Incorrect data in XML!",
                content: e,
                type: "red",
            });
        }
    };

    reader.readAsText(files[0]);
};

function deletePhones(deleteBtnsOfPhones) {
    for (var i = 0, length = deleteBtnsOfPhones.length; i < length; i++) {
        deleteBtnsOfPhones[0].click();
    }
};

function createAndAppointXMLForDownload() {
    data = [];
    data.push("\<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    data.push("\<Account\>\n   ");
    data.push("\<name>" + document.getElementById('Name').value + "\</name>\n   ");
    data.push("\<surname>" + document.getElementById('Surname').value + "\</surname>\n   ");
    data.push("\<middleName>" + document.getElementById('MiddleName').value + "\</middleName>\n   ");
    data.push("\<dateOfBirth>" + document.getElementById('DateOfBirth').value + "\</dateOfBirth>\n   ");
    data.push("\<personalPhones>\n");
    var personalPhoneNumbers = document.getElementsByName("personalPhoneNumber");
    for (var i = 0, length = personalPhoneNumbers.length; i < length; i++) {
        data.push("      \<personalPhone>" + personalPhoneNumbers[i].value + "\</personalPhone>\n");
    }
    data.push("   \</personalPhones>\n   ");
    data.push("\<workPhones>\n");
    var workPhoneNumbers = document.getElementsByName("workPhoneNumber");
    for (var i = 0, length = workPhoneNumbers.length; i < length; i++) {
        data.push("      \<workPhone>" + workPhoneNumbers[i].value + "\</workPhone>\n");
    }
    data.push("   \</workPhones>\n   ");
    data.push("\<homeAddress>" + document.getElementById('HomeAddress').value + "\</homeAddress>\n   ");
    data.push("\<jobAddress>" + document.getElementById('WorkAddress').value + "\</jobAddress>\n   ");
    data.push("\<email>" + document.getElementById('Email').value + "\</email>\n   ");
    data.push("\<icq>" + document.getElementById('ICQ').value + "\</icq>\n   ");
    data.push("\<skype>" + document.getElementById('Skype').value + "\</skype>\n   ");
    data.push("\<additionalInfo>" + document.getElementById('AddInfo').value + "\</additionalInfo>\n");
    data.push("\</Account>");

    var properties = {type: 'plain/xml'};
    var file;
    try {
        // Specify the filename using the File constructor, but ...
        file = new File(data, "file.xml", properties);
    } catch (e) {
        // ... fall back to the Blob constructor if that isn't supported.
        file = new Blob(data, properties);
    }
    var url = URL.createObjectURL(file);
    document.getElementById("DownloadXML").href = url;
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

function prepareDangerText(txt) {
    var p = document.createElement("p");
    p.className = ("bg-danger");
    p.innerHTML = txt;
    p.setAttribute("name", "incorrectPhone");
    return p;
};

function deleteOldDangers(name) {
    var elements = document.getElementsByName(name);
    var length = elements.length;
    for (var i = 0; i < length; i++) {
        elements[0].remove();
    }
};


