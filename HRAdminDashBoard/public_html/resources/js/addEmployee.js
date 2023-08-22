var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90931405|-31949321676913140|90950398";

var jpdbIRL = "/api/irl";
var jpdbIML = "/api/iml";
var empDBName = "EMP-DB";
var empRelationName = "EmpData";

setBaseUrl(jpdbBaseURL);

function disableCtrl(ctrl) {
    $('#new').prop("disabled", ctrl);
    $('#save').prop("disabled", ctrl);
    $('#edit').prop("disabled", ctrl);
    $('#change').prop("disabled", ctrl);
    $('#reset').prop("disabled", ctrl);
}

function disableNav(ctrl) {
    $("#first").prop("disabled", ctrl);
    $("#prev").prop("disabled", ctrl);
    $("#next").prop("disabled", ctrl);
    $("#last").prop("disabled", ctrl);
}

function disableForm(bValue) {
    $("#empid").prop("disabled", bValue);
    $("#empname").prop("disabled", bValue);
    $("#empsal").prop("disabled", bValue);
    $("#hra").prop("disabled", bValue);
    $("#da").prop("disabled", bValue);
    $("#deduct").prop("disabled", bValue);
}

function initEmpForm() {
    localStorage.removeItem("first_rec_no");
    localStorage.removeItem("last_rec_no");
    localStorage.removeItem("rec_no");

    console.log("initEmpForm() - done");
}

function setCurrRecNo2LS(jsonObj) {
    var data = JSON.parse(jsonObj.data);
    localStorage.setItem("rec_no", data.rec_no);
}

function getCurrRecNoFromLS(jsonObj) {
    return localStorage.getItem("rec_no");
}

function setFirstRecNo2LS(jsonObj) {
    var data = JSON.parse(jsonObj.data);
    if (data.rec_no === undefined) {
        localStorage.setItem("first_rec_no", "0");
    } else {
        localStorage.setItem("first_rec_no", data.rec_no);
    }
}

function getFirstRecNoFromLS() {
    return localStorage.getItem("first_rec_no");
}

function setLastRecNo2LS(jsonObj) {
    var data = JSON.parse(jsonObj.data);
    if (data.rec_no === undefined) {
        localStorage.setItem("last_rec_no", "0");
    } else {
        localStorage.setItem("last_rec_no", data.rec_no);
    }
}

function getLastRecNoFromLS() {
    return localStorage.getItem("last_rec_no");
}


function showData(jsonObj) {
    if (jsonObj.status === 400)
        return;
    var data = (JSON.parse(jsonObj.data)).record;
    setCurrRecNo2LS(jsonObj);
    $("#empid").val(data.id);
    $("#empname").val(data.name);
    $("#empsal").val(data.salary);
    $("#hra").val(data.hra);
    $("#da").val(data.da);
    $("#deduct").val(data.deduction);

    disableNav(false);
    disableForm(true);

    $('#save').prop("disabled", true);
    $('#change').prop("disabled", true);
    $('#reset').prop("disabled", true);

    $('#new').prop("disabled", false);
    $('#edit').prop("disabled", false);

    if (getCurrRecNoFromLS() === getLastRecNoFromLS()) {
        $('#next').prop("disabled", true);
        $('#last').prop("disabled", true);
    }

    if (getCurrRecNoFromLS() === getFirstRecNoFromLS()) {
        $('#prev').prop("disabled", true);
        $('#first').prop("disabled", true);
    }
}

function newForm() {
    makeDataFormEmpty();

    disableForm(false);
    $("#empid").focus();
    disableNav(true);
    disableCtrl(true);

    $("#save").prop("disabled", false);
//    $("#reset").prop("disabled", false);
}

function makeDataFormEmpty() {
    $("#empid").val("");
    $("#empname").val("");
    $("#empsal").val("");
    $("#hra").val("");
    $("#da").val("");
    $("#deduct").val("");
}

function saveData() {
    var jsonStrObj = validateData();
    if (jsonStrObj === "")
        return "";

    var putRequest = createPUTRequest(connToken, jsonStrObj, empDBName, empRelationName);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(putRequest, imlPartUrl);
    jQuery.ajaxSetup({async: true});

    if (isNoRecordPresentLS())
        setFirstRecNo2LS(jsonObj);
    setLastRecNo2LS(jsonObj);
    setCurrRecNo2LS(jsonObj);
    resetForm();
}

function resetForm() {
    disableCtrl(true);
    disableNav(false);

    var getCurRequest = createGET_BY_RECORDRequest(connToken, empDBName, empRelationName, getCurrRecNoFromLS());
    jQuery.ajaxSetup({async: false});
    var result = executeCommand(getCurRequest, irlPartUrl);
    jQuery.ajaxSetup({async: false});
    showData(result);

    if (isOnlyOneRecordPresent() || isNoRecordPresentLS())
        disableNav(true);

    $("#new").prop("disabled", false);
    if (isNoRecordPresentLS()) {
        makeDataFormEmpty();
        $("#edit").prop("disabled", true);
    } else {
        $("#edit").prop("disabled", false);
    }
    disableForm(true);
}

