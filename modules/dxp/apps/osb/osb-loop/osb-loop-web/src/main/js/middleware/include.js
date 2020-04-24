import {Map} from 'immutable';

import {classNameIdToSchema} from '../lib/util';

export const INCLUDE = 'INCLUDE';

export default store => next => action => {
	const include = action[INCLUDE];

	if (include) {
		const {classNameId, id} = include;

		action.include = store.getState().getIn([classNameIdToSchema(classNameId), id, 'data'], Map());

		delete action[INCLUDE];
	}

	return next(action);
};