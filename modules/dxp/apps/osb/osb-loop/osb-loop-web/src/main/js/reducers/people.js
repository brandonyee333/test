import {fromJS, Map, OrderedSet} from 'immutable';

import createBaseReducer from './crud';
import {actionTypes} from '../actions/people';
import {classNameIdToSchema, composeReducers, createReducer} from '../lib/util';

function updateExpertiseLoading(loading) {
	return (state, {id}) => state.setIn([id, 'expertise', 'loading'], loading);
}

function updateFollowingLoading(loading) {
	return (state, {entityClassNameId, id}) => state.setIn([id, 'following', classNameIdToSchema(entityClassNameId), 'loading'], loading);
}

function updateNetworkData(type) {
	return (state, {data, id}) => {
		const {results, total} = data;

		return state.mergeIn(
			[id, 'network', type],
			fromJS(
				{
					data: state.getIn(
						[id, 'network', type, 'data'],
						OrderedSet()
					).concat(results),
					loading: false,
					total
				}
			)
		);
	};
}

function updateNetworkLoading(loading, type) {
	return (state, {id}) => state.setIn([id, 'network', type, 'loading'], loading);
}

function updateDivisionNetworkLoading(loading) {
	return (state, {divisionType, id}) => state.setIn(
		[
			id,
			'network',
			'divisions',
			divisionType,
			'loading'
		],
		loading
	);
}

const actionHandlers = {
	[actionTypes.FETCH_DIVISIONS_FAILURE]: updateDivisionNetworkLoading(false),

	[actionTypes.FETCH_DIVISIONS_REQUEST]: updateDivisionNetworkLoading(true),

	[actionTypes.FETCH_DIVISIONS_SUCCESS]: (state, action) => {
		const {
			data: {results, total},
			divisionType,
			id
		} = action;

		return state.mergeIn(
			[
				id,
				'network',
				'divisions',
				divisionType
			],
			fromJS(
				{
					data: results,
					loading: false,
					total
				}
			)
		);
	},

	[actionTypes.FETCH_EXPERTISE_FAILURE]: updateExpertiseLoading(false),

	[actionTypes.FETCH_EXPERTISE_REQUEST]: updateExpertiseLoading(true),

	[actionTypes.FETCH_EXPERTISE_SUCCESS]: (state, {data, id}) => (
		state.mergeIn(
			[id, 'expertise'],
			fromJS(
				{
					data,
					loading: false
				}
			)
		)
	),

	[actionTypes.FETCH_FOLLOWING_FAILURE]: updateFollowingLoading(false),

	[actionTypes.FETCH_FOLLOWING_REQUEST]: updateFollowingLoading(true),

	[actionTypes.FETCH_FOLLOWING_SUCCESS]: (state, action) => {
		const {
			data: {results, total},
			entityClassNameId,
			id
		} = action;

		return state.updateIn(
			[id, 'following', classNameIdToSchema(entityClassNameId), 'data'],
			OrderedSet(),
			curResults => curResults.concat(results)
		).mergeIn(
			[id, 'following', classNameIdToSchema(entityClassNameId)],
			fromJS(
				{
					loading: false,
					total
				}
			)
		);
	},

	[actionTypes.FETCH_MANAGERS_FAILURE]: updateNetworkLoading(false, 'managers'),

	[actionTypes.FETCH_MANAGERS_REQUEST]: updateNetworkLoading(true, 'managers'),

	[actionTypes.FETCH_MANAGERS_SUCCESS]: updateNetworkData('managers'),

	[actionTypes.FETCH_SUBORDINATES_FAILURE]: updateNetworkLoading(false, 'subordinates'),

	[actionTypes.FETCH_SUBORDINATES_REQUEST]: updateNetworkLoading(true, 'subordinates'),

	[actionTypes.FETCH_SUBORDINATES_SUCCESS]: updateNetworkData('subordinates')
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'entityClassPK'
		}
	),
	createReducer(Map(), actionHandlers)
);