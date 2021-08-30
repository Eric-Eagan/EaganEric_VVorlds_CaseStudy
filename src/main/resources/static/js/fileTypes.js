/**
 * Scripts for uploading file types
 */

function loadFileTypes() {
	let removeables = document.querySelectorAll('tbody > tr');
	for (let el of removeables) {
		el.remove();
	}
	
	let tableBody = document.querySelector('tbody');
	for (let type of typeList) {
		if (type.name != "") {
			let newRow = document.createElement('tr');
			newRow.style.setProperty('max-height', '180px');
			let imgCell = document.createElement('td');
			let image = document.createElement('img');
			image.setAttribute('src', type.imgPath);
			image.setAttribute('alt', 'type image');
			image.style.setProperty('width', '135px');
			let typeCell = document.createElement('td');
			typeCell.innerHTML = type.name;
			let deleteCell = document.createElement('td');
			let deleteForm = document.createElement('form');
			deleteForm.setAttribute('name', 'deleteForm'+type.id);
			deleteForm.setAttribute('id', 'deleteForm'+type.id);
			deleteForm.setAttribute('method', 'post');
			deleteForm.setAttribute('action', 'changeFileTypes/delete='+type.id);
			let deleteButton = document.createElement('button');
			deleteButton.innerHTML = 'Delete Type';
			deleteButton.setAttribute('onclick', 'deleteType('+type.id+')');
			deleteButton.setAttribute('id', 'deleteButton'+type.id);
			deleteButton.setAttribute('type', 'submit');
	
			newRow.appendChild(imgCell);
			imgCell.appendChild(image);
			newRow.appendChild(typeCell);
			newRow.appendChild(deleteCell);
			deleteCell.appendChild(deleteForm);
			deleteCell.appendChild(deleteButton);
			tableBody.appendChild(newRow);
		}
		
	}
}

function deleteType(id) {
	let button = document.getElementById('deleteButton'+id);
	if (confirm('Delete this File Type?')) {
		button.setAttribute('form', 'deleteForm'+id);
	}
}