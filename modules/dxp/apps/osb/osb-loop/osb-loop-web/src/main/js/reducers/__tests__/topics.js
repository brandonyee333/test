jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../topics');

import {Map, OrderedSet} from 'immutable';

import reducer from '../topics';
import {actionTypes} from '../../actions/topics';

describe(
	'Topics Reducer',
	() => {
		it(
			'should handle fetch experts request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_EXPERTS_REQUEST
				};

				const state = reducer(Map(), action);

				expect(state.getIn([id, 'experts', 'loading'])).toBe(true);
			}
		);

		it(
			'should handle fetch experts failure',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_EXPERTS_FAILURE
				};

				const state = reducer(
					Map().setIn([id, 'experts', 'loading'], true),
					action
				);

				expect(state.getIn([id, 'experts', 'loading'])).toBe(false);
			}
		);

		it(
			'should handle fetch experts success',
			() => {
				const id = 1;
				const items = OrderedSet([2, 3, 4]);

				const action = {
					data: {
						results: items,
						total: 3
					},
					id,
					type: actionTypes.FETCH_EXPERTS_SUCCESS
				};

				const state = reducer(Map(), action);

				const data = state.getIn([id, 'experts', 'data']);
				const loading = state.getIn([id, 'experts', 'loading']);
				const total = state.getIn([id, 'experts', 'total']);

				expect(data).toEqual(items);
				expect(loading).toBeFalsy();
				expect(total).toEqual(3);
			}
		);

		it(
			'should set remove an entity if the id is a string',
			() => {
				const id = 'tests';

				const action = {
					data: {
						loopTopicCompositeJSONObject: 0,
						parentLoopTopicCompositeJSONObject: 1
					},
					id,
					type: actionTypes.FETCH_SUCCESS
				};

				const prevState = Map({[id]: {loading: true}});

				expect(prevState.get(id)).toBeTruthy();

				const state = reducer(prevState, action);

				expect(state.get(id)).toBeFalsy();
			}
		);
	}
);