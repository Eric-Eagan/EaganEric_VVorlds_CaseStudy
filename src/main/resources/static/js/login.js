function verify() {
	let username = document.querySelector("#UN");
	let pass = document.querySelector("#PW");
	let passCon = document.querySelector("#PWC");
	let subButton = document.querySelector("#SBM");

	if (username.value != "" && pass.value != "" && pass.value == passCon.value) {
		subButton.style.borderColor = "black";
		subButton.style.setProperty("--submit-hover-bckgnd", "gold");
		subButton.style.setProperty("--submit-hover-brdr", "black");
		subButton.removeAttribute("disabled");
	}else {
		subButton.style.borderColor = "gray";
		subButton.style.setProperty("--submit-hover-bckgnd", "darkgoldenrod");
		subButton.style.setProperty("--submit-hover-brdr", "gray");
		subButton.setAttribute("disabled", true);
	}
}
