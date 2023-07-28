/* 
 *  
 * Copyright Notice
 * 
 * This source code file is created by Hemant Kumar Dugar and is his sole property. 
 * Hemant Kumar Dugar  reserves the rights to all information contained within this 
 * source code file. Any copying of this source code file, in whole or in part including 
 * the concept, idea and logic explained herein by any means for any purpose, 
 * without the expressed written consent of Hemant Kumar Dugar is a violation 
 * of copyright law. 
 * 
 * Copyright © 2018 - Hemant Kumar Dugar - All Rights Reserved
 */

// REMEMBER
// This jpdb-commons.js and all other in this folder is shared resources for
// application development for JsonPowerDB 
// 
// Main file(s) in this folder to be modified for any new feature implementation
//

var baseUrl = "http://api.login2explore.com:5577";

//To set the baseUrl 
function setBaseUrl(baseUrlArg) {
    baseUrl = baseUrlArg;
}

var imlPartUrl = "/api/iml";
var irlPartUrl = "/api/irl";
var islPartUrl = "/api/isl";

var FILE_STATUS_OK = "OK";
var FILE_STATUS_EOF = "EOF";
var FILE_STATUS_BOF = "BOF";
var RELATION_IS_EMPTY = "RELATION_IS_EMPTY";
var DATA_HAS_BEEN_RETRIEVED_FROM_PI = "DATA_HAS_BEEN_RETRIEVED_FROM_PI";
var INVALID_RECORD = "INVALID_RECORD";
var DATA_NOT_FOUND = "DATA_NOT_FOUND";
var SUCCESS = "Success";
var FAILURE = "Faliure";
var COLUMN_EXIST = "COLUMN EXIST";
var COLUMN_DOES_NOT_EXIST = "COLUMN DOES NOT EXIST";
var RES_STATUS_SUCCESS = 200;
var RES_STATUS_FAILURE = 400;
var TRUE = "true";
var FALSE = "false";
//REMEMBER - this constant used in JPDBObject in GETALL command, so if there is any changes in GETALL command in JSONPowerDB
// then we have to upadate this constant also.
var RELATION_DOES_NOT_EXIST ="RELATION DOES NOT EXIST";

// To send and add form data to JPDB instance specified in the apiBaseUrl
function insertFormData2JPDB(formID) {

    var $form = $("#" + formID + "");
    var formDataInJson = getFormDataInJson($form);          //jpdb-commons.js method
    var formJsonStr = JSON.stringify(formDataInJson);

    $.ajaxSetup({async: false});
    // The following like will create a put request - jpdb-commons.js method
    var msgDivID = $("#" + formID + "").attr('data-response-div-id');
    var connToken = $("#" + formID + "").attr('data-connection-token');
    if (connToken === "" || connToken === undefined) {
        if (msgDivID === "" || msgDivID === undefined) {
            alert("JPDB Connection Token Missing!");
        } else {
            $("#" + msgDivID + '').html('JPDB Connection Token Missing!').fadeIn().delay(3000).fadeOut();
        }
        return false;
    }
    var dbName = $("#" + formID + "").attr('data-db-name');
    if (dbName === undefined) {
        dbName = "";
    }
    var relName = $("#" + formID + "").attr('data-table-name');
    if (relName === undefined) {
        relName = "";
    }

    var successMsg = $("#" + formID + "").attr('data-success-msg');
    var errorMsg = $("#" + formID + "").attr('data-error-msg');

    var putReq = createPUTRequest(connToken, formJsonStr, dbName, relName);

    var imlPartUrl = "/api/iml";                           // API End-Point URL
    // Sends data to the JPDB server - jpdb-commons.js method
    var respJson = executeCommand(putReq, imlPartUrl);

    var status = respJson.status;
    var statusMsg = "";
    if (status === 200) {
        if (successMsg === "" || successMsg === undefined) {
            statusMsg = respJson.message;
        } else {
            statusMsg = successMsg;
        }
    } else {
        if (errorMsg === "" || errorMsg === undefined) {
            statusMsg = respJson.message;
        } else {
            statusMsg = errorMsg;
        }
    }
    if (msgDivID === "" || msgDivID === undefined) {
        alert(statusMsg);
    } else {
        $("#" + msgDivID + '').html(statusMsg).fadeIn().delay(3000).fadeOut();
    }

    document.getElementById(formID).reset();
    $.ajaxSetup({async: true});

    return false;
}

// This method is used to create PUT Json request.
function createPUTRequest(connToken, jsonObj, dbName, relName) {
    var putRequest = "{\n"
            + "\"token\" : \""
            + connToken
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"PUT\",\n"
            + "\"rel\" : \""
            + relName + "\","
            + "\"jsonStr\": \n"
            + jsonObj
            + "\n"
            + "}";
    return putRequest;
}

// This method is responsible to execute JPDB command on the given dbBaseUrl
// and return response to the caller.
// 
function executeCommandAtGivenBaseUrl(reqString, dbBaseUrl, apiEndPointUrl) {
    var url = dbBaseUrl + apiEndPointUrl;
    var jsonObj;
    $.post(url, reqString, function (result) {
        jsonObj = JSON.parse(result);
    }).fail(function (result) {
        var dataJsonObj = result.responseText;
        jsonObj = JSON.parse(dataJsonObj);
    });
    return jsonObj;
}

// This method is responsible to execute JPDB command on the default baseUrl
// and return response to the caller.
function executeCommand(reqString, apiEndPointUrl) {
    var url = baseUrl + apiEndPointUrl;
    var jsonObj;
    $.post(url, reqString, function (result) {
        jsonObj = JSON.parse(result);
    }).fail(function (result) {
        var dataJsonObj = result.responseText;
        jsonObj = JSON.parse(dataJsonObj);
    });
    return jsonObj;
}

// A general method to extract any form data and return the cooresponding Json representation.
function getFormDataInJson($form) {
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};
    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });
    return indexed_array;
}

function createIS_COLUMN_EXISTRequest(token, dbname, relationName, colName) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbname
            + "\",\n" + "\"cmd\" : \"IS_COLUMN_EXIST\",\n"
            + "\"rel\" : \""
            + relationName
            + "\",\n"
            + "\"colName\" : \""
            + colName
            + "\",\n"
            + "\n"
            + "}";
    return req;
}

function createGETALLSyncRecordRequest(token, dbName, relName, timeStamp, pageNo, pageSize) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"GETALL\",\n"
            + "\"rel\" : \""
            + relName
            + "\",\n" + "\"timeStamp\": "
            + timeStamp
            + ",\n" + "\"pageNo\":"
            + pageNo
            + "," + "\"pageSize\":"
            + pageSize
            + "\n"
            + "}";
    return req;
}

function createGETALLRecordRequest(token, dbName, relName, pageNo, pageSize) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"GETALL\",\n"
            + "\"rel\" : \""
            + relName
            + "\",\n" + "\"pageNo\":"
            + pageNo
            + "," + "\"pageSize\":"
            + pageSize
            + "\n"
            + "}";
    return req;
}

function createGETALLCOLRequest(token, dbName, relName) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"GETALLCOL\",\n"
            + "\"rel\" : \""
            + relName
            + "\"\n"
            + "}";
    return req;
}

function createGETRequest(token, dbname, relationName, jsonObjStr) {
    var value1 = "{\n"
            + "\"token\" : \""
            + token
            + "\",\n" + "\"cmd\" : \"GET\",\n"
            + "\"dbName\": \""
            + dbname
            + "\",\n"
            + "\"rel\" : \""
            + relationName
            + "\",\n"
            + "\"jsonStr\":\n"
            + jsonObjStr
            + "\n"
            + "}";
    return value1;
}

function createGET_BY_RECORDRequest(token, dbName, relName, reqId) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"GET_BY_RECORD\",\n"
            + "\"rel\" : \""
            + relName
            + "\",\n" + "\"record\":"
            + reqId
            + "\n"
            + "}";
    return req;
}

// DEPRECATED - Can be removed in future releases.
function createGET_RELATION_SIZERequest(token, dbname, relationName) {
    var value1 = "{\n"
            + "\"token\" : \""
            + token
            + "\",\n" + "\"cmd\" : \"GET_RELATION_SIZE\",\n"
            + "\"dbName\": \""
            + dbname
            + "\",\n"
            + "\"rel\" : \""
            + relationName
            + "\",\n"
            + "\n"
            + "}";
    return value1;
}

function createGET_RELATION_STATSRequest(token, dbname, relationName) {
    var value1 = "{\n"
            + "\"token\" : \""
            + token
            + "\",\n" + "\"cmd\" : \"GET_RELATION_STATS\",\n"
            + "\"dbName\": \""
            + dbname
            + "\",\n"
            + "\"rel\" : \""
            + relationName
            + "\",\n"
            + "\n"
            + "}";
    return value1;
}

// DEPRECATED - Can be removed in future releases.
function createGETALLRELRequest(token, dbName) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"GETALLREL\"\n"
            + "}";
    return req;
}

function createGET_ALL_RELATIONRequest(token, dbName) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"GET_ALL_RELATION\"\n"
            + "}";
    return req;
}

function createSYNC_DBRequest(token, dbName, relTimestampObjStr) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"SYNC_DB\",\n"
            + "\"relTsJson\":"
            + "{\n"
            + relTimestampObjStr
            + "\n"
            + "}"
            + "}";
    return req;
}

function createREMOVERecordRequest(token, dbName, relName, reqId) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"REMOVE\",\n"
            + "\"rel\" : \""
            + relName
            + "\",\n" + "\"record\":"
            + reqId
            + "\n"
            + "}";
    return req;
}

function createUPDATERecordRequest(token, jsonObj, dbName, relName, reqId) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"UPDATE\",\n"
            + "\"rel\" : \""
            + relName
            + "\",\n"
            + "\"jsonStr\":{ \""
            + reqId
            + "\":\n"
            + jsonObj
            + "\n"
            + "}}";
    return req;
}


function createFIND_RECORDRequest(token, dbName, relName, jsonObjStr) {
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"FIND_RECORD\",\n"
            + "\"rel\" : \""
            + relName
            + "\",\n"
            + "\"jsonStr\":\n"
            + jsonObjStr
            + "\n"
            + "}";
    return req;
}

function createNEXT_RECORDRequest(token, dbName, relName, recordNumber) {
    return createNavReq(token, dbName, relName, "NEXT_RECORD", recordNumber);
}

function createPREV_RECORDRequest(token, dbName, relName, recordNumber) {
    return createNavReq(token, dbName, relName, "PREV_RECORD", recordNumber);
}

function createFIRST_RECORDRequest(token, dbName, relName) {
    return createNavReq(token, dbName, relName, "FIRST_RECORD");
}

function createLAST_RECORDRequest(token, dbName, relName) {
    return createNavReq(token, dbName, relName, "LAST_RECORD");
}

function createNavReq(token, dbName, relName, nav, recNo) {
    var partReq = "";
    if (nav === "NEXT_RECORD" || nav === "PREV_RECORD") {
        partReq = ",\n"
                + "\"record\":"
                + recNo;
    }
    var req = "{\n"
            + "\"token\" : \""
            + token
            + "\","
            + "\"dbName\": \""
            + dbName
            + "\",\n" + "\"cmd\" : \"" + nav + "\",\n"
            + "\"rel\" : \""
            + relName
            + '"'
            + partReq
            + "\n}";
    return req;
}

function createEmailToSendReq(token, jsonStr) {
    var sendEmailRequest = "{\n"
            + "\"token\" : \""
            + token
            + "\",\n"
            + "\"jsonStr\" : \n"
            + jsonStr
            + "\n"
            + "}";
    return sendEmailRequest;
}

//This variable need to be declare as const in future
var JPDB_SUCCESS_CODE = 200;
var JPDB_INVALID_TOKEN_CODE = 401;
var JPDB_ERROR_CODE = 400;


//Function to get session token from JPDB
function createJpdbSessionToken(token, seedValue, dbName, relName, userEmail) {

    //Creating getSessionToken request 
    var getSessionReq = "{\n"
            + "\"token\":\"" + token + "\",\n"
            + "\"jsonStr\":{\"seedValue\":" + seedValue + "}\n}";

    //Executing getSessionToken request
    var respSessionReq = executeCommand(getSessionReq, "/serverless/get_new_session");

    //Checking if session token is created or not by the response of getSessionToken request
    var getSessionTokenStatus = respSessionReq.status;

    if (getSessionTokenStatus === JPDB_SUCCESS_CODE) {
        //Getting session token and setting it on the local storage
        var data = respSessionReq.data;
        var dataObj = JSON.parse(data);
        var jpdbSessionToken = dataObj.sessionToken;

        //Inserting the session token in the relation provided by user
        var dataToPut = {
            jpdbUserSessionToken: jpdbSessionToken,
            email: userEmail
        };
        var dataPutObj = JSON.stringify(dataToPut);
        var sessionRelName = relName + "_session";
        //creating put request to insert session token of the respective user
        var putReqStr = createPUTRequest(token, dataPutObj, dbName, sessionRelName);
        //Executing put request
        var respPUTReq = executeCommand(putReqStr, "/api/iml");

        var putStatus = respPUTReq.status;
        if (putStatus === 200) {
            localStorage.setItem('jpdbUserSessionToken', jpdbSessionToken);
            return JPDB_SUCCESS_CODE;
        }
        return putStatus;
    }
    return getSessionTokenStatus;
}

//Function to get session token from localStorage
function getJpdbSessionToken() {
    return localStorage.getItem('jpdbUserSessionToken');
}

//Function to validate session token from JPDB
function validateJpdbSessionToken(token, jpdbSessionToken) {
    //Creating validate session token request 
    var validSessionReq = "{\n"
            + "\"token\":\"" + token + "\",\n"
            + "\"jsonStr\": {\"sessionToken\": \"" + jpdbSessionToken + "\"}}";
    //Executing validate session token request
    var resValidSessionReq = executeCommand(validSessionReq, "/serverless/validate_session");

    //Checking if session token is valid or not by the response of validate jpdbSessionToken request
    var validateSessionStatus = resValidSessionReq.status;
    return validateSessionStatus;
}

function isJpdbSessionTokenExists(token, dbName, relName) {

    var jpdbSessionToken = getJpdbSessionToken();
    //creating obj for find record request
    var dataToSend = {
        jpdbUserSessionToken: jpdbSessionToken
    };
    var dataObjStr = JSON.stringify(dataToSend);
    var sessionRelName = relName + "_session";

    //Creating find record request to find if session token is exists or not
    var findRecReq = createFIND_RECORDRequest(token, dbName, sessionRelName, dataObjStr);

    //Executing find record request
    $.ajaxSetup({async: false});
    var respFindRecReq = executeCommand(findRecReq, "/api/irl");

    //Checking if session token exists or not by the response of find record request
    var findRecStatus = respFindRecReq.status;
    if (findRecStatus === JPDB_SUCCESS_CODE) {
        //validating session token from JPDB
        var validSessionTokenStatus = validateJpdbSessionToken(token, jpdbSessionToken);

        //if session token is not validated 
        if (validSessionTokenStatus === JPDB_ERROR_CODE) {
            //Getting the record freom find record response
            var data = respFindRecReq.data;

            $.each(data, function (index, row) {

                //Getting the rec_no
                var recordNo = row['rec_no'];
                //Creating remove record request to remove user session
                var removeReqStr = createREMOVERecordRequest(token, dbName, sessionRelName, recordNo);
                //Executing remove record request
                var respRemoveRecord = executeCommand(removeReqStr, "/api/iml");
                $.ajaxSetup({async: true});

                //Checking if record is removed or not by the response of remove record request
                var removeStatus = respRemoveRecord.status;
                if (removeStatus === JPDB_SUCCESS_CODE) {
                    //As session token is invalid and we remove it from JPDB database, 
                    //hence it does not exists
                    if (getJpdbSessionToken() !== null) {
                        //Session token is not validated
                        localStorage.removeItem('jpdbUserSessionToken');
                    }
                    //That's why we are returning 400 as status
                    return JPDB_ERROR_CODE;
                }
                //Record is not removed due to some errors
                return removeStatus;
            });
            return validSessionTokenStatus;
        }
        //Token is valid and exists
        return validSessionTokenStatus;
    }
    $.ajaxSetup({async: true});
    if (findRecStatus === JPDB_ERROR_CODE) {
        localStorage.removeItem('jpdbUserSessionToken');
        return JPDB_ERROR_CODE;
    }
    return findRecStatus;
}


//Function to remove session token from JPDB
function removeJpdbSessionToken(token, jpdbSessionToken) {

    //Creating remove session token request 
    var removeSessionTokenReq = "{\n"
            + "\"token\":\"" + token + "\",\n"
            + "\"jsonStr\": {\"sessionToken\": \"" + jpdbSessionToken + "\"}}";
    //Executing remove session token request

    var resRemoveSessionToken = executeCommand(removeSessionTokenReq, "/serverless/remove_session");

    //Checking if session token is removed or not by the response of remove jpdbSessionToken request
    var removeSessionStatus = resRemoveSessionToken.status;
    return removeSessionStatus;
}


function removeSessionTokenFromJPDB(token, jpdbSessionToken, dbName, relName) {

    $.ajaxSetup({async: false});
    //removing session token from JPDB
    var respRemoveSession = removeJpdbSessionToken(token, jpdbSessionToken);

    //session removed successfully
    if (respRemoveSession === JPDB_SUCCESS_CODE) {

        localStorage.removeItem('jpdbUserSessionToken');

        //Creating find record request obj
        var dataToSend = {
            jpdbUserSessionToken: jpdbSessionToken
        };
        var dataObjStr = JSON.stringify(dataToSend);
        var sessionRelName = relName + "_session";
        //Creating find record request to find if session token is exists or not
        var findRecReq = createFIND_RECORDRequest(token, dbName, sessionRelName, dataObjStr);
        //Executing find record request
        var respFindRecReq = executeCommand(findRecReq, "/api/irl");

        //Checking if user session token exists or not by the response of find record request
        var findRecStatus = respFindRecReq.status;

        if (findRecStatus === JPDB_SUCCESS_CODE) {
            //Getting the record freom find record response
            var data = respFindRecReq.data;

            $.each(data, function (index, row) {
                //Getting the rec_no
                var recordNo = row['rec_no'];
                //Creating remove record request to remove user session
                var removeReqStr = createREMOVERecordRequest(token, dbName, sessionRelName, recordNo);
                //Executing remove record request
                var respRemoveRecord = executeCommand(removeReqStr, "/api/iml");
                $.ajaxSetup({async: true});

                //Checking if user session token REMOVED or not by the response of REMOVE record request
                var removeStatus = respRemoveRecord.status;
                //session token is removed from relation
                if (removeStatus === JPDB_SUCCESS_CODE) {
                    //session token is removed from loggedIn relation
                    return JPDB_SUCCESS_CODE;
                }
                return removeStatus;
            });
            return JPDB_SUCCESS_CODE;
        }

        $.ajaxSetup({async: true});

        if (findRecStatus === 401) {
            return JPDB_INVALID_TOKEN_CODE;
        } else {
            return findRecStatus;
        }
    }
    return respRemoveSession;
}


// It is used in registerDev() method to check email is valid or not.
function isEmailValid(email) {
    var valid = !(email.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/));
//    alert(valid);
    return valid;
}

