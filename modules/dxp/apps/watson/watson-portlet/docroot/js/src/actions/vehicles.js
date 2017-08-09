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
	add as addVehicle,
	create as createVehicle,
	destroy as destroyVehicle,
	edit as editVehicle,
	fetchMetrics as fetchVehicleMetrics,
	fetchTranslation as fetchVehicleTranslation,
	importModel as importVehicles,
	index as indexVehicles,
	requestTranslation as requestVehicleTranslation,
	search as searchVehicles,
	update as updateVehicle,
	updateDataManually as updateVehiclesDataManually,
	updateFormData as updateVehiclesFormData,
	view as viewVehicle
};