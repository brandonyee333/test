import {fromJS, Map, OrderedSet} from 'immutable';

import {actionTypes} from '../actions/followers';
import {classNameIdToSchema, createReducer} from '../lib/util';

function updateFollowersLoading(loading) {
	return (state, {entityClassNameId, id}) => {
		return state.setIn([classNameIdToSchema(entityClassNameId), id, 'followers', 'loading'], loading);
	};
}

const actionHandlers = {
	[actionTypes.FETCH_FOLLOWERS_FAILURE]: updateFollowersLoading(false),

	[actionTypes.FETCH_FOLLOWERS_REQUEST]: updateFollowersLoading(true),

	[actionTypes.FETCH_FOLLOWERS_SUCCESS]: (state, {entityClassNameId, data, id}) => {
		const schema = classNameIdToSchema(entityClassNameId);

		return state.mergeIn(
			[schema, id, 'followers'],
			fromJS(
				{
					data: state.getIn(
						[schema, id, 'followers', 'data'],
						OrderedSet()
					).concat(data),
					loading: false
				}
			)
		);
	}
};

export default createReducer(Map(), actionHandlers);