import {get, includes} from 'lodash';

import {actionTypes as feedActionTypes} from '../actions/feeds';
import {actionTypes as homeActionTypes} from '../actions/home';
import {actionTypes as pageActionTypes} from '../actions/pages';
import {actionTypes as postActionTypes} from '../actions/posts';
import {PAGES, POSTS} from '../lib/router-constants';

const actionTypes = [
	feedActionTypes.FETCH_COMMENTS_SUCCESS,
	feedActionTypes.FETCH_POSTS_SUCCESS,
	homeActionTypes.FULL_SEARCH_SUCCESS,
	pageActionTypes.ADD_SUCCESS,
	pageActionTypes.FETCH_PAGES_SUCCESS,
	pageActionTypes.FETCH_SUCCESS,
	pageActionTypes.SEARCH_SUCCESS,
	pageActionTypes.UPDATE_SUCCESS,
	postActionTypes.CREATE_SUCCESS,
	postActionTypes.FETCH_SUCCESS,
	postActionTypes.HYDRATE,
	postActionTypes.LIKE_POST_SUCCESS,
	postActionTypes.SEARCH_SUCCESS,
	postActionTypes.UPDATE_SUCCESS
];

const actionTypeLocator = {
	[homeActionTypes.FULL_SEARCH_SUCCESS]: [[PAGES, 'results'], [POSTS, 'results']],
	[postActionTypes.SEARCH_SUCCESS]: ['results']
};

function parsePayloads(data) {
	const {childAssetEntrySets} = data;

	if (childAssetEntrySets && childAssetEntrySets.length) {
		childAssetEntrySets.forEach(parsePayloads);
	}

	data.payload = JSON.parse(data.payload);
}

function parsePayloadTypes(data, type) {
	if (includes(actionTypes, type)) {
		if (data.payload) {
			parsePayloads(data);
		}
		else if (Array.isArray(data)) {
			data.forEach(parsePayloads);
		}
		else if (Array.isArray(data.results)) {
			data.results.forEach(parsePayloads);
		}
	}
}

export default store => next => action => {
	const {type} = action;

	let {data} = action;

	const locators = actionTypeLocator[type];

	if (locators) {
		data = locators.map(
			(locator, index) => get(data, locator, data)
		);

		data.forEach(
			item => {
				parsePayloadTypes(item, type);
			}
		);
	}
	else {
		parsePayloadTypes(data, type);
	}

	return next(action);
};