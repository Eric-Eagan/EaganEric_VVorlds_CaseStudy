const Tectonics = [
	['Tectonics Basics', 'https://www.youtube.com/embed/x_Tn66PvTn4'],
	['Tectonics Details', 'https://www.youtube.com/embed/yGMKmbGTEHQ']
];

const Circulation = [
	['Atmospheric Circulation','https://www.youtube.com/embed/LifRswfCxFU'],
	['Ocean Currents','https://www.youtube.com/embed/n_E9UShtyY8']
];

const Climate = [
	['Climates Equatorial','https://www.youtube.com/embed/5lCbxMZJ4zA'],
	['Climates Intermediate/Polar','https://www.youtube.com/embed/fag48Nh8PXE']
];

function setupSelect() {
	let select = document.getElementById('RS');
	let categories = ['Tectonics', 'Circulation', 'Climate'];

	for (let option of categories) {
		let newOpt = document.createElement('option');
		newOpt.setAttribute('value', option);
		newOpt.textContent = option;
		select.appendChild(newOpt);
	}
}

function updateResources() {
	let topic = document.getElementById('RS').value;

	let removeables = document.querySelectorAll('tbody > tr');
	for (let el of removeables) {
		el.remove();
	}

	let addables;
	switch(topic) {
		case 'Tectonics':
			addables = Tectonics;
			break;
		case 'Circulation':
			addables = Circulation;
			break;
		case 'Climate':
			addables = Climate;
			break;
	}

	let tableBody = document.querySelector('tbody');

	for (let entry of addables) {
		let newRow = document.createElement('tr');
		let descCell = document.createElement('td');
		descCell.textContent = entry[0];
		let videoCell = document.createElement('td');
		let newVideo = document.createElement('iframe');
		newVideo.setAttribute('width', '100%');
		newVideo.setAttribute('height', 'auto');
		newVideo.setAttribute('allowfullscreen', '');
		newVideo.setAttribute('src', entry[1]);

		newRow.appendChild(descCell);
		videoCell.appendChild(newVideo);
		newRow.appendChild(videoCell);
		tableBody.appendChild(newRow);
	}

	document.querySelector("select").blur();
}


/*
<tr>
	<td>Tectonics Basics</td>
	<td class="video_cell">
		<iframe width="100%" height="auto" allowfullscreen
		src="https://www.youtube.com/embed/x_Tn66PvTn4" >
		</iframe>
	</td>
</tr>
*/
