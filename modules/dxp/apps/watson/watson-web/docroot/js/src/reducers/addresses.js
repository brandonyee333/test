import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/addresses';
import {composeReducers, createReducer} from '../lib/util';
import createBaseReducer, {updateErrorDisplay, updateLoading} from './crud';
import createSupplementalReducer from './supplemental';

const actionHandlers = {
	[actionTypes.SEND_COORDINATES_FAILURE]: updateErrorDisplay,

	[actionTypes.SEND_COORDINATES_REQUEST]: updateLoading(true),

	[actionTypes.SEND_COORDINATES_SUCCESS]: updateLoading(false)
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Address'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Address'
		}
	),
	createReducer(OrderedMap(), actionHandlers)
);