

function checkHc() {
    var message = "";
    if (document.getElementById("hcName").value.length < 3){
        message+="\nName must contain at least 3 characters";

    }
    if (document.getElementById("hcSelectHouse").value == 0){
        message+="\nYou have to select defined house";

    }



    if(message == ""){
        successHc();
        editSuccess()
        return true;
    }
    else {
        alert(message);
        return false;
    }

}

function checkHouse() {
    var message = "";
    if (document.getElementById("adress").value.length < 3){
        message+="\nAdress must contain at least 3 characters";

    }
    if(message == ""){
        successHouse();
        return true;
    }
    else {
        alert(message);
        return false;
    }

}

function checkFlat() {
    var message = "";
    if (document.getElementById("number").value == 0) {
        message += "\nFlat number cannot be undefined";

    }
    else if (document.getElementById("number").value < 1) {
        message += "\nFlat number cannot be less than 1";

    }
    if (document.getElementById("area").value < 10) {
        message += "\nFlat area cannot be smaller than 10";

    }
    if (document.getElementById("flatSelectHouse").value == 0) {
        message += "\nYou have to select defined house";

    }
    if (message == "") {
        successFlat();
        return true;
    }
    else {
        alert(message);
        return false;
    }
}

    function checkHabitant() {

        var message = "";
        if (document.getElementById("firstName").value.length < 3){
            message+="\nFirst name is too short";

        }
        if (document.getElementById("lastName").value.length < 3){
            message+="\nLast name is too short";

        }
        if (document.getElementById("gender").value == 0){
            message+="\nYou have to define gender of habitant";

        }
        if (document.getElementById("habitantSelectFlat").value == 0){
            message+="\nYou have to select defined flat";

        }


        if(message == ""){
            successHabitant();
            return true;
        }
        else {
            alert(message);
            return false;
        }

}

function reset(div) {
    div.style.backgroundColor = "none";
}

function successHc() {
    var div = document.getElementById("hcDiv");
    div.style.backgroundColor = "green";
    setTimeout("reset(div)",200);
}
function successHouse() {
    var div = document.getElementById("houseDiv");
    div.style.backgroundColor = "green";
    setTimeout("reset(div)",200);
}
function successFlat() {
    var div = document.getElementById("flatDiv");
    div.style.backgroundColor = "green";
    setTimeout("reset(div)",200);
}
function successHabitant() {
    var div = document.getElementById("habDiv");
    div.style.backgroundColor = "green";
    setTimeout("reset(div)",200);
}

function editSuccess() {
    var div = document.getElementById("blink");
    div.style.backgroundColor = "green";
    setTimeout("reset(div)",200)

}



function areYouSure() {
    if (confirm(" (!)  Are you sure ?  (!) ")) {
        txt = "You pressed OK!";
        return true;
    } else {
        txt = "You pressed Cancel!";
        return false;
    }

}

