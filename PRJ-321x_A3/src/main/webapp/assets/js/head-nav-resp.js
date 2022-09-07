function responsive() {

    var x = document.getElementById("repnav");
    if (x.className === "navbar") {
        x.className += " responsive";
    } else {
        x.className = "navbar";
    }

}