function checkname() {
	
    var email = document.getElementById("email").value;
    var pwd = document.getElementById("pwd").value;
    var uname = document.getElementById("uname").value;
    var address = document.getElementById("adrs").value;
    var phone = document.getElementById("phone").value;
    
    let nothing = '';

    document.getElementById("email").setCustomValidity("");
    document.getElementById("pwd").setCustomValidity("");
    document.getElementById("uname").setCustomValidity("");
    document.getElementById("adrs").setCustomValidity("");
    document.getElementById("phone").setCustomValidity("");
    
    switch (nothing) {
	  case email:
	    document.getElementById("email").setCustomValidity("Please enter your email!");
	    break;
	  case pwd:
	    document.getElementById("pwd").setCustomValidity("Please enter your password!");
	    break;
	  case uname:
	    document.getElementById("uname").setCustomValidity("Please enter your username!");
	    break;
	  case address:
	    document.getElementById("adrs").setCustomValidity("Please enter your address!");
	    break;
	  case phone:
	    document.getElementById("phone").setCustomValidity("Please enter your phone number!");
	    break;
	}
    
}