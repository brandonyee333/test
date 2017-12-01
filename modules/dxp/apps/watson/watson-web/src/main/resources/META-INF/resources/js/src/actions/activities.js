import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';
import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'ACTIVITY';

const controller = 'activities';

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
	...supplemental.actionTypes,
	...createActionTypes('FETCH_DRAFT', controller, true)
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

function fetchDraft(data) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'fetchDraft.json',
			data,
			types: [actionTypes.FETCH_DRAFT_REQUEST, actionTypes.FETCH_DRAFT_SUCCESS, actionTypes.FETCH_DRAFT_FAILURE]
		}
	};
}

export {
	actionTypes,
	add as addActivities,
	create as createActivities,
	destroy as destroyActivities,
	edit as editActivities,
	fetchDraft as fetchActivitiesDraft,
	fetchMetrics as fetchActivityMetrics,
	fetchTranslation as fetchActivitiesTranslation,
	importModel as importActivities,
	index as indexActivities,
	requestTranslation as requestActivitiesTranslation,
	search as searchActivities,
	update as updateActivities,
	updateDataManually as updateActivitiesDataManually,
	updateFormData as updateActivitiesFormData,
	view as viewActivities
};