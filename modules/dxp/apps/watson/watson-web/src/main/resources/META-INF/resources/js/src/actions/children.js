import {CALL_API} from '../middleware/api';
import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'CHILDREN';

const controller = 'children';

const base = createBaseActions(
	{
		controller,
		name: NAME
	}
);

const supplemental = createSupplementalActions(
	{
		controller,
		name: NAME
	}
);

const actionTypes = {
	...base.actionTypes,
	...supplemental.actionTypes
};

const {
	add,
	create,
	destroy,
	edit,
	index,
	search,
	update,
	view
} = base.actions;

const {
	fetchMetrics,
	fetchTranslation,
	importModel,
	requestTranslation,
	updateDataManually,
	updateFormData
} = supplemental.actions;

function refreshSubModel(data) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'refreshSubModel.json',
			data,
			types: [actionTypes.REFRESH_SUB_MODEL_REQUEST, actionTypes.REFRESH_SUB_MODEL_SUCCESS, actionTypes.REFRESH_SUB_MODEL_FAILURE]
		}
	};
}

export {
	actionTypes,
	add as addChild,
	create as createChild,
	destroy as destroyChild,
	edit as editChild,
	fetchMetrics as fetchChildMetrics,
	fetchTranslation as fetchChildTranslation,
	importModel as importChildren,
	index as indexChildren,
	refreshSubModel,
	requestTranslation as requestChildTranslation,
	search as searchChildren,
	update as updateChild,
	updateDataManually as updateChildrenDataManually,
	updateFormData as updateChildrenFormData,
	view as viewChild
};