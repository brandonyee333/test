import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'INCIDENTS';

const controller = 'incidents';

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
	...createActionTypes('FETCH_AFFILIATIONS', controller, true),
	...createActionTypes('REFRESH_SUB_MODEL', controller, true),
	UPDATE_INCIDENT_ADDRESS_FORM_DATA: 'UPDATE_INCIDENT_ADDRESS_FORM_DATA'
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
	requestTranslation,
	updateDataManually,
	updateFormData
} = supplemental.actions;

function fetchAffiliations(data) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'fetchAffiliations.json',
			data,
			types: [actionTypes.FETCH_AFFILIATIONS_REQUEST, actionTypes.FETCH_AFFILIATIONS_SUCCESS, actionTypes.FETCH_AFFILIATIONS_FAILURE]
		}
	};
}

function refreshSubModel(data) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'refreshSubModel.json',
			data,
			types: [actionTypes.REFRESH_SUB_MODEL_REQUEST, actionTypes.REFRESH_SUB_MODEL_SUCCESS, actionTypes.REFRESH_SUB_MODEL_FAILURE]
		}
	};
}

function updateIncidentAddressFormData(data) {
	return {
		data,
		type: actionTypes.UPDATE_INCIDENT_ADDRESS_FORM_DATA
	};
}

export {
	actionTypes,
	add as addIncidents,
	create as createIncidents,
	destroy as destroyIncidents,
	edit as editIncidents,
	fetchAffiliations as fetchIncidentAffiliations,
	fetchMetrics as fetchIncidentMetrics,
	fetchTranslation as fetchIncidentsTranslation,
	index as indexIncidents,
	refreshSubModel,
	requestTranslation as requestIncidentsTranslation,
	search as searchIncidents,
	update as updateIncidents,
	updateDataManually as updateIncidentsDataManually,
	updateFormData as updateIncidentsFormData,
	updateIncidentAddressFormData,
	view as viewIncidents
};