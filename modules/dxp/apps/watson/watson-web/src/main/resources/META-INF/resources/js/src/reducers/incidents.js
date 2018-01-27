import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/incidents';
import {composeReducers, createReducer} from '../lib/util';
import createBaseReducer, {updateData, updateErrorDisplay, updateLoading} from './crud';
import createSupplementalReducer from './supplemental';

const actionHandlers = {
	[actionTypes.FETCH_AFFILIATIONS_FAILURE]: updateErrorDisplay,

	[actionTypes.FETCH_AFFILIATIONS_REQUEST]: updateLoading(true),

	[actionTypes.FETCH_AFFILIATIONS_SUCCESS]: (state, action) => {
		const data = {};

		const {count, items: responseItems} = action.data;

		responseItems.forEach(
			entry => {
				const {watsonIncidentId} = entry;

				data[watsonIncidentId] = entry;
			}
		);

		state = state.set('count', count);

		return updateData(state, {data});
	},
	[actionTypes.REFRESH_SUB_MODEL_FAILURE]: updateErrorDisplay,

	[actionTypes.REFRESH_SUB_MODEL_REQUEST]: updateLoading(true),

	[actionTypes.REFRESH_SUB_MODEL_SUCCESS]: (state, action) => {
		const {
			id,
			idArray,
			model
		} = action.data;

		let newState;

		if (model !== 'incidents') {
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
	},

	[actionTypes.UPDATE_INCIDENT_ADDRESS_FORM_DATA]: (state, action) => {
		const {data} = action;

		const incidentFormData = state.getIn(['formData', 0]) || {};

		incidentFormData.address = data;

		return state.setIn(['formData', 0], Object.assign({}, incidentFormData));
	}
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Incident'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Incident'
		}
	),
	createReducer(OrderedMap(), actionHandlers)
);