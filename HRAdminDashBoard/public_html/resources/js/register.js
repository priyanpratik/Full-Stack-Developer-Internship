var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";
var empDBName = "EMP-DB";
var empRelationName = "HrRegisterData";

setBaseUrl(jpdbBaseURL);

function resetFormRegister() {
    $("#name").val("");
    $("#email").val("");
    $("#phone").val("");
    $("#password").val("");
    $("#retypepassword").val("");
    $("#name").focus();
}



function validateData() {
    var name, email, phone, password, retypepassword;
    name = $("#name").val();
    email = $("#email").val();
    phone = $("#phone").val();
    password = $("#password").val();
    retypepassword = $("#retypepassword").val();

    if (name === "") {
        alert("Name missing");
        $("#name").focus();
        return  "";
    }

    if (email === "") {
        alert("Email missing");
        $("#email").focus();
        return  "";
    }

    if (phone === "") {
        alert("Phone No missing");
        $("#phone").focus();
        return  "";
    }

    if (password === "") {
        alert("Password missing");
        $("#password").focus();
        return  "";
    }

    if (retypepassword === "") {
        alert("Re-type Passowrd missing");
        $("#retypepassword").focus();
        return  "";
    }

    if (retypepassword !== password) {
        alert("Password and Re-type Passowrd mismatch");
        $("#password").focus();
        return  "";
    }

    var jsonStrObj = {
        name: name,
        email: email,
        phone: phone,
        password: password,
        retypepassword: retypepassword
    };

    return JSON.stringify(jsonStrObj);

}


function saveDataRegister() {
    event.preventDefault();
    var jsonStrObj = validateData();
    if (jsonStrObj === "")
        return "";

    var putRequest = createPUTRequest(connToken, jsonStrObj, empDBName, empRelationName);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(putRequest, imlPartUrl);
    jQuery.ajaxSetup({async: true});

    resetFormRegister();
    window.location.assign('login.html');

}

function checkEmail() {
    var email = $("#email").val();
    var jsonStrEmailObj = {
        email: email
    }

    var jsonEmailObj = JSON.stringify(jsonStrEmailObj);
    var getByKeyRequest = createGET_BY_KEYRequest(connToken, empDBName, empRelationName, jsonEmailObj);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(getByKeyRequest, irlPartUrl);
    jQuery.ajaxSetup({async: true});

    if (jsonObj.status == 200) {
        alert("You are already registered!!!");
        resetFormRegister();
        return;
    }
}

$("#name").focus();
