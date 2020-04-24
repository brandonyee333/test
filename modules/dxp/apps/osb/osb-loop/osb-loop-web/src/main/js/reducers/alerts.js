import {Map} from 'immutable';

import {actionTypes} from '../actions/alerts';
import {createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.ADD_ALERT]: (state, {alertType, id, message}) => {
		return state.mergeIn(
			[id],
			{
				alertType,
				id,
				message
			}
		);
	},

	[actionTypes.REMOVE_ALERT]: (state, {id}) => {
		return state.delete(id);
	},

	[actionTypes.UPDATE_ALERT]: (state, {alertType, id, message}) => {
		return state.mergeIn(
			[id],
			{
				alertType,
				message
			}
		);
	}
};

export default createReducer(Map(), actionHandlers);