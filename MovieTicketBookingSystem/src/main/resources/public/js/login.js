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


function validateLogin() {
    event.preventDefault();
    var email = $("#email").val();
    var password = $("#password").val();

    if (email === "") {
        alert("Missing Email!!!");
        return;
    }
    if (password === "") {
        alert("Missing Password!!!");
        return;
    }

    var emailJsonObject = JSON.stringify({
        "email": email
    });

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:4567/login");

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
//            alert(xhr.responseText);
            var responseObj = xhr.responseText;
            var responseJsonObj = JSON.parse(responseObj);
            if (responseJsonObj.status == 200) {
                var data = responseJsonObj.data;
                var recordJsonObj = JSON.parse(data);
                var record = recordJsonObj.record;
                var pass = record.password;
//                alert(responseJsonObj.status + " " + pass + " " + password);
                if (pass === password) {
                    localStorage.setItem("email", email);
                    window.location.assign('../index.html');
                } else {
                    alert("Incorrect Id/Password!!!");
                }
            }
            else{
                alert("You are not registered!!!  Please sign up!!!");
                window.location.assign('../register.html');
            }
        } else {
            console.log(`Error: ${xhr.status}`);
        }
    };

    xhr.send(emailJsonObject);

}



(function ($) {

    "use strict";

    var fullHeight = function () {

        $('.js-fullheight').css('height', $(window).height());
        $(window).resize(function () {
            $('.js-fullheight').css('height', $(window).height());
        });

    };
    fullHeight();

    $(".toggle-password").click(function () {

        $(this).toggleClass("fa-eye fa-eye-slash");
        var input = $($(this).attr("toggle"));
        if (input.attr("type") == "password") {
            input.attr("type", "text");
        } else {
            input.attr("type", "password");
        }
    });

})(jQuery);
