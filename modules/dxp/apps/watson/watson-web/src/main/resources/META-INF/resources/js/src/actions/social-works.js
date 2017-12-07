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
	add as addIllnesses,
	create as createIllnesses,
	destroy as destroyIllnesses,
	edit as editIllnesses,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchIllnessesTranslation,
	importModel as importIllnesses,
	index as indexIllnesses,
	requestTranslation as requestIllnessesTranslation,
	search as searchIllnesses,
	update as updateIllnesses,
	updateDataManually as updateIllnessesDataManually,
	updateFormData as updateIllnessesFormData,
	view as viewIllnesses
};