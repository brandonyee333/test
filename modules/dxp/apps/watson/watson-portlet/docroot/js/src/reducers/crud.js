import {OrderedMap} from 'immutable';

import {createReducer} from '../lib/util';

export function generateActionHandlers(actionTypes, key) {
	return {
		[actionTypes.ADD_FAILURE]: updateErrorDisplay,

		[actionTypes.ADD_REQUEST]: updateLoading(true),

		[actionTypes.ADD_SUCCESS]: (state, action) => {
			return updateSingleEntry(key, state, action);
		},

		[actionTypes.CREATE_FAILURE]: updateErrorDisplay,

		[actionTypes.CREATE_REQUEST]: updateLoading(true),

		[actionTypes.CREATE_SUCCESS]: updateData,

		[actionTypes.DESTROY_FAILURE]: updateErrorDisplay,

		[actionTypes.DESTROY_REQUEST]: updateLoading(true),

		[actionTypes.DESTROY_SUCCESS]: updateOnDestroy,

		[actionTypes.EDIT_FAILURE]: updateErrorDisplay,

		[actionTypes.EDIT_REQUEST]: updateLoading(true),

		[actionTypes.EDIT_SUCCESS]: (state, action) => {
			return updateSingleEntry(key, state, action);
		},

		[actionTypes.INDEX_FAILURE]: updateErrorDisplay,

		[actionTypes.INDEX_REQUEST]: updateLoading(true),

		[actionTypes.INDEX_SUCCESS]: (state, action) => {
			const data = {};

			const {count, items: responseItems} = action.data;

			responseItems.forEach(
				entry => {
					const id = entry[key];

					data[id] = entry;
				}
			);

			const {start = 0} = action;

			if (start !== 0) {
				const stateData = state.get('data');

				if (stateData) {
					stateData.forEach(
						entry => {
							data[entry.get(key)] = entry;
						}
					);
				}
			}

			state = state.set('count', count);

			return updateData(state, {data});
		},

		[actionTypes.SEARCH_FAILURE]: updateErrorDisplay,

		[actionTypes.SEARCH_REQUEST]: updateLoading(true),

		[actionTypes.SEARCH_SUCCESS]: (state, action) => {
			const data = {};

			const {count, items: responseItems} = action.data;

			responseItems.forEach(
				entry => {
					const id = entry[key];

					data[id] = entry;
				}
			);

			const {start = 0} = action;

			if (start !== 0) {
				const stateData = state.get('data');

				if (stateData) {
					stateData.forEach(
						entry => {
							data[entry.get(key)] = entry;
						}
					);
				}
			}

			state = state.set('count', count);

			return updateData(state, {data});
		},

		[actionTypes.UPDATE_FAILURE]: updateErrorDisplay,

		[actionTypes.UPDATE_REQUEST]: (state, action) => {
			const {id = 0} = action;

			state = state.deleteIn(['formData', id]);

			return state.set('loading', true);
		},

		[actionTypes.UPDATE_SUCCESS]: (state, action) => {
			return updateSingleEntry(key, state, action);
		},

		[actionTypes.VIEW_FAILURE]: updateErrorDisplay,

		[actionTypes.VIEW_REQUEST]: updateLoading(true),

		[actionTypes.VIEW_SUCCESS]: (state, action) => {
			return updateSingleEntry(key, state, action);
		}
	};
}

export function updateData(state, action) {
	return state.merge(
		{
			data: action.data,
			errors: {},
			loading: false,
			response: {
				data: action.responseData,
				failure: false,
				message: action.message,
				status: 'success'
			},
			translationData: {},
			update: true
		}
	);
}

export function updateErrorDisplay(state, action) {
	const {forbidden} = action;

	const failure = !action.errors && !forbidden;

	const {id = 0} = action;

	const formData = {};

	formData[id] = action.model;

	state = state.merge(
		{
			errors: action.errors,
			loading: false,
			response: {
				data: action.responseData,
				failure,
				forbidden,
				message: action.message,
				status: 'failure'
			},
			translationData: {},
			update: false
		}
	);

	let modelId = 0;

	const {model} = action;

	if (model) {
		modelId = action.model.id;

		if (id != modelId) {
			action.model.id = '';
		}
	}

	return state.setIn(['formData', id], model);
}

export function updateLoading(loading) {
	return (state, action) => state.set('loading', loading);
}

export function updateOnDestroy(state, action) {
	state = state.merge(
		{
			loading: false,
			update: true
		}
	);

	return state.delete(action.id);
}

export function updateSingleEntry(key, state, action) {
	const stateData = state.get('data');

	const data = {};

	if (stateData) {
		stateData.forEach(
			entry => {
				data[entry.get(key)] = entry;
			}
		);
	}

	const {data: responseData} = action;

	if (responseData) {
		const id = responseData[key];

		data[id] = responseData;

		delete action.data;
	}

	return updateData(state, Object.assign({data}, {responseData}, action));
}

export default ({actionTypes, primaryKey}) => {
	const key = `watson${primaryKey}Id`;

	const actionHandlers = generateActionHandlers(actionTypes, key);

	return createReducer(OrderedMap(), actionHandlers);
};