var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";
var empDBName = "EMP-DB";
var empRelationName = "HrRegisterData";
var sessionRelationName = "SessionRelation";

setBaseUrl(jpdbBaseURL);

function resetLogin() {
    $("#email").val("");
    $("#password").val("");
}

function validateData() {
    var email, password;
    email = $("#email").val();
    password = $("#password").val();

    if (email === "") {
        alert("Email missing");
        $("#email").focus();
        return  "";
    }

    if (password === "") {
        alert("Password missing");
        $("#password").focus();
        return  "";
    }

    var jsonStrEmailObj = {
        email: email
    }

    var jsonEmailObj = JSON.stringify(jsonStrEmailObj);
    var getByKeyRequest = createGET_BY_KEYRequest(connToken, empDBName, empRelationName, jsonEmailObj);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(getByKeyRequest, irlPartUrl);
    jQuery.ajaxSetup({async: true});
    if (jsonObj.status === 400) {
        alert("You are not registered");
        resetLogin();
        return "";
    }
    var data = (JSON.parse(jsonObj.data)).record;
    var pass = data.password;
    if (pass !== password) {
        alert("Incorrect Password");
        resetLogin();
        return "";
    }
    return email;
}

function redirect() {
    window.location.assign('home.html');
}


function login() {
    event.preventDefault();
    var email = validateData();
    if (email === "")
        return "";
    resetLogin();
    jQuery.ajaxSetup({async: false});
    var getSessionTokenStatus = createJpdbSessionToken(connToken, 1, empDBName, sessionRelationName, email);
    jQuery.ajaxSetup({async: true});
    if (getSessionTokenStatus === 400) {
        alert("Login Unsuccessful!!!");
        return;
    }
//    localStorage.setItem("email", email);
    window.location.assign('home.html');
}

$("#email").focus();