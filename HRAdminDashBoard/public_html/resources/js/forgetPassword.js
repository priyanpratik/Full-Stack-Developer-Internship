var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";
var empDBName = "EMP-DB";
var empRelationName = "HrRegisterData";

setBaseUrl(jpdbBaseURL);

function validateData() {
    var email, password;
    email = $("#email").val();
    password = $("#password").val();

    if (email === "") {
        alert("Email missing");
        $("#email").focus();
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

    recordNo = JSON.parse(jsonObj.data).rec_no;
    return email;
}

function generatePass() {
    var pass = '';
    var str = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' +
            'abcdefghijklmnopqrstuvwxyz0123456789@#$';

    for (var i = 1; i <= 8; i++) {
        var char = Math.floor(Math.random()
                * str.length + 1);

        pass += str.charAt(char)
    }

    return pass;
}

function sendMail() {
    event.preventDefault();
    var email = validateData();
    if (email === "")
        return "";
    var randomPassword = generatePass();
    var jsonStrObj = {
        password: randomPassword,
        retypepassword: randomPassword
    };
    var jsonChg = JSON.stringify(jsonStrObj);

    var updateRequest = createUPDATERecordRequest(connToken, jsonChg, empDBName, empRelationName, recordNo);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(updateRequest, imlPartUrl);
    jQuery.ajaxSetup({async: true});

    var emailContent = "You new requested password is " + randomPassword + " Please Change it whenever you login!!!";
    var emailSubject = "Password Change Request";
    var jsonStr = {
        emailTo: email,
        emailSubject: emailSubject,
        emailContent: emailContent
    }

    var jsonEmailObj = JSON.stringify(jsonStr);
    var emailObjReq = createEmailToSendReq(connToken, jsonEmailObj);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(emailObjReq, "/serverless/send_email");
    jQuery.ajaxSetup({async: true});
    window.location.assign('login.html');
}

var recordNo;

