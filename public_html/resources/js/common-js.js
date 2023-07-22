function loadNavBar(){
    $("#hrNavBar").load("resources/header.html");
}

function loadFooter(){
    $("#hrFooter").load("resources/footer.html");
}

function listHighlight(hdPageId){
    if(hdPageId==="employee"){
        $("#hd-hlt-employee").addClass("active");
    }
    if(hdPageId==="hdHome"){
        $("#hd-hlt-home").addClass("active");
    }
}


