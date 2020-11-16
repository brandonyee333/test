function DLExternalVideoPicker(callback) {
	callback();
}

DLExternalVideoPicker.prototype = {
	constructor: DLExternalVideoPicker,

	openPicker: function() {
		const url = prompt('Video URL')
		if (url) {
			fetch(
				'${getDLExternalVideoFieldsURL}&${namespace}dlExternalVideoURL=' + url
			)
			.then(
				res => res.json()
			)
			.then(
				${onFilePickCallback}
			);
		}
	}
};