function logMeIn() {
	console.log("Trying to log in");
	if (validate()) {
		alert("Successful, but unimplemented login")
	}
}

function validate() {
	if (document.user_input.userName.value == "") {
        alert("Please provide your name!");
        document.user_input.userName.focus();
        return false;
    } else { var NAME = document.getElementById("UN").value; }

	if (document.user_input.password.value == "") {
        alert("Please provide your password!");
        document.user_input.password.focus();
        return false;
    } else { var PASS = document.getElementById("PW").value; }

	return true;
}
