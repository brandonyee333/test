import {Map} from 'immutable';

import {actionTypes} from '../actions/toolbar';
import {createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.REMOVE_TOOLBAR_DISPLAY]: state => state.remove('display'),

	[actionTypes.SET_ACTIVE_TAB_INDEX]: (state, {index}) => state.set('activeTabIndex', index),

	[actionTypes.SET_PAGE_TITLE]: (state, {pageTitle}) => {
		const {SPA} = Liferay.Loop;

		if (SPA) {
			SPA._metalRouter.setDefaultTitle(pageTitle);
		}

		return state.set('pageTitle', pageTitle);
	},

	[actionTypes.SET_TOOLBAR_DISPLAY]: (state, {display}) => state.set('display', display)
};

export default createReducer(Map(), actionHandlers);