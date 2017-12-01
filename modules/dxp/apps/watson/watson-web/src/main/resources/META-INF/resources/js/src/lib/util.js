import {isEqual} from 'lodash';
import {List, Map} from 'immutable';
import moment from 'moment';
import sub from 'string-sub';

export function compareObjectsData(originalObject = {}, newObject = {}) {
	const {id: oldId, modifiedDateTimeStamp: originalObjectTimeStamp} = originalObject;
	const {id: newId, modifiedDateTimeStamp: newObjectTimeStamp} = newObject;

	const reset = originalObjectTimeStamp > newObjectTimeStamp;

	const wrongId = oldId != newId;

	return reset || wrongId;
}

export function composeReducers(...reducers) {
	return (state, action) => reducers.reduce(
		(currentState, reducer) => reducer(currentState, action),
		state
	);
}

export function convertHtmlToText(html = '') {
	const htmlRegex = /<\/?([a-z][a-z0-9]*)\b[^>]*>?/gi;

	html = html.replace(htmlRegex, ' ');

	return html.trim();
}

export function convertMapToObject(map = new Map()) {
	const object = {};

	map.forEach(
		(value, inputId) => {
			if (List.isList(value)) {
				value = value.toArray();
			}

			object[inputId] = value;
		}
	);

	return object;
}

const actionTypes = ['FAILURE', 'REQUEST', 'SUCCESS'];

export function createActionTypes(action, name, genericizeKey) {
	action = action.toUpperCase();
	name = name.toUpperCase();

	const key = genericizeKey ? action : `${action}_${name}`;

	return actionTypes.reduce(
		(actions, type) => {
			actions[`${key}_${type}`] = `${action}_${name}_${type}`;

			return actions;
		},
		{}
	);
}

export function createReducer(initialState, actionHandlers) {
	return (state = initialState, action) => {
		const handler = actionHandlers[action.type];

		return handler ? handler(state, action) : state;
	};
}

export function deepCompareIsEqual(object1, object2) {
	return isEqual(JSON.stringify(object1), JSON.stringify(object2));
}

const BYTE_UNITS = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

export function formatBytesToString(bytes) {
	let i = 0;

	while (bytes >= 1024) {
		bytes /= 1024;

		i++;
	}

	return `${bytes.toFixed(1)} ${BYTE_UNITS[i]}`;
}

export function getMimeType(mimeType = '') {
	let type = '';

	if (mimeType) {
		if (mimeType.includes('image')) {
			type = 'IMG';
		}
		else if (mimeType.includes('video')) {
			type = 'VIDEO';
		}
		else if (mimeType.includes('audio')) {
			type = 'AUDIO';
		}
		else {
			type = 'FILE';
		}
	}

	return type;
}

export function getModifiedMoment(userName = '', timestamp = 0) {
	let modifiedTimeMoment = moment(parseInt(timestamp, 10));

	modifiedTimeMoment = modifiedTimeMoment.locale(WatsonConstants.currentLanguageId);

	return sub(Liferay.Language.get('last-modified-x-by-x'), modifiedTimeMoment.fromNow(false), userName);
}

export function getOptionsLabelFromWatsonConstants(model, key, watsonListTypeId) {
	let listTypeLabel = '';

	if (model && key && watsonListTypeId !== undefined) {
		const modelConfig = WatsonConstants.inputConfig[model];

		if (modelConfig) {
			const inputConfig = modelConfig.inputs[key];

			if (inputConfig) {
				const watsonListType = inputConfig.options[watsonListTypeId];

				if (watsonListType) {
					listTypeLabel = watsonListType.label;
				}
			}
		}
	}

	return listTypeLabel;
}

export function getPluralMessage(singular, plural, count = 0) {
	return count === 1 ? singular : plural;
}

export function getURLForLanguageId(languageId) {
	const origin = `${window.location.protocol}//${window.location.host}`;

	let {pathname: pathName} = window.location;

	let portalMainPath = themeDisplay.getPathMain();

	if (pathName && portalMainPath && portalMainPath !== '/c') {
		const indexOf = portalMainPath.indexOf('/c');

		portalMainPath = portalMainPath.slice(0, indexOf);

		pathName = pathName.replace(portalMainPath, '');
	}

	return `${origin}/${languageId}${pathName}`;
}

export function updateDOMTitle(newTitle) {
	window.document.title = newTitle;
}