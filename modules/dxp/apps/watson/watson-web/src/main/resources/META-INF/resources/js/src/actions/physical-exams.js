import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'PHYSICAL_EXAMS';

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
	add as addPhysicalExams,
	create as createPhysicalExams,
	destroy as destroyPhysicalExams,
	edit as editPhysicalExams,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchPhysicalExamsTranslation,
	importModel as importPhysicalExams,
	index as indexPhysicalExams,
	requestTranslation as requestPhysicalExamsTranslation,
	search as searchPhysicalExams,
	update as updatePhysicalExams,
	updateDataManually as updatePhysicalExamsDataManually,
	updateFormData as updatePhysicalExamsFormData,
	view as viewPhysicalExams
};