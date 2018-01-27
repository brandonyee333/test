import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/children';
import {composeReducers, createReducer} from '../lib/util';
import createBaseReducer, {updateErrorDisplay, updateLoading} from './crud';
import createSupplementalReducer from './supplemental';

const actionHandlers = {
	[actionTypes.REFRESH_SUB_MODEL_FAILURE]: updateErrorDisplay,

	[actionTypes.REFRESH_SUB_MODEL_REQUEST]: updateLoading(true),

	[actionTypes.REFRESH_SUB_MODEL_SUCCESS]: (state, action) => {
		const {
			id,
			idArray,
			model
		} = action.data;

		let newState;

		if (model !== 'children') {
			const newData = {
				data: {
					[id]: {
						[model]: idArray
					}
				},
				loading: false
			};

			if (state.has('formData')) {
				const formData = state.getIn(['formData', id]);

				if (formData) {
					formData[model] = idArray;

					state = state.setIn(['formData', id], formData);
				}
			}

			state = state.removeIn(['data', id, model]);

			newState = state.mergeDeep(newData);
		}
		else {
			const newData = {
				loading: false
			};

			newState = state.mergeDeep(newData);
		}

		return newState;
	}
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Child'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Child'
		}
	),
	createReducer(OrderedMap(), actionHandlers)
);