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
	subButton.classList.remove("hastooltip");
}

function disableSub() {
	let subButton = document.querySelector("#SBM");
	subButton.style.borderColor = "gray";
	subButton.style.setProperty("--submit-hover-bckgnd", "darkgoldenrod");
	subButton.style.setProperty("--submit-hover-brdr", "gray");
	subButton.setAttribute("disabled", true);
	subButton.classList.add("hastooltip");
}

function unlockAccount() {
	let inputList = document.querySelectorAll("#account_display>div>.lockedInput");
	let button = document.querySelector("#SBM");
	
	for (let el of inputList) {
		el.removeAttribute("readonly");
		el.classList.remove("lockedInput");
	}
	
	let parentDiv = button.parentElement;
	let newButton = button.cloneNode();
	button.style.setProperty("float", "left");
	button.innerHTML = "- !Delete Account! -";
	button.setAttribute("onclick", "confirmDelete()");
	
	newButton.setAttribute("id", "NSBM");
	newButton.innerHTML = "- Submit Changes -";
	newButton.setAttribute("onclick", "");
	newButton.setAttribute("form", "account_display");
	parentDiv.appendChild(newButton);
}

function confirmDelete() {
	if(confirm("Warning!\nAre you sure?\nThis will delete all files on your account.\nThere is no recovery.")) {
		let button = document.querySelector("#SBM");
		let form = document.querySelector("#account_display");
		form.setAttribute("action", "deleteAccount");
		button.setAttribute("form", "account_display");
	}
}