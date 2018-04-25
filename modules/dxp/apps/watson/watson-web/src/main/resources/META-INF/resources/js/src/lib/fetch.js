import Ajax from 'metal-ajax';
import {MultiMap} from 'metal-structs';

export default function fetch(requestURL, config = {}) {
	const {
		body,
		data,
		headers,
		method,
		timeout
	} = config;

	return Ajax.request(
		requestURL,
		method,
		body,
		multiMapFromObject(headers),
		multiMapFromObject(data),
		timeout,
		false,
		true
	).then(
		({response, status}) => {
			if (status !== 200) {
				throw new Error('Request error');
			}

			return JSON.parse(response);
		}
	).then(
		response => {
			const {data, message, status} = response;

			let retVal = response;

			if (status && (data || message)) {
				if (status === 200) {
					retVal = {
						data,
						message
					};
				}
				else if (status === 403) {
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
		}
	);
}

export function multiMapFromObject(obj = {}) {
	const map = new MultiMap();

	Object.keys(obj).forEach(key => map.add(key, obj[key]));

	return map;
}