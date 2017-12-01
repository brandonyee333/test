import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'PEOPLE';

const controller = 'people';

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
	add as addPeople,
	create as createPeople,
	destroy as destroyPeople,
	edit as editPeople,
	fetchMetrics as fetchPersonMetrics,
	fetchTranslation as fetchPeopleTranslation,
	importModel as importPeople,
	index as indexPeople,
	requestTranslation as requestPeopleTranslation,
	search as searchPeople,
	update as updatePeople,
	updateDataManually as updatePeopleDataManually,
	updateFormData as updatePeopleFormData,
	view as viewPeople
};