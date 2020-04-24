import {fromJS, Map, OrderedSet} from 'immutable';
import {isNumber} from 'lodash';

import createBaseReducer from './crud';
import {actionTypes} from '../actions/divisions';
import {actionTypes as pagesActionTypes} from '../actions/pages';
import {composeReducers, createReducer, getOrderDirection} from '../lib/util';

export function getLocation(id, type, subtype) {
	let storeLocation = [id, 'network', type];

	if (isNumber(subtype)) {
		storeLocation = [id, 'network', type, subtype];
	}

	return storeLocation;
}

function updateNetworkData(type, force = false) {
	return (state, action) => {
		const {
			data,
			id,
			start,
			subtype
		} = action;

		const replace = force || isNumber(start) && start <= 0;

		const currResults = state.getIn([id, 'network', type, 'data'], OrderedSet());

		let updateData;

		if (data.results) {
			const {results, total} = data;

			updateData = {
				data: replace ? results : currResults.concat(results),
				loading: false,
				total
			};
		}
		else {
			updateData = {
				data: replace ? data : currResults.concat(data),
				loading: false
			};
		}

		return state.mergeIn(
			getLocation(id, type, subtype),
			fromJS(updateData)
		);
	};
}

function updateNetworkLoading(loading, type) {
	return (state, {id, subtype}) => {
		return state.setIn([...getLocation(id, type, subtype), 'loading'], loading);
	};
}

const actionHandlers = {
	[actionTypes.FETCH_CHILD_DIVISIONS_FAILURE]: updateNetworkLoading(false, 'childDivisions'),

	[actionTypes.FETCH_CHILD_DIVISIONS_REQUEST]: updateNetworkLoading(true, 'childDivisions'),

	[actionTypes.FETCH_CHILD_DIVISIONS_SUCCESS]: updateNetworkData('childDivisions'),

	[actionTypes.FETCH_LEADS_FAILURE]: updateNetworkLoading(false, 'leads'),

	[actionTypes.FETCH_LEADS_REQUEST]: updateNetworkLoading(true, 'leads'),

	[actionTypes.FETCH_LEADS_SUCCESS]: updateNetworkData('leads'),

	[actionTypes.FETCH_MEMBERS_FAILURE]: updateNetworkLoading(false, 'members'),

	[actionTypes.FETCH_MEMBERS_REQUEST]: updateNetworkLoading(true, 'members'),

	[actionTypes.FETCH_MEMBERS_SUCCESS]: updateNetworkData('members'),

	[actionTypes.FETCH_PARENT_DIVISION_FAILURE]: updateNetworkLoading(false, 'parentDivision'),

	[actionTypes.FETCH_PARENT_DIVISION_REQUEST]: updateNetworkLoading(true, 'parentDivision'),

	[actionTypes.FETCH_PARENT_DIVISION_SUCCESS]: (state, {data, id}) => {
		return state.mergeIn(
			[id, 'network', 'parentDivision'],
			fromJS(
				{
					data,
					loading: false
				}
			)
		);
	},

	[actionTypes.SET_CHILD_DIVISIONS_FAILURE]: updateNetworkLoading(false, 'childDivisions'),

	[actionTypes.SET_CHILD_DIVISIONS_REQUEST]: updateNetworkLoading(true, 'childDivisions'),

	[actionTypes.SET_CHILD_DIVISIONS_SUCCESS]: updateNetworkData('childDivisions', true),

	[actionTypes.SET_PARENT_DIVISION_FAILURE]: updateNetworkLoading(false, 'parentDivision'),

	[actionTypes.SET_PARENT_DIVISION_REQUEST]: updateNetworkLoading(true, 'parentDivision'),

	[actionTypes.SET_PARENT_DIVISION_SUCCESS]: (state, {data, id}) => {
		return state.mergeIn(
			[id, 'network', 'parentDivision'],
			fromJS(
				{
					data,
					loading: false
				}
			)
		);
	},

	[pagesActionTypes.ADD_SUCCESS]: (state, {data}) => {
		return state.updateIn(
			[data.classPK, 'data', 'pagesCount'],
			0,
			curTotal => curTotal + 1
		);
	},

	[pagesActionTypes.CLEAR_PAGES]: (state, {ownerId}) => state.deleteIn([ownerId, 'pages']),

	[pagesActionTypes.FETCH_PAGES_FAILURE]: (state, {ownerId, reverseSort, sortFieldName}) => {
		return state.setIn(
			[ownerId, 'pages', sortFieldName, getOrderDirection(reverseSort), 'loading'],
			false
		);
	},

	[pagesActionTypes.FETCH_PAGES_REQUEST]: (state, {ownerId, reverseSort, sortFieldName}) => {
		return state.setIn(
			[ownerId, 'pages', sortFieldName, getOrderDirection(reverseSort), 'loading'],
			true
		);
	},

	[pagesActionTypes.FETCH_PAGES_SUCCESS]: (state, action) => {
		const {
			data,
			ownerId,
			reverseSort,
			sortFieldName
		} = action;

		const orderDirection = getOrderDirection(reverseSort);

		const {results, total} = data;

		return state.updateIn(
			[ownerId, 'pages', sortFieldName, orderDirection, 'data'],
			OrderedSet(),
			curResults => curResults.concat(results)
		).setIn(
			[ownerId, 'pages', sortFieldName, orderDirection, 'loading'],
			false
		).setIn(
			[ownerId, 'data', 'pagesCount'],
			total
		);
	}
};

const overrideHandlers = {
	[actionTypes.FETCH_SUCCESS]: (state, {data, id}) => {
		if (!isNumber(id)) {
			state = state.delete(id);
		}

		const {loopDivisionCompositeJSONObject, parentLoopDivisionCompositeJSONObject} = data;

		return state.mergeIn(
			[loopDivisionCompositeJSONObject, 'network', 'parentDivision'],
			fromJS(
				{
					data: parentLoopDivisionCompositeJSONObject,
					loading: false
				}
			)
		);
	}
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			overrideHandlers,
			primaryKey: 'entityClassPK'
		}
	),
	createReducer(Map(), actionHandlers)
);