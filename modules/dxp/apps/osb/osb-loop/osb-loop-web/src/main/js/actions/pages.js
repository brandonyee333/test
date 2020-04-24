import createBase from './crud';
import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const controller = 'pages';

const base = createBase(
	{
		controller,
		name: 'page',
		plural: 'pages'
	}
);

const actionTypes = {
	...base.actionTypes,
	...createActionTypes('fetch', 'pages'),
	CLEAR_PAGES: 'CLEAR_PAGES'
};

const {
	add,
	destroy,
	fetch,
	search,
	update
} = base.actions;

export {
	actionTypes,
	destroy as destroyPage,
	fetch as fetchPage,
	search as searchPages,
	update as updatePage
};

export function addPage(data) {
	const {
		ownerClassNameId,
		ownerId,
		payload,
		title
	} = data;

	return add(
		{
			classNameId: ownerClassNameId,
			classPK: ownerId,
			payload,
			title
		}
	);
}

export function clearPages(ownerId) {
	return {
		ownerId,
		type: actionTypes.CLEAR_PAGES
	};
}

export function fetchPages(data) {
	const {
		end,
		ownerClassNameId,
		ownerId,
		reverseSort = false,
		sortFieldName,
		start
	} = data;

	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'index.json',
			data: {
				classNameId: ownerClassNameId,
				classPK: ownerId,
				end,
				reverseSort,
				sortFieldName,
				start
			},
			method: 'GET',
			types: [actionTypes.FETCH_PAGES_REQUEST, actionTypes.FETCH_PAGES_SUCCESS, actionTypes.FETCH_PAGES_FAILURE]
		},
		ownerId,
		reverseSort,
		sortFieldName
	};
}