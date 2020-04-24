jest.unmock('../../actions/alerts');
jest.unmock('../../lib/util');
jest.unmock('../alerts');

import {Map, OrderedMap} from 'immutable';

import reducer from '../alerts';
import {actionTypes} from '../../actions/alerts';

describe(
	'Alerts Reducer',
	() => {
		const message = 'Hello World!';

		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should add an alert to the alerts state',
			() => {
				const id = '1';

				const action = {
					alertType: 'error',
					id,
					message,
					type: actionTypes.ADD_ALERT
				};

				const state = reducer(OrderedMap(), action);

				const alert = state.get(id);

				expect(alert.has('alertType')).toBe(true);
				expect(alert.has('message')).toBe(true);
				expect(state.has(id)).toBe(true);
			}
		);

		it(
			'should change an alert in the alerts state',
			() => {
				const id = '2';

				const action = {
					alertType: 'confirmation',
					id,
					type: actionTypes.UPDATE_ALERT
				};

				const prevState = OrderedMap(
					{
						[id]: Map(
							{
								alertType: 'pending',
								id,
								message
							}
						)
					}
				);

				const newState = reducer(prevState, action);

				expect(newState.getIn([id, 'alertType'])).toBe('confirmation');
				expect(prevState.getIn([id, 'alertType'])).toBe('pending');
			}
		);

		it(
			'should remove an alert from the alerts state',
			() => {
				const id = '2';

				const action = {
					id,
					type: actionTypes.REMOVE_ALERT
				};

				const prevState = OrderedMap(
					{
						[id]: Map(
							{
								alertType: 'error',
								id,
								message
							}
						)
					}
				);

				const newState = reducer(prevState, action);

				expect(newState.has(id)).toBe(false);
				expect(prevState.has(id)).toBe(true);
			}
		);
	}
);