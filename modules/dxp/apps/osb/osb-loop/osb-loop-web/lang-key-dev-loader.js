const fs = require('fs');
const path = require('path');
const properties = require('properties');

const LANG_KEY_PATH = path.resolve(
	'src',
	'main',
	'resources',
	'content',
	'Language.properties'
);

const REGEX = /Liferay\s*\.Language\s*\.get\(\s*'(.*?)'\s*\)/g;

module.exports = function(source) {
	this.addDependency(LANG_KEY_PATH);

	const callback = this.async();

	const fileSystem = this.fs || fs;

	fileSystem.readFile(
		LANG_KEY_PATH,
		(err, buffer) => {
			if (err) {
				callback(err);
			}

			const keys = properties.parse(buffer.toString('utf8')) || {};

			const result = source.replace(
				REGEX,
				(match, key) => JSON.stringify(keys[key] || key)
			);

			callback(null, result);
		}
	);
};