import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';
import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'ADDRESSES';

const controller = 'addresses';

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
	...supplemental.actionTypes,
	...createActionTypes('SEND_COORDINATES', controller, true)
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

function sendCoordinates(data) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'sendCoordinates.json',
			data,
			types: [actionTypes.SEND_COORDINATES_REQUEST, actionTypes.SEND_COORDINATES_SUCCESS, actionTypes.SEND_COORDINATES_FAILURE]
		}
	};
}

export {
	actionTypes,
	add as addAddresses,
	create as createAddresses,
	destroy as destroyAddresses,
	edit as editAddresses,
	fetchMetrics as fetchAddressMetrics,
	fetchTranslation as fetchAddressesTranslation,
	importModel as importAddresses,
	index as indexAddresses,
	requestTranslation as requestAddressesTranslation,
	search as searchAddresses,
	sendCoordinates as sendAddressesCoordinates,
	update as updateAddresses,
	updateDataManually as updateAddressesDataManually,
	updateFormData as updateAddressesFormData,
	view as viewAddresses
};