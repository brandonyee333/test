import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const controller = 'list_type';

export const actionTypes = {
	...createActionTypes('fetch', 'children_list_types'),
	...createActionTypes('fetch', 'list_type')
};

export function fetchChildrenListTypes(parentWatsonListTypeId, listType) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'fetchChildrenListTypes.json',
			data: {
				listType,
				parentWatsonListTypeId
			},
			types: [actionTypes.FETCH_CHILDREN_LIST_TYPES_REQUEST, actionTypes.FETCH_CHILDREN_LIST_TYPES_SUCCESS, actionTypes.FETCH_CHILDREN_LIST_TYPES_FAILURE]
		},
		listType,
		parentWatsonListTypeId
	};
}

export function fetchListType(watsonListTypeId) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'fetchListType.json',
			data: {
				watsonListTypeId
			},
			types: [actionTypes.FETCH_LIST_TYPE_REQUEST, actionTypes.FETCH_LIST_TYPE_SUCCESS, actionTypes.FETCH_LIST_TYPE_FAILURE]
		},
		watsonListTypeId
	};
}