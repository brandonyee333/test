import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'RESOURCES';

const controller = 'resources';

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
	add as addResources,
	create as createResources,
	destroy as destroyResources,
	edit as editResources,
	fetchMetrics as fetchResourceMetrics,
	fetchTranslation as fetchResourcesTranslation,
	importModel as importResources,
	index as indexResources,
	requestTranslation as requestResourcesTranslation,
	search as searchResources,
	update as updateResources,
	updateDataManually as updateResourcesDataManually,
	updateFormData as updateResourcesFormData,
	view as viewResources
};