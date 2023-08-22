var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";
var empDBName = "EMP-DB";
var empRelationName = "HrRegisterData";

setBaseUrl(jpdbBaseURL);

function validateData() {
    var oldPassword = $("#oldpassowrd").val();
    var newPassword = $("#newpassword").val();
    var retypeNewPassword = $("#retypenewpassword").val();

    if (oldPassword === "") {
        alert("Old Password missing");
        $("#oldPassword").focus();
        return  "";
    }

    if (newPassword === "") {
        alert("New Password missing");
        $("#newpassword").focus();
        return  "";
    }

    if (retypeNewPassword === "") {
        alert("Re-typed New Password missing");
        $("#retypenewpassword").focus();
        return  "";
    }


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

    var pass = data.password;
    recordNo = JSON.parse(jsonObj.data).rec_no;

    if (pass !== oldPassword) {
        alert("Old Password not matched");
        return "";
    }

    if (newPassword !== retypeNewPassword) {
        alert("New Password  and Re-typed New password not matched");
        return "";
    }

    var jsonStrObj = {
        password: newPassword,
        retypepassword: newPassword
    };
    return JSON.stringify(jsonStrObj);
}

function changePassword() {

    var jsonChg = validateData();
    if (jsonChg === "") {
        return "";
    }
    var updateRequest = createUPDATERecordRequest(connToken, jsonChg, empDBName, empRelationName, recordNo);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(updateRequest, imlPartUrl);
    jQuery.ajaxSetup({async: true});
    eraseSession();
    window.location.assign('login.html');
}


var recordNo;