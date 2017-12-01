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
	add as addLegals,
	create as createLegals,
	destroy as destroyLegals,
	edit as editLegals,
	fetchMetrics as fetchReportMetrics,
	fetchTranslation as fetchLegalsTranslation,
	importModel as importLegals,
	index as indexLegals,
	requestTranslation as requestLegalsTranslation,
	search as searchLegals,
	update as updateLegals,
	updateDataManually as updateLegalsDataManually,
	updateFormData as updateLegalsFormData,
	view as viewLegals
};