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
	add as addPerson,
	create as createPerson,
	destroy as destroyPerson,
	edit as editPerson,
	fetchMetrics as fetchPersonMetrics,
	fetchTranslation as fetchPersonTranslation,
	importModel as importPeople,
	index as indexPeople,
	requestTranslation as requestPersonTranslation,
	search as searchPeople,
	update as updatePerson,
	updateDataManually as updatePeopleDataManually,
	updateFormData as updatePeopleFormData,
	view as viewPerson
};