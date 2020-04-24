import {applyMiddleware, createStore} from 'redux';
import {isPlainObject, noop} from 'lodash';
import {Map} from 'immutable';

import {CALL_API} from '../middleware/api';

const NO_OP = 'NO_OP';

const ignoreMocks = store => next => action => {
	let retVal = action;

	if (!isPlainObject(action)) {
		next(
			{
				type: NO_OP
			}
		);
	}
	else {
		if (action[CALL_API]) {
			action.then = noop;
			action.type = NO_OP;
		}

		retVal = next(action);
	}

	return retVal;
};

function mockStore(initialState = Map(), reducer = s => s) {
	return createStore(reducer, initialState, applyMiddleware(ignoreMocks));
}

export default mockStore;