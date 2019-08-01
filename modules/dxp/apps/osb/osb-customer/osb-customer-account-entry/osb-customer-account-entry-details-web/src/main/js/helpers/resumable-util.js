export const handleFileError = (file, message) => {
	return JSON.parse(message);
};

export const getFileProgress = file => {
	return Math.floor(file.progress() * 100);
};

export const handleFileSuccess = (file, message) => {
	const response = JSON.parse(message);

	const retVal = {
		file: null,
		fileAttachment: null,
		message: response.message,
		submitForm: false
	};

	if (response.message == 'complete' || response.message == 'file-exists') {
		retVal.fileAttachment = file;
		retVal.file = JSON.stringify(response.file);
		retVal.submitForm = true;
	}

	return retVal;
};
