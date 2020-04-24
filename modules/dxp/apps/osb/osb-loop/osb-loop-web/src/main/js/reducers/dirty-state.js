import {Set} from 'immutable';

import {actionTypes} from '../actions/dirty-state';
import {createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.ADD_DIRTY_STATE]: (state, {id}) => {
		return state.add(id);
	},

	[actionTypes.REMOVE_DIRTY_STATE]: (state, {id}) => {
		return state.delete(id);
	}
};

export default createReducer(Set(), actionHandlers);