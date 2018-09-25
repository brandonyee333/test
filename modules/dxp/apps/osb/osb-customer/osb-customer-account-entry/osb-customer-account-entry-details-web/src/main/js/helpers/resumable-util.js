export const handleFileError = (file, message) => {
	return JSON.parse(message);
};

export const getFileProgress = (file) => {
;	return Math.floor(file.progress() * 100);
}

export const handleFileSuccess = (file, message) => {
	const response = JSON.parse(message);

	let retVal = {};

	if ((response.message == 'complete') || (response.message == 'file-exists')) {
		retVal.fileAttachment = file;
		retVal.file = JSON.stringify(response.file);
		retVal.message = response.message;
		retVal.submitForm = true;
	}
	else {
		retVal.fileAttachment = null;
		retVal.file = null;
		retVal.message = response.message;
		retVal.submitForm = false;
	}

	return retVal;
};