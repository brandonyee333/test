import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/authentication';
import {createReducer} from '../lib/util';

function updateLoading(state, loading) {
	return state.set(['loading'], loading);
}

const actionHandlers = {
	[actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_FAILURE]: (state, action) => {
		const {forbidden} = action;

		return state.merge(
			{
				loading: false,
				response: {
					data: action.responseData,
					failure: !forbidden,
					forbidden,
					message: action.message,
					status: 'failure'
				}
			}
		);
	},

	[actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_REQUEST]: state => {
		return updateLoading(state, true);
	},

	[actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_SUCCESS]: (state, action) => {
		return state.merge(
			{
				loading: false,
				response: {
					data: action.responseData,
					message: action.message,
					status: action.status
				},
				status: action.message
			}
		);
	},
	[actionTypes.SUBMIT_AUTHENTICATION_TOKEN_FAILURE]: (state, action) => {
		state = updateLoading(state, false);

		return state.set('status', action.message);
	},
	[actionTypes.SUBMIT_AUTHENTICATION_TOKEN_REQUEST]: state => {
		return updateLoading(state, true);
	},
	[actionTypes.SUBMIT_AUTHENTICATION_TOKEN_SUCCESS]: (state, action) => {
		state = updateLoading(state, false);

		return state.set('status', action.message);
	}

};

export default createReducer(OrderedMap(), actionHandlers);