import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'CASEWORK_ACTIVITIES';

const controller = 'reports';

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

export {
	actionTypes,
	add as addCaseworkActivities,
	create as createCaseworkActivities,
	destroy as destroyCaseworkActivities,
	edit as editCaseworkActivities,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchCaseworkActivitiesTranslation,
	importModel as importCaseworkActivities,
	index as indexCaseworkActivities,
	requestTranslation as requestCaseworkActivitiesTranslation,
	search as searchCaseworkActivities,
	update as updateCaseworkActivities,
	updateDataManually as updateCaseworkActivitiesDataManually,
	updateFormData as updateCaseworkActivitiesFormData,
	view as viewCaseworkActivities
};