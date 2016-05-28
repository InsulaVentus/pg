function chePassword() {
    var pass1 = document.getElementsById("pass1").value;
    var pass2 = document.getElementsById("pass2").value;
    var ok = true;
    if (pass1 != pass2) {
//alert("Passwords Do not match");
        document.getElementsById("pass1").style.borderColor = "#E34234";
        document.getElementsById("pass2").style.borderColor = "#E34234";
        ok = false;
    }
    else {
        alert("Passwords Match!!!");
    }
    return ok;
}