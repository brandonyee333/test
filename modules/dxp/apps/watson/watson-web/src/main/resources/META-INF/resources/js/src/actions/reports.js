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
	add as addReport,
	create as createReport,
	destroy as destroyReport,
	edit as editReport,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchReportTranslation,
	importModel as importReports,
	index as indexReports,
	requestTranslation as requestReportTranslation,
	search as searchReports,
	update as updateReport,
	updateDataManually as updateReportsDataManually,
	updateFormData as updateReportsFormData,
	view as viewReport
};