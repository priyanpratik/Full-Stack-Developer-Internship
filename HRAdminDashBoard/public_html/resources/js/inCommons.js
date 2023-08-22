var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";
var empDBName = "EMP-DB";
var sessionRelationName = "SessionRelation";

function loadFooter() {
    $("#footer").load("resources/footer.html");
}

function loadHeader() {
    $("#header").load("resources/header.html");
}

function eraseSession() {
    jQuery.ajaxSetup({async: false});
    var tokenStatus = removeSessionTokenFromJPDB(connToken, empDBName, sessionRelationName);
    jQuery.ajaxSetup({async: true});
}

function checkStatus() {
    jQuery.ajaxSetup({async: false});
    var tokenStatus = isJpdbSessionTokenExists(connToken, empDBName, sessionRelationName);
    jQuery.ajaxSetup({async: true});
    if (tokenStatus !== 200) {
        window.location.assign('login.html');
    }
}

checkStatus();




