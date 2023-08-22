var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";
var empDBName = "EMP-DB";
var empRelationName = "HrRegisterData";

setBaseUrl(jpdbBaseURL);

function controlForm(value) {
    $("#name").prop("disabled", value);
    $("#email").prop("disabled", value);
    $("#phone").prop("disabled", value);
}

function initialise() {
    var email = localStorage.getItem("userID");

    var jsonStrEmailObj = {
        email: email
    }

    var jsonEmailObj = JSON.stringify(jsonStrEmailObj);
    var getByKeyRequest = createGET_BY_KEYRequest(connToken, empDBName, empRelationName, jsonEmailObj);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(getByKeyRequest, irlPartUrl);
    jQuery.ajaxSetup({async: true});
    var data = (JSON.parse(jsonObj.data)).record;
    var name = data.name;
    var phone = data.phone;
    recordNo = JSON.parse(jsonObj.data).rec_no;

    $("#email").val(email);
    $("#name").val(name);
    $("#phone").val(phone);
}

function validateData() {
    var email = $("#email").val();
    var name = $("#name").val();
    var phone = $("#phone").val();

    if (name === "") {
        alert("Name missing");
        $("#name").focus();
        return  "";
    }

    if (phone === "") {
        alert("Phone no missing");
        $("#phone").focus();
        return  "";
    }

    var jsonStrObj = {
        email: email,
        name: name,
        phone: phone
    };

    return JSON.stringify(jsonStrObj);
}

function saveProfile() {
    var jsonChg = validateData();
    if (jsonChg === "") {
        return "";
    }

    var updateRequest = createUPDATERecordRequest(connToken, jsonChg, empDBName, empRelationName, recordNo);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(updateRequest, imlPartUrl);
    jQuery.ajaxSetup({async: true});
    initialise();
}

function editProfile() {
    event.preventDefault();
    controlForm(false);
    $("#edit").prop("disabled", true);
    $("#email").prop("disabled", true);
    $("#save").prop("disabled", false);
}


$("#save").prop("disabled", true);
controlForm(true);
initialise();
var recordNo;