import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'HISTORY';

const controller = 'histories';

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

const {updateDataManually} = supplemental.actions;

export {
	actionTypes,
	add as addHistories,
	create as createHistories,
	destroy as destroyHistories,
	edit as editHistories,
	index as indexHistories,
	updateDataManually as updateHistoriesDataManually,
	update as updateHistories,
	view as viewHistories
};