/**
 * Javascript functions for file upload page
 */

function addTypes() {
	let fileTypeSelect = document.getElementById("FT");
	let temp;
	let count = 0;
	
	for (let fileType of typeList) {
		if (fileType.type != "") {
			count = count+1;
			temp = document.createElement("option");
			temp.setAttribute("value", fileType.id);
			temp.innerHTML = fileType.type;
			fileTypeSelect.appendChild(temp);
		}
	}
	
	if (count === 0) {
		enableSub();
		let noOption = document.querySelector("option");
		noOption.setAttribute("value", "1");
		noOption.removeAttribute("disabled");
	}
}