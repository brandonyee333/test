import {CALL_API} from '../middleware/api';
import {createActionTypes, getController} from '../lib/util';

const actionTypes = {
	...createActionTypes('fetch', 'index'),
	CLEAR_LISTS: 'CLEAR_LISTS'
};

export function clearLists() {
	return {
		type: actionTypes.CLEAR_LISTS
	};
}

export function fetchIndex({classNameId, createTime, end, listName, start, ...others}) {
	return {
		[CALL_API]: {
			controller: getController(classNameId),
			controllerMethod: 'index.json',
			data: {
				...others,
				createTime,
				end,
				start
			},
			method: 'GET',
			types: [actionTypes.FETCH_INDEX_REQUEST, actionTypes.FETCH_INDEX_SUCCESS, actionTypes.FETCH_INDEX_FAILURE]
		},
		end,
		entityClassNameId: classNameId,
		listName,
		start
	};
}

export {
	actionTypes,
	fetchIndex as fetchIndexList
};