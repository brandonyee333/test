import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/authentication';
import {createReducer} from '../lib/util';

function updateLoading(loading) {
	return (state, action) => state.set('loading', loading);
}

const actionHandlers = {
	[actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_FAILURE]: (state, action) => {
		return state.merge(
			{
				loading: false,
				status: action.message
			}
		);
	},

	[actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_REQUEST]: updateLoading(true),

	[actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_SUCCESS]: (state, action) => {
		return state.merge(
			{
				loading: false,
				status: action.message
			}
		);
	},
	[actionTypes.SUBMIT_AUTHENTICATION_TOKEN_FAILURE]: (state, action) => {
		return state.merge(
			{
				loading: false,
				status: action.message
			}
		);
	},
	[actionTypes.SUBMIT_AUTHENTICATION_TOKEN_REQUEST]: updateLoading(true),

	[actionTypes.SUBMIT_AUTHENTICATION_TOKEN_SUCCESS]: (state, action) => {
		return state.merge(
			{
				loading: false,
				status: action.message
			}
		);
	}

};

export default createReducer(OrderedMap(), actionHandlers);