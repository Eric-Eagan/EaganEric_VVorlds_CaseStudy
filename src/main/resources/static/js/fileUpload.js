/**
 * Javascript functions for file upload page
 */

function addTypes() {
	let fileTypeSelect = document.getElementById("FT");
	let temp;
	
	for (let fileType of typeList) {
		temp = document.createElement("option");
		temp.setAttribute("value", fileType.typeId);
		temp.innerHTML = fileType.type;
		fileTypeSelect.appendChild(temp);
	}
}