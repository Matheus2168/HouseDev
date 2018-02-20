



;



function hideAll() {

    var hcDiv = document.getElementById("hc");
    var houseDiv = document.getElementById("house");
    var flatDiv = document.getElementById("flat");
    var habitantDiv = document.getElementById("habitant");
    hcDiv.style.display = "none";
    houseDiv.style.display = "none";
    flatDiv.style.display = "none";
    habitantDiv.style.display = "none";
}




function showProperDiv() {

    hideAll();
    var type = document.getElementById("select").value;
    if( type == "hc"){
        document.getElementById("hc").style.display = "block";
        document.getElementById("text").innerText = "Select housing community:"
    }
    else if (type == "house"){
        document.getElementById("house").style.display = "block";
        document.getElementById("text").innerText = "Select house:"
    }
    else if (type == "flat"){
        document.getElementById("flat").style.display = "block";
        document.getElementById("text").innerText = "Select flat:"
    }
    else if (type == "habitant"){
        document.getElementById("habitant").style.display = "block";
        document.getElementById("text").innerText = "Select habitant:"
    }
}

function redirect() {
    document
    window.location = '/edit/hc/asd';
}