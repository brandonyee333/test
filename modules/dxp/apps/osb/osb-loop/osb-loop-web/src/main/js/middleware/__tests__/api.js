jest.unmock('../../middleware/api');

import Promise from 'metal-promise';

import api, {CALL_API, toAction} from '../api';
import sendRequest from '../../lib/request';

describe(
	'API Middleware',
	() => {
		const apiCall = {
			[CALL_API]: {
				controller: 'test',
				controllerMethod: 'test.json',
				types: ['TEST_REQUEST', 'TEST_SUCCESS', 'TEST_FAILURE']
			}
		};

		it(
			'should call next middleware if not an API call',
			() => {
				const next = jest.fn();

				const action = {
					type: 'TEST'
				};

				api()(next)(action);

				expect(next).toBeCalledWith(action);
			}
		);

		it(
			'should return a promise',
			() => {
				const next = jest.fn();

				const result = api()(next)(apiCall);

				expect(sendRequest).toBeCalled();
				expect(result instanceof Promise).toBe(true);

				sendRequest.mockClear();
			}
		);

		it(
			'should call the next middleware with an action',
			() => {
				const next = jest.fn();

				api()(next)(apiCall);

				expect(next.mock.calls[0][0].type).toBeTruthy();
			}
		);

		it(
			'should call the next middleware with the successful action',
			() => {
				sendRequest.mockReturnValue(Promise.resolve({test: 'foo'}));

				const next = jest.fn();

				api()(next)(apiCall);

				jest.runAllTimers();

				const {data} = next.mock.calls[1][0];

				expect(data.test).toBe('foo');
			}
		);

		it(
			'should call the next middleware with the successful action',
			() => {
				sendRequest.mockReturnValue(Promise.reject({reason: 'bar'}));

				const next = jest.fn();

				api()(next)(apiCall).catch(jest.fn());

				jest.runAllTimers();

				const {error} = next.mock.calls[1][0];

				expect(error.reason).toBe('bar');
			}
		);
	}
);

describe(
	'toAction',
	() => {
		it(
			'should return an action object',
			() => {
				const actionType = 'TEST';

				const action = toAction(
					actionType,
					{
						id: 5
					}
				);

				expect(action.type).toBe(actionType);
			}
		);

		it(
			'should not contain api call data',
			() => {
				const action = toAction(
					'TEST',
					{
						[CALL_API]: 1,
						id: 15
					}
				);

				expect(action[CALL_API]).toBe(undefined);
			}
		);
	}
);