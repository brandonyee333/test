import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/relationships';
import {composeReducers, createReducer} from '../lib/util';
import createSupplementalReducer from './supplemental';
import {generateActionHandlers, updateSingleEntry} from './crud';

function overrideCreateBaseReducer() {
	const actionHandlers = generateActionHandlers(actionTypes, 'watsonRelationshipId');

	delete actionHandlers[actionTypes.EDIT_SUCCESS];
	delete actionHandlers[actionTypes.UPDATE_SUCCESS];

	actionHandlers[actionTypes.EDIT_SUCCESS] = (state, action) => {
		return updateSingleEntry('watsonIncidentId', state, action);
	};

	actionHandlers[actionTypes.UPDATE_SUCCESS] = (state, action) => {
		return updateSingleEntry('watsonIncidentId', state, action);
	};

	return createReducer(OrderedMap(), actionHandlers);
}

export default composeReducers(
	overrideCreateBaseReducer(),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Relationship'
		}
	)
);