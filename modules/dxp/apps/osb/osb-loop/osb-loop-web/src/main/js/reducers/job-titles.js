import {isNumber} from 'lodash';
import {Map} from 'immutable';

import createBaseReducer from './crud';
import {actionTypes} from '../actions/job-titles';
import {composeReducers, createReducer} from '../lib/util';

const actionHandlers = {};

const overrideHandlers = {
	[actionTypes.FETCH_SUCCESS]: (state, {data, id}) => {
		if (!isNumber(id)) {
			state = state.delete(id);
		}

		return state.setIn([data.entityClassPK, 'loading'], false);
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