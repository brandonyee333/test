jest.unmock('../include');
jest.unmock('../../lib/util');

import {createStore} from 'redux';
import {fromJS, Map} from 'immutable';

import middleware, {INCLUDE} from '../include';
import {classNameIdToSchema} from '../../lib/util';

const {classNameIds} = LoopConstants;

describe(
	'include middleware',
	() => {
		it(
			'should include post data from the store',
			() => {
				const id = 1;

				const action = {
					id,
					[INCLUDE]: {
						classNameId: classNameIds.posts,
						id
					}
				};

				const postData = fromJS(
					{
						id
					}
				);

				const store = createStore(
					s => s,
					Map().mergeIn(
						[classNameIdToSchema(classNameIds.posts), id, 'data'],
						postData
					)
				);

				const next = jest.fn();

				middleware(store)(next)(action);

				const newAction = next.mock.calls[0][0];

				expect(newAction.include).toBe(postData);
			}
		);

		it(
			'should ignore action if INCLUDE key is not found',
			() => {
				const action = {
					type: 'TEST'
				};

				const next = jest.fn();

				middleware()(next)(action);

				const newAction = next.mock.calls[0][0];

				expect(newAction.include).toBeUndefined();
			}
		);
	}
);