function showOwnedFiles() {
	let header = document.querySelector('h2');
	header.innerHTML = 'YOUR FILES';
	
	let fileBlock = document.getElementById('file-block');
	
	let removeables = document.querySelectorAll('#file-block > .file-spacer');
	for (let el of removeables) {
		el.remove();
	}
	
	for (let file of ownedList) {
		fileBlock.appendChild(makeFileDiv(file.id, file.path, file.title, file.fileType));
	}
}

function showSharedFiles() {
	let header = document.querySelector('h2');
	header.innerHTML = 'SHARED FILES';

	let fileBlock = document.getElementById('file-block');
	
	let removeables = document.querySelectorAll('#file-block > .file-spacer');
	for (let el of removeables) {
		el.remove();
	}
	
	for (let file of shareList) {
		fileBlock.appendChild(makeFileDiv(file.id, file.path, file.title, file.fileType));
	}
}

function makeFileDiv(fileId, path, title, typePath) {
	let result = document.getElementById('template').cloneNode(true);
	result.removeAttribute('id');
	
	let parent = result.firstElementChild.firstElementChild;
	let mover = parent.firstElementChild.firstElementChild;
	
	mover.setAttribute('onclick', 'deleteFile('+fileId+')');
	
	mover = mover.nextElementSibling.firstElementChild;
	mover.setAttribute('src', typePath);
	
	parent = parent.nextElementSibling;
	
	mover = parent.firstElementChild;
	mover.innerHTML = title;
	
	mover = mover.nextElementSibling.firstElementChild;
	
	mover.setAttribute('onclick', 'downloadFile('+fileId+')');
	
	mover = mover.nextElementSibling
	mover.setAttribute('onclick', 'shareFile('+fileId+')');
	
	return result;
}

function downloadFile(id) {

}

function shareFile(id) {

}

function deleteFile(id) {

}