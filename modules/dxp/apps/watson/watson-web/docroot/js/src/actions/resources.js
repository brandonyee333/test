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
	add as addResource,
	create as createResource,
	destroy as destroyResource,
	edit as editResource,
	fetchMetrics as fetchResourceMetrics,
	fetchTranslation as fetchResourceTranslation,
	importModel as importResources,
	index as indexResources,
	requestTranslation as requestResourceTranslation,
	search as searchResources,
	update as updateResource,
	updateDataManually as updateResourcesDataManually,
	updateFormData as updateResourcesFormData,
	view as viewResource
};