import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/list-types';
import {createReducer} from '../lib/util';
import {updateErrorDisplay} from './crud';

function updateLoading(state, loading) {
	return state.set('loading', loading);
}

export function updateByPrimaryKeys(state, action, primaryKey, ownKey) {
	state = updateLoading(state, false);

	return state.setIn(['data', primaryKey, ownKey], action.data);
}

const actionHandlers = {
	[actionTypes.FETCH_CHILDREN_LIST_TYPES_FAILURE]: (state, action) => {
		const {listType, parentWatsonListTypeId} = action;

		action.data = {};

		state = updateErrorDisplay(state, action);

		return updateByPrimaryKeys(state, action, parentWatsonListTypeId, listType);
	},
	[actionTypes.FETCH_CHILDREN_LIST_TYPES_REQUEST]: state => {
		return updateLoading(state, true);
	},
	[actionTypes.FETCH_CHILDREN_LIST_TYPES_SUCCESS]: (state, action) => {
		const {listType, parentWatsonListTypeId} = action;

		return updateByPrimaryKeys(state, action, parentWatsonListTypeId, listType);
	},
	[actionTypes.FETCH_LIST_TYPE_FAILURE]: (state, action) => {
		const {watsonListTypeId} = action;

		state = updateErrorDisplay(state, action);

		action.data = {};

		return updateByPrimaryKeys(state, action, watsonListTypeId, 'self');
	},
	[actionTypes.FETCH_LIST_TYPE_REQUEST]: state => {
		return updateLoading(state, true);
	},
	[actionTypes.FETCH_LIST_TYPE_SUCCESS]: (state, action) => {
		const {watsonListTypeId = 0} = action;

		return updateByPrimaryKeys(state, action, watsonListTypeId, 'self');
	}
};

export default createReducer(OrderedMap(), actionHandlers);