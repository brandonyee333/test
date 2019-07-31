/**
 * String subsitution for handling language keys with tokens
 * @param {string} langKey - The language key with tokens
 * @param {string[]} args - An array of the token values
 * @returns {string} The string subsituted language key
 */

export default function langSub(langKey, args) {
	return langKey.replace(/{(\d+)}/g, (match, index) => args[Number(index)]);
}
