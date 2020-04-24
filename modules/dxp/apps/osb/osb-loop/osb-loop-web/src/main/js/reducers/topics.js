import {Map, OrderedSet} from 'immutable';
import {isNumber} from 'lodash';

import createBaseReducer from './crud';
import {actionTypes} from '../actions/topics';
import {composeReducers, createReducer} from '../lib/util';

function updateLoading(loading) {
	return (state, {id}) => state.setIn([id, 'experts', 'loading'], loading);
}

const actionHandlers = {
	[actionTypes.FETCH_EXPERTS_FAILURE]: updateLoading(false),

	[actionTypes.FETCH_EXPERTS_REQUEST]: updateLoading(true),

	[actionTypes.FETCH_EXPERTS_SUCCESS]: (state, {data, id}) => {
		return state.updateIn(
			[id, 'experts', 'data'],
			OrderedSet(),
			items => items.concat(data.results)
		).setIn(
			[id, 'experts', 'total'],
			data.total
		).setIn(
			[id, 'experts', 'loading'],
			false
		);
	}
};

const overrideHandlers = {
	[actionTypes.FETCH_SUCCESS]: (state, {data, id}) => {
		if (!isNumber(id)) {
			state = state.delete(id);
		}

		return state;
	}
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			overrideHandlers,
			primaryKey: 'entityClassPK'
		}
	),
	createReducer(Map(), actionHandlers)
);