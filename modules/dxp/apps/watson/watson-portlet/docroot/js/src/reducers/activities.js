import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/activities';
import {composeReducers, createReducer} from '../lib/util';
import createBaseReducer, {updateErrorDisplay, updateLoading} from './crud';
import createSupplementalReducer from './supplemental';

const actionHandlers = {
	[actionTypes.FETCH_DRAFT_FAILURE]: updateErrorDisplay,

	[actionTypes.FETCH_DRAFT_REQUEST]: updateLoading(true),

	[actionTypes.FETCH_DRAFT_SUCCESS]: (state, action) => {
		const {data} = action;

		state = state.set('loading', false);

		return state.setIn(['formData', 0], data);
	}
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Activity'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Activity'
		}
	),
	createReducer(OrderedMap(), actionHandlers)
);