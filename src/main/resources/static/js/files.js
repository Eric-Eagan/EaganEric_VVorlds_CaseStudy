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
	
	//parent -> container : mover -> delete_form
	let parent = result.firstElementChild.firstElementChild;
	let mover = parent.firstElementChild.firstElementChild;
	mover.setAttribute('action', 'delete_file/'+fileId);
	mover.setAttribute('id', 'delete_'+fileId);
	
	//mover -> delete_button
	mover = mover.nextElementSibling;
	mover.setAttribute('onclick', 'deleteFile('+fileId+')');
	mover.setAttribute('form', '');
	
	//mover -> file_type icon
	mover = mover.nextElementSibling.firstElementChild;
	mover.setAttribute('src', typePath);
	
	//mover -> file-title
	mover = parent.nextElementSibling.firstElementChild;
	mover.innerHTML = title;
	
	//mover -> download_form
	mover = mover.nextElementSibling.firstElementChild;
	mover.setAttribute('action', 'download/'+fileId);
	mover.setAttribute('id', 'download_'+fileId);
	
	//mover -> share_form
	mover = mover.nextElementSibling
	mover.setAttribute('action', 'share/'+fileId);
	mover.setAttribute('id', 'share_'+fileId);
	
	return result;
}

function deleteFile(id) {
	if(confirm('Are you sure?\nThere is no recovery.')) {
		let deleteButton = document.getElementById('delete_'+id).nextElementSibling;
		deleteButton.setAttribute('form', 'delete_'+id);
	}
	
	
}