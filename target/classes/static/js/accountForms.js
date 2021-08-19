function verify() {
	let username = document.querySelector("#UN");
	let pass = document.querySelector("#PW");
	let passCon = document.querySelector("#PWC");

	if (username != null && username.value == "") {
		disableSub();
		return;
	}

	if (pass.value != "" && pass.value == passCon.value) {
		enableSub();
	}else {
		disableSub();
	}
}

function enableSub() {
	let subButton = document.querySelector("#SBM");
	subButton.style.borderColor = "black";
	subButton.style.setProperty("--submit-hover-bckgnd", "gold");
	subButton.style.setProperty("--submit-hover-brdr", "black");
	subButton.removeAttribute("disabled");
}

function disableSub() {
	let subButton = document.querySelector("#SBM");
	subButton.style.borderColor = "gray";
	subButton.style.setProperty("--submit-hover-bckgnd", "darkgoldenrod");
	subButton.style.setProperty("--submit-hover-brdr", "gray");
	subButton.setAttribute("disabled", true);
}