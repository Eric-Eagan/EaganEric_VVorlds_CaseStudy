function showOwnedFiles() {
	let header = document.querySelector('h2');
	header.innerHTML = 'YOUR FILES';
	
	let fileBlock = document.getElementById('file-block');
	
	let removeables = document.querySelectorAll('#file-block > .file-spacer');
	for (let el of removeables) {
		el.remove();
	}
	
	for (let file of ownedList) {
		console.log(file);
		fileBlock.appendChild(makeFileDiv(file.id, file.path, file.title, file.fileType, 1));
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
		fileBlock.appendChild(makeFileDiv(file.id, file.path, file.title, file.fileType, 0));
	}
}

function makeFileDiv(fileId, path, title, typePath, ownerControl) {
	let result = document.getElementById('template').cloneNode(true);
	result.removeAttribute('id');
	
	//parent -> container : mover -> delete_form
	let parent = result.firstElementChild.firstElementChild;
	let mover = parent.firstElementChild.firstElementChild;
	mover.setAttribute('action', 'delete_file/'+fileId);
	mover.setAttribute('id', 'delete_'+fileId);
	
	//mover -> delete_button
	mover = mover.nextElementSibling;
	if(ownerControl === 1) {
		mover.setAttribute('onclick', 'deleteFile('+fileId+')');
		mover.setAttribute('form', '');
	}else {
		mover.style.setProperty('display', 'none');
	}
	
	//mover -> file_type icon
	mover = mover.nextElementSibling.firstElementChild;
	mover.setAttribute('src', typePath);
	
	//mover -> file-title
	mover = parent.nextElementSibling.firstElementChild;
	mover.innerHTML = title;
	
	//mover -> download_form
	mover = mover.nextElementSibling.firstElementChild;
	mover.setAttribute('action', 'download_file/'+fileId);
	mover.setAttribute('id', 'download_'+fileId);
	
	//mover -> share_form
	mover = mover.nextElementSibling
	mover.setAttribute('action', 'share_file/'+fileId);
	mover.setAttribute('id', 'share_'+fileId);
	
	//mover -> share_button
	mover = mover.nextElementSibling
	if(ownerControl === 1) {
		mover.setAttribute('onclick', 'shareFile('+fileId+')');
		mover.setAttribute('form', '');
	}else {
		mover.style.setProperty('display', 'none');
	}
	
	return result;
}

function deleteFile(id) {
	if(confirm('Are you sure?\nThere is no recovery.')) {
		let deleteButton = document.getElementById('delete_'+id).nextElementSibling;
		deleteButton.setAttribute('form', 'delete_'+id);
	}
}

function shareFile(id) {
	let target = prompt('Enter username of target')
	
	if(target != null) {
		let shareForm = document.getElementById('share_'+id);
		shareForm.setAttribute('action', 'share_file/'+id+'/'+target);
		
		let shareButton = shareForm.nextElementSibling;
		shareButton.setAttribute('form', 'share_'+id);
	}
}