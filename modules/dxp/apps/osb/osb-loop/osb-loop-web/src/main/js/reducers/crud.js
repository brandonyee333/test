import {fromJS, Map} from 'immutable';
import {isNumber} from 'lodash';

import {createReducer} from '../lib/util';

export function mergeData(state, {data, id}) {
	let entityId = id;

	let retVal = state;

	if (!isNumber(entityId)) {
		entityId = data.entityClassPK;

		retVal = retVal.delete(id);
	}

	return retVal.mergeIn(
		[entityId],
		fromJS(
			{
				data,
				loading: false
			}
		)
	);
}

export function updateLoading(loading) {
	return (state, action) => state.setIn([action.id, 'loading'], loading);
}

export default ({actionTypes, overrideHandlers, primaryKey}) => {
	const baseActionHandlers = {
		[actionTypes.CREATE_SUCCESS]: (state, action) => {
			return mergeData(
				state,
				{
					...action,
					id: action.data[primaryKey]
				}
			);
		},

		[actionTypes.DESTROY_FAILURE]: updateLoading(false),

		[actionTypes.DESTROY_REQUEST]: updateLoading(true),

		[actionTypes.DESTROY_SUCCESS]: (state, action) => {
			return state.delete(action.id);
		},

		[actionTypes.FETCH_FAILURE]: updateLoading(false),

		[actionTypes.FETCH_REQUEST]: updateLoading(true),

		[actionTypes.FETCH_SUCCESS]: mergeData,

		[actionTypes.HYDRATE]: (state, action) => {
			let retVal = state;

			if (!state.getIn([action.id, 'data'])) {
				retVal = mergeData(state, action);
			}

			return retVal;
		},

		[actionTypes.UPDATE_FAILURE]: updateLoading(false),

		[actionTypes.UPDATE_REQUEST]: updateLoading(true),

		[actionTypes.UPDATE_SUCCESS]: mergeData,

		...overrideHandlers
	};

	return createReducer(Map(), baseActionHandlers);
};