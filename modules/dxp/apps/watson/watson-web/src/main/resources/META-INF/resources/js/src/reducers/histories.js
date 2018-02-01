import {OrderedMap} from 'immutable';

import {actionTypes} from '../actions/histories';
import {composeReducers, createReducer} from '../lib/util';
import createSupplementalReducer from './supplemental';
import {generateActionHandlers, updateSingleEntry} from './crud';

function overrideCreateBaseReducer() {
	const actionHandlers = generateActionHandlers(actionTypes, 'watsonHistoryId');

	delete actionHandlers[actionTypes.INDEX_SUCCESS];

	actionHandlers[actionTypes.INDEX_SUCCESS] = (state, action) => {
		return updateSingleEntry('watsonParentId', state, action);
	};

	return createReducer(OrderedMap(), actionHandlers);
}

export default composeReducers(
	overrideCreateBaseReducer(),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'History'
		}
	)
);