import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'RELATIONSHIPS';

const controller = 'relationships';

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
	update,
	view
} = base.actions;

const {
	updateDataManually,
	updateFormData
} = supplemental.actions;

export {
	actionTypes,
	add as addRelationship,
	create as createRelationship,
	destroy as destroyRelationship,
	edit as editRelationships,
	index as indexRelationships,
	update as updateRelationships,
	updateDataManually as updateRelationshipsDataManually,
	updateFormData as updateRelationshipsFormData,
	view as viewRelationships
};