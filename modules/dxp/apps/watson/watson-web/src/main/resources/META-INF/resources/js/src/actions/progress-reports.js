import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'PROGRESS_REPORTS';

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
	add as addProgressReports,
	create as createProgressReports,
	destroy as destroyProgressReports,
	edit as editProgressReports,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchProgressReportsTranslation,
	importModel as importProgressReports,
	index as indexProgressReports,
	requestTranslation as requestProgressReportsTranslation,
	search as searchProgressReports,
	update as updateProgressReports,
	updateDataManually as updateProgressReportsDataManually,
	updateFormData as updateProgressReportsFormData,
	view as viewProgressReports
};