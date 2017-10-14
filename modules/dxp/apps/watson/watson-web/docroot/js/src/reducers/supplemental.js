import {OrderedMap} from 'immutable';

import {createReducer} from '../lib/util';
import {updateErrorDisplay, updateLoading} from './crud';

export function generateActionHandlers(actionTypes, key) {
	return {
		[actionTypes.FETCH_METRICS_FAILURE]: updateErrorDisplay,

		[actionTypes.FETCH_METRICS_REQUEST]: updateLoading(true),

		[actionTypes.FETCH_METRICS_SUCCESS]: (state, action) => {
			const {data} = action;

			state = state.set('loading', false);

			return state.setIn(['metricsData'], data);
		},
		[actionTypes.FETCH_TRANSLATION_FAILURE]: updateErrorDisplay,

		[actionTypes.FETCH_TRANSLATION_REQUEST]: updateLoading(true),

		[actionTypes.FETCH_TRANSLATION_SUCCESS]: (state, action) => {
			const {data} = action;

			const {id} = data;

			state = state.set('loading', false);

			return state.setIn(['translationData', id], data);
		},
		[actionTypes.IMPORT_FAILURE]: updateErrorDisplay,

		[actionTypes.IMPORT_REQUEST]: updateLoading(true),

		[actionTypes.IMPORT_SUCCESS]: state => {
			return state.set('update', true);
		},
		[actionTypes.REQUEST_TRANSLATION_FAILURE]: updateErrorDisplay,

		[actionTypes.REQUEST_TRANSLATION_REQUEST]: updateLoading(true),

		[actionTypes.REQUEST_TRANSLATION_SUCCESS]: updateLoading(false),

		[actionTypes.UPDATE_DATA_MANUALLY]: (state, action) => {
			return state.merge(action.data);
		},
		[actionTypes.UPDATE_FORM_DATA]: (state, action) => {
			const {
				formData,
				[key]: watsonModelId = 0
			} = action.data;

			return state.setIn(['formData', watsonModelId], Object.assign({}, formData));
		}
	};
}

export default ({actionTypes, primaryKey}) => {
	const key = `watson${primaryKey}Id`;

	const actionHandlers = generateActionHandlers(actionTypes, key);

	return createReducer(OrderedMap(), actionHandlers);
};