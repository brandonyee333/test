import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const modelizeActionTypes = ['UPDATE_DATA_MANUALLY', 'UPDATE_FORM_DATA'];
const requestActions = ['fetch_metrics', 'fetch_translation', 'import', 'request_translation'];

export default ({controller, name}) => {
	const modelizedActionTypes = modelizeActionTypes.reduce(
		(result, next) => (
			{
				...result,
				...modelizeActionType(next, name)
			}
		),
		{}
	);

	const requestActionTypes = requestActions.reduce(
		(result, next) => (
			{
				...result,
				...createActionTypes(next, name, true)
			}
		),
		{}
	);

	const actionTypes = Object.assign(modelizedActionTypes, requestActionTypes);

	const actions = {
		fetchMetrics: data => {
			return {
				[CALL_API]: {
					controller,
					controllerMethod: 'fetchMetrics.json',
					data,
					types: [actionTypes.FETCH_METRICS_REQUEST, actionTypes.FETCH_METRICS_SUCCESS, actionTypes.FETCH_METRICS_FAILURE]
				}
			};
		},
		fetchTranslation: data => {
			return {
				[CALL_API]: {
					controller,
					controllerMethod: 'fetchTranslation.json',
					data,
					types: [actionTypes.FETCH_TRANSLATION_REQUEST, actionTypes.FETCH_TRANSLATION_SUCCESS, actionTypes.FETCH_TRANSLATION_FAILURE]
				}
			};
		},
		importModel: data => {
			return {
				[CALL_API]: {
					controller,
					controllerMethod: 'executeImport.json',
					data,
					types: [actionTypes.IMPORT_REQUEST, actionTypes.IMPORT_SUCCESS, actionTypes.IMPORT_FAILURE]
				}
			};
		},
		requestTranslation: data => {
			return {
				[CALL_API]: {
					controller,
					controllerMethod: 'requestTranslation.json',
					data,
					types: [actionTypes.REQUEST_TRANSLATION_REQUEST, actionTypes.REQUEST_TRANSLATION_SUCCESS, actionTypes.REQUEST_TRANSLATION_FAILURE]
				}
			};
		},
		updateDataManually: data => {
			return {
				data,
				type: actionTypes.UPDATE_DATA_MANUALLY
			};
		},
		updateFormData: data => {
			return {
				data,
				type: actionTypes.UPDATE_FORM_DATA
			};
		}
	};

	return {
		actions,
		actionTypes
	};
};

function modelizeActionType(action, name) {
	const actionParts = action.split('_');

	let resultType = `${actionParts[0]}_${name}`;

	for (let i = 1; i < actionParts.length; i++) {
		resultType += `_${actionParts[i]}`;
	}

	return {
		[action]: resultType
	};
}