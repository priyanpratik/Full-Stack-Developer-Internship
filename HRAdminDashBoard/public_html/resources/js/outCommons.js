var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";
var empDBName = "EMP-DB";
var sessionRelationName = "SessionRelation";


function checkStatus() {
    jQuery.ajaxSetup({async: false});
    var tokenStatus = isJpdbSessionTokenExists(connToken, empDBName, sessionRelationName);
    jQuery.ajaxSetup({async: true});

    if (tokenStatus === 200) {
        window.location.assign('home.html');
    }
}

checkStatus();