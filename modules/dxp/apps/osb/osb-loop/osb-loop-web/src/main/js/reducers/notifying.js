import {Map} from 'immutable';

import {actionTypes} from '../actions/notifying';
import {classNameIdToSchema, createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.NOTIFY_EMAIL_ENTITY_SUCCESS]: (state, {entityClassNameId, id, notifyingEmail}) => {
		return state.setIn(
			[classNameIdToSchema(entityClassNameId), id, 'data', 'notifyingEmail'],
			notifyingEmail
		);
	},

	[actionTypes.NOTIFY_ENTITY_SUCCESS]: (state, {entityClassNameId, id, notifying}) => {
		return state.setIn(
			[classNameIdToSchema(entityClassNameId), id, 'data', 'notifying'],
			notifying
		);
	}
};

export default createReducer(Map(), actionHandlers);