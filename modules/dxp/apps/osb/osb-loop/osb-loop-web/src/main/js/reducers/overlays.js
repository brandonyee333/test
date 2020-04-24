import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/overlays';
import {actionTypes as modalsActionTypes} from '../actions/modals';
import {createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.HIDE_OVERLAY]: (state, {id}) => {
		return state.delete(id);
	},
	[actionTypes.SHOW_OVERLAY]: (state, action) => {
		const {
			alignment,
			alignWithParent,
			containerClass,
			id,
			offset,
			overlayProps,
			overlayType
		} = action;

		return state.mergeIn(
			[action.id],
			{
				alignment,
				alignWithParent,
				containerClass,
				id,
				offset,
				overlayProps,
				overlayType
			}
		);
	},
	[modalsActionTypes.SHOW_MODAL]: state => state.clear()
};

export default createReducer(OrderedMap(), actionHandlers);