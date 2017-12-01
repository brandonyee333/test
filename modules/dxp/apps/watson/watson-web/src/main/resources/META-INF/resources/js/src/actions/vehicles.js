import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'VEHICLES';

const controller = 'vehicles';

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
	add as addVehicles,
	create as createVehicles,
	destroy as destroyVehicles,
	edit as editVehicles,
	fetchMetrics as fetchVehicleMetrics,
	fetchTranslation as fetchVehiclesTranslation,
	importModel as importVehicles,
	index as indexVehicles,
	requestTranslation as requestVehiclesTranslation,
	search as searchVehicles,
	update as updateVehicles,
	updateDataManually as updateVehiclesDataManually,
	updateFormData as updateVehiclesFormData,
	view as viewVehicles
};