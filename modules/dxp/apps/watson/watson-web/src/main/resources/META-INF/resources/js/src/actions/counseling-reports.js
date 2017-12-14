import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'COUNSELING_REPORTS';

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
	add as addCounselingReports,
	create as createCounselingReports,
	destroy as destroyCounselingReports,
	edit as editCounselingReports,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchCounselingReportsTranslation,
	importModel as importCounselingReports,
	index as indexCounselingReports,
	requestTranslation as requestCounselingReportsTranslation,
	search as searchCounselingReports,
	update as updateCounselingReports,
	updateDataManually as updateCounselingReportsDataManually,
	updateFormData as updateCounselingReportsFormData,
	view as viewCounselingReports
};