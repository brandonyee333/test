import {isArray, isObject, isPlainObject} from 'lodash';

import fetch from './fetch';

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

	return fetch(requestURL, requestConfig);
};