import {Map} from 'immutable';

import createBaseReducer from './crud';
import {actionTypes} from '../actions/pages';
import {composeReducers, createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.ADD_SUCCESS]: (state, {data}) => {
		return state.mergeIn(
			[data.entityClassPK, 'data'],
			data
		);
	}
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'entityClassPK'
		}
	),
	createReducer(Map(), actionHandlers)
);