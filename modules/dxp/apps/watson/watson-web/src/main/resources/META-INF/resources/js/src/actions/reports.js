import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'REPORTS';

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
	add as addReports,
	create as createReports,
	destroy as destroyReports,
	edit as editReports,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchReportsTranslation,
	importModel as importReports,
	index as indexReports,
	requestTranslation as requestReportsTranslation,
	search as searchReports,
	update as updateReports,
	updateDataManually as updateReportsDataManually,
	updateFormData as updateReportsFormData,
	view as viewReports
};