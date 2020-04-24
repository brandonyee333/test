import {List, Map} from 'immutable';

import LoopConstants from '../lib/loop-constants';
import {actionTypes as homeActionTypes} from '../actions/home';
import {actionTypes as peopleActionTypes} from '../actions/people';
import {actionTypes as topicsActionTypes} from '../actions/topics';
import {actionTypes as divisionsActionTypes} from '../actions/divisions';
import {classNameIdToSchema, createReducer} from '../lib/util';

const {classNameIds} = LoopConstants;

function updateFullLoading(loading) {
	return state => state.set('loading', loading);
}

function updateSearchResults(classNameId) {
	const schema = classNameIdToSchema(classNameId);

	return (state, {data}) => state.updateIn(
		[schema, 'results'],
		List(),
		list => list.concat(data)
	).setIn(
		[schema, 'loading'],
		false
	);
}

function updateSchemaLoading(classNameId, bool) {
	return state => state.setIn([classNameIdToSchema(classNameId), 'loading'], bool);
}

const actionHandlers = {
	[divisionsActionTypes.SEARCH_FAILURE]: updateSchemaLoading(classNameIds.divisions, false),

	[divisionsActionTypes.SEARCH_REQUEST]: updateSchemaLoading(classNameIds.divisions, true),

	[divisionsActionTypes.SEARCH_SUCCESS]: updateSearchResults(classNameIds.divisions),

	[homeActionTypes.CLEAR_SEARCH]: state => state.clear(),

	[homeActionTypes.FULL_SEARCH_FAILURE]: updateFullLoading(false),

	[homeActionTypes.FULL_SEARCH_REQUEST]: updateFullLoading(true),

	[homeActionTypes.FULL_SEARCH_SUCCESS]: (state, action) => {
		return state.merge(
			{
				...action.data,
				loading: false
			}
		);
	},

	[peopleActionTypes.SEARCH_REQUEST]: updateSchemaLoading(classNameIds.people, true),

	[peopleActionTypes.SEARCH_SUCCESS]: updateSearchResults(classNameIds.people),

	[topicsActionTypes.SEARCH_REQUEST]: updateSchemaLoading(classNameIds.topics, true),

	[topicsActionTypes.SEARCH_SUCCESS]: updateSearchResults(classNameIds.topics)
};

export default createReducer(Map(), actionHandlers);