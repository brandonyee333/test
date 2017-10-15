import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/display';
import {createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.UPDATE_COLLAPSED_ENTRIES]: (state, action) => {
		const {collapsedEntries, watsonIncidentId} = action.data;

		return state.setIn(['collapsedEntries', watsonIncidentId], collapsedEntries);
	},
	[actionTypes.UPDATE_COLLAPSED_ENTRY]: (state, action) => {
		const {auto = false, collapsedEntryHash, value, watsonIncidentId} = action.data;

		if (auto) {
			state = state.set('lastAutoOpenedEntry', collapsedEntryHash);
		}

		const collapsedEntries = state.getIn(['collapsedEntries', watsonIncidentId]);

		return state.setIn(
			['collapsedEntries', watsonIncidentId],
			Object.assign(
				{},
				collapsedEntries,
				{
					[collapsedEntryHash]: value
				}
			)
		);
	},
	[actionTypes.UPDATE_DISPLAY_BY]: (state, action) => {
		const displayBy = action.data;

		return state.set('displayBy', displayBy);
	},
	[actionTypes.UPDATE_FILTER]: (state, action) => {
		const {filterData = action.data, model = 'incidents', watsonIncidentId = 0} = action.data;

		return state.mergeIn(
			['filter', watsonIncidentId],
			{
				[model]: filterData
			}
		);
	},
	[actionTypes.UPDATE_HIDE_LOADING_OVERLAY]: (state, action) => {
		const displayBy = action.data;

		return state.set('hideLoadingOverlay', displayBy);
	},
	[actionTypes.UPDATE_LAST_FOCUS]: (state, action) => {
		const lastFocusSelector = action.data;

		return state.set('lastFocus', lastFocusSelector);
	},
	[actionTypes.UPDATE_LAST_ITEMS_LOADED]: (state, action) => {
		const {lastClicked, lastLoaded, model = 'incidents', watsonIncidentId = 0} = action.data;

		return state.setIn(
			['lastLoaded', watsonIncidentId],
			{
				[model]: {
					lastClicked,
					lastLoaded
				}
			}
		);
	},
	[actionTypes.UPDATE_PAGE_TITLE]: (state, action) => {
		const pageTitle = action.data;

		return state.set('pageTitle', pageTitle);
	},
	[actionTypes.UPDATE_SORT_BY]: (state, action) => {
		const {model = 'incidents', sortByData = action.data, watsonIncidentId = 0} = action.data;

		return state.mergeIn(
			['sortBy', watsonIncidentId],
			{
				[model]: sortByData
			}
		);
	}
};

export default createReducer(OrderedMap(), actionHandlers);