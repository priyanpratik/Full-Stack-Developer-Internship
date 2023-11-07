var i = 0;
var images = [];
var slideTime = 4000;
images[2] = 'https://img2.wallspic.com/crops/8/6/8/1/5/151868/151868-iron_man-thor-marvel_cinematic_universe-thanos-avengers-3840x2160.jpg';
images[1] = 'https://m.media-amazon.com/images/I/91rZG2MlOHL.jpg';
images[3] = 'https://wallpapers.com/images/hd/amazing-thor-lightning-art-jokcr256qjvi7911.jpg';
images[0] = 'https://cdna.artstation.com/p/assets/images/images/010/344/660/large/bhavin-solanki-alogo-land-r2.jpg?1523933440';
function changePicture() {
    document.body.style.backgroundImage = "url(" + images[i] + ")";

    if (i < images.length - 1) {
        i++;
    } else {
        i = 0;
    }
    setTimeout(changePicture, slideTime);
}
window.onload = changePicture;



var jpdbBaseURL = "http://api.login2explore.com:5577";
var connToken = "90933146|-31949318775558192|90951378";
var empDBName = "Movie Ticket Booking Database";
var empRelationName = "User Login";

function forgetPassword(){
    event.preventDefault();
    var email = $("#email").val();
    alert("start");
    
    if (email === "") {
        alert("Missing Email!!!");
        return;
    }
    
    var jsonStrEmailObj = {
        email: email
    };
    
    var jsonEmailObj = JSON.stringify(jsonStrEmailObj);
    var getByKeyRequest = createGET_BY_KEYRequest(connToken, empDBName, empRelationName, jsonEmailObj);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(getByKeyRequest, "/api/irl");
    jQuery.ajaxSetup({async: true});
    if (jsonObj.status === 400) {
        alert("You are not registered");
        resetLogin();
        return "";
    }

    recordNo = JSON.parse(jsonObj.data).rec_no;
    alert(1+""+email);
    return email;
}

function generatePass() {
    var pass = '';
    var str = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' +
            'abcdefghijklmnopqrstuvwxyz0123456789@#$';

    for (var i = 1; i <= 8; i++) {
        var char = Math.floor(Math.random()
                * str.length + 1);

        pass += str.charAt(char);
    }

    return pass;
}

function sendMail() {
    event.preventDefault();
    var email = forgetPassword();
    alert(2+""+email);
    if (email === "")
        return "";
    var randomPassword = generatePass();
    var jsonStrObj = {
        password: randomPassword
    };
    var jsonChg = JSON.stringify(jsonStrObj);
//    alert(jsonChg);

    var updateRequest = createUPDATERecordRequest(connToken, jsonChg, empDBName, empRelationName, recordNo);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(updateRequest, "/api/iml");
    jQuery.ajaxSetup({async: true});

    var emailContent = "You new requested password is " + randomPassword + " Please Change it whenever you login!!!";
    var emailSubject = "Password Change Request";
    var jsonStr = {
        emailTo: email,
        emailSubject: emailSubject,
        emailContent: emailContent
    };

    var jsonEmailObj = JSON.stringify(jsonStr);
    var emailObjReq = createEmailToSendReq(connToken, jsonEmailObj);
    jQuery.ajaxSetup({async: false});
    var jsonObj = executeCommand(emailObjReq, "/serverless/send_email");
    alert(JSON.stringify(jsonObj));
    jQuery.ajaxSetup({async: true});
    window.location.assign('login.html');
}

var recordNo;