function validateData() {
    var empid, empname, empsal, hra, da, deduct;
    empid = $("#empid").val();
    empname = $("#empname").val();
    empsal = $("#empsal").val();
    hra = $("#hra").val();
    da = $("#da").val();
    deduct = $("#deduct").val();

    if (empid === "") {
        alert("Employee ID missing");
        $("#empid").focus();
        return  "";
    }

    if (empname === "") {
        alert("Employee Name missing");
        $("#empname").focus();
        return  "";
    }

    if (empsal === "") {
        alert("Employee Salary missing");
        $("#empsal").focus();
        return  "";
    }

    if (hra === "") {
        alert("Employee HRA missing");
        $("#hra").focus();
        return  "";
    }

    if (da === "") {
        alert("Employee DA missing");
        $("#da").focus();
        return  "";
    }

    if (deduct === "") {
        alert("Employee Deduction missing");
        $("#deduct").focus();
        return  "";
    }

    var jsonStrObj = {
        id: empid,
        name: empname,
        salary: empsal,
        hra: hra,
        da: da,
        deduction: deduct
    };

    return JSON.stringify(jsonStrObj);

}

function changeData() {
    jsonChg = validateData();
    var updateRequest = createUPDATERecordRequest(connToken, jsonChg, empDBName, empRelationName, getCurrRecNoFromLS());
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommandAtGivenBaseUrl(updateRequest, jpdbBaseURL, jpdbIML);
    jQuery.ajaxSetup({async: true});
    console.log(jsonObj);
    resetForm();
//    $("#empid").focus();
//    $("#edit").focus();
}

function isNoRecordPresentLS() {
    if (getFirstRecNoFromLS() === "0" && getLastREcNoFromLS() === "0") {
        return true;
    }
    return false;
}

function isOnlyOneRecordPresent() {
    if (isNoRecordPresentLS())
        return false;

}

function editData() {
    disableForm(false);

    $("#empid").prop("disabled", true);
    $("#empname").focus();
    disableNav(true);
    disableCtrl(true);
    $("#change").prop("disabled", false);
    $("#reset").prop("disabled", false);
}

function getFirst() {
    var getFirstRequest = createFIRST_RECORDRequest(connToken, empDBName, empRelationName);
    jQuery.ajaxSetup({async: false});
    var result = executeCommand(getFirstRequest, irlPartUrl);
    jQuery.ajaxSetup({async: true});
    showData(result);
    setFirstRecNo2LS(result);

    $("#empid").prop("disabled", true);
    $("#first").prop("disabled", true);
    $("#prev").prop("disabled", true);
    $("#next").prop("disabled", false);
    $("#save").prop("disabled", true);
}

function getPrev() {
    var r = getCurrRecNoFromLS();
    if (r === 1) {
        $("#prev").prop("disabled", true);
        $("#first").prop("disabled", true);
    }

    var getPrevRequest = createPREV_RECORDRequest(connToken, empDBName, empRelationName, r);
    jQuery.ajaxSetup({async: false});
    var result = executeCommand(getPrevRequest, irlPartUrl);
    showData(result);
    jQuery.ajaxSetup({async: true});
    var r = getCurrRecNoFromLS();
    if (r === 1) {
        $("#prev").prop("disabled", true);
        $("#first").prop("disabled", true);
    }
    $("#save").prop("disabled", true);
}

function getNext() {
    var r = getCurrRecNoFromLS();

    var getPrevRequest = createNEXT_RECORDRequest(connToken, empDBName, empRelationName, r);
    jQuery.ajaxSetup({async: false});
    var result = executeCommand(getPrevRequest, irlPartUrl);
    showData(result);
    jQuery.ajaxSetup({async: true});
    $("#save").prop("disabled", true);
}


function getLast() {
    var getLastRequest = createLAST_RECORDRequest(connToken, empDBName, empRelationName);
    jQuery.ajaxSetup({async: false});
    var result = executeCommand(getLastRequest, irlPartUrl);
    showData(result);
    setLastRecNo2LS(result);
    jQuery.ajaxSetup({async: true});

    $("#first").prop("disabled", false);
    $("#prev").prop("disabled", false);
    $("#last").prop("disabled", true);
    $("#next").prop("disabled", true);
    $("#save").prop("disabled", true);
}

function isOnlyOneRecordPresent() {
    if (isNoRecordPresentLS())
        return false;
    if (getFirstRecNoFromLS() === getLastRecNoFromLS())
        return true;
    return false;
}


function checkForNoOrOneRecord() {
    if (isNoRecordPresentLS()) {
        disableForm(true);
        disableNav(true);
        disableCtrl(true);
        $("#new").prop("disabled", true);
        return;
    }

    if (isOnlyOneRecordPresent()) {
        disableForm(true);
        disableNav(true);
        disableCtrl(true);
        $("#new").prop("disabled", true);
        $("#edit").prop("disabled", true);
        return;
    }
}

initEmpForm();
getFirst();
getLast();
checkForNoOrOneRecord();