$(document).ready(function () {

    $("#UploadXML").on("change", uploadXml);

    createAndAppointXMLForDownload();

});

function createAndAppointXMLForDownload() {
    data = [];
    data.push("\<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    data.push("\<Account\>\n   ");
    data.push("\<name>" + document.getElementById('Name').value + "\</name>\n   ");
    data.push("\<surname>" + document.getElementById('Surname').value + "\</surname>\n   ");
    data.push("\<middleName>" + document.getElementById('MiddleName').value + "\</middleName>\n   ");
    data.push("\<dateOfBirth>" + document.getElementById('DateOfBirth').value + "\</dateOfBirth>\n   ");
    data.push("\<personalPhones>\n");
    var personalPhoneNumbers = document.getElementsByName("personalPhoneNumbers");
    for (var i = 0, length = personalPhoneNumbers.length; i < length; i++) {
        data.push("      \<phone>" + personalPhoneNumbers[i].value + "\</phone>\n");
    }
    data.push("   \</personalPhones>\n   ");
    data.push("\<workPhones>\n");
    var workPhoneNumbers = document.getElementsByName("workPhoneNumbers");
    for (var i = 0, length = workPhoneNumbers.length; i < length; i++) {
        data.push("      \<phone>" + workPhoneNumbers[i].value + "\</phone>\n");
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

function uploadXml(e) {

    var files = e.target.files;
    var reader = new FileReader();
    reader.onload = function () {
        var parsed = new DOMParser().parseFromString(this.result, "text/xml");
        console.log(parsed);

        $("#fname").val(parsed.getElementsByTagName("firstName")[0].childNodes[0].nodeValue);
        $("#lname").val(parsed.getElementsByTagName("lastName")[0].childNodes[0].nodeValue);
        $("#mname").val(parsed.getElementsByTagName("middleName")[0].childNodes[0].nodeValue);
        $("#email").val(parsed.getElementsByTagName("email")[0].childNodes[0].nodeValue);
        $("#skype").val(parsed.getElementsByTagName("skype")[0].childNodes[0].nodeValue);
        $("#icq").val(parsed.getElementsByTagName("icq")[0].childNodes[0].nodeValue);
        $("#home").val(parsed.getElementsByTagName("homeAddress")[0].childNodes[0].nodeValue);
        $("#datepicker").val(parsed.getElementsByTagName("dateBirth")[0].childNodes[0].nodeValue);
        $("#add").val(parsed.getElementsByTagName("additional")[0].childNodes[0].nodeValue);
        $("#job").val(parsed.getElementsByTagName("jobAddress")[0].childNodes[0].nodeValue);
        var numbers = parsed.getElementsByTagName("ph");
        $("#dTable > li").remove();

        for (var i = 0, l = numbers.length; i < l; i++) {
            addRow(numbers[i].childNodes[0].nodeValue)
        }

    };
    reader.readAsText(files[0]);
}