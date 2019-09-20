/**
 * Mocks the Liferay.Language.get() method, which returns the value for a given language key.
 * @param {string} key - The language key
 * @returns {string} The original key
 */

export default function lang(key) {
	return key;
}