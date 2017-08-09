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
	add as addHistory,
	create as createHistory,
	destroy as destroyHistory,
	edit as editHistory,
	index as indexHistories,
	updateDataManually as updateHistoriesDataManually,
	update as updateHistory,
	view as viewHistory
};