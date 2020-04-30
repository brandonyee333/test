import axios from 'axios';

/**
 * Returns a promise of the request data
 * @param {string} endpoint The endpoint to post to
 * @param {object} params The parameters object to post with
 * @param {string} encoding The data encoding for the request
 * @returns {Promise} A Promise of the object that results from the Request
 */
export function postData(endpoint, namespace, params, encoding = 'json') {
	let namespacedParams;

	if (encoding === 'json') {
		namespacedParams = Object.fromEntries(
			Object.entries(params).map(([key, value]) => [
				`${namespace}${key}`,
				value
			])
		);
	} else if (encoding === 'formData') {
		namespacedParams = new FormData();

		Object.entries(params).forEach(([key, value]) =>
			namespacedParams.append(`${namespace}${key}`, value)
		);
	} else {
		throw new TypeError(`Invalid data encoding: ${encoding}`);
	}

	return axios.post(endpoint, namespacedParams);
}
