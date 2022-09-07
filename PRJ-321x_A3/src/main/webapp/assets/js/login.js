function checkname() {
	
    var email = document.getElementById("email").value;
    var pwd = document.getElementById("pwd").value;

    document.getElementById("email").setCustomValidity("");
    
    if (email == '') {
	
        document.getElementById("email").setCustomValidity("Please enter your username!");
        
    }

    document.getElementById("pwd").setCustomValidity("");
    
    if (pwd == '') {
	
        document.getElementById("pwd").setCustomValidity("Please enter your password!");
        
    }
    
}