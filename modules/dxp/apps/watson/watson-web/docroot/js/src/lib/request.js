import {isArray, isObject, isPlainObject} from 'lodash';

export function addParams(url, params) {
	const separator = url.includes('?') ? '&' : '?';

	return `${url}${separator}${serializeQueryString(params)}`;
}

export function getFormData(data, fileUpload) {
	const formData = new FormData();

	Object.keys(data).forEach(
		key => {
			const value = data[key];

			if (isObject(value) && (!isArray(value) || isPlainObject(value[0])) && !fileUpload) {
				formData.append(WatsonConstants.namespace + key, JSON.stringify(value));
			}
			else {
				formData.append(WatsonConstants.namespace + key, value);
			}
		}
	);

	return formData;
}

export function serializeQueryString(params, namespaceParams = false) {
	const namespace = namespaceParams ? WatsonConstants.namespace : '';

	return Object.keys(params).map(
		key => `${namespace}${encodeURIComponent(key)}=${encodeURIComponent(params[key])}`
	).join('&');
}

export default request => {
	const {
		controller,
		controllerMethod,
		data: requestParams = {},
		fileUpload = false,
		method = 'POST'
	} = request;

	let requestURL = `${WatsonConstants.urls.baseURL}/${controller}/${controllerMethod}`;

	const requestConfig = {
		credentials: 'same-origin',
		method
	};

	if (method === 'GET') {
		requestURL += `?${serializeQueryString(requestParams)}`;
	}
	else {
		requestConfig.body = getFormData(requestParams, fileUpload);
	}

	return fetchURL(requestURL, requestConfig);
};

export function fetchURL(requestURL, requestConfig) {
	return fetch(requestURL, requestConfig).then(
		response => response.json(),
		error => Promise.reject(error)
	).then(
		json => {
			const {data, message, status} = json;

			let retVal = json;

			if (status && (data || message)) {
				if (status === 200) {
					retVal = {
						data,
						message
					};
				}
				else if (status == 403) {
					retVal = Promise.reject(
						{
							forbidden: true,
							message
						}
					);
				}
				else if (data && data.errors) {
					retVal = Promise.reject(
						{
							errors: data.errors,
							message,
							model: data.model
						}
					);
				}
				else {
					retVal = Promise.reject(
						{
							data: data || message
						}
					);
				}
			}

			return retVal;
		},
		error => Promise.reject(error)
	);
}