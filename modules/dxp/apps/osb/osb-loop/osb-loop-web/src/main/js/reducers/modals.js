import {Map} from 'immutable';

import {actionTypes} from '../actions/modals';
import {createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.HIDE_MODAL]: state => {
		return state.merge(
			{
				open: false
			}
		);
	},
	[actionTypes.SHOW_MODAL]: (state, action) => {
		const {fullScreen, hideOnBlur, modalProps, modalType, onClose, redirectURL} = action;

		return state.merge(
			{
				fullScreen,
				hideOnBlur,
				modalProps,
				modalType,
				onClose,
				open: true,
				redirectURL
			}
		);
	}
};

export default createReducer(Map(), actionHandlers);