jest.unmock('../normalizer');

import {Map} from 'immutable';

import reducer from '../normalizer';

describe(
	'Normalizer Reducer',
	() => {
		function makePayload(entityClassPK) {
			return {
				creator: entityClassPK,
				sharedTo: []
			};
		}

		function makePerson(id) {
			return {
				entityClassNameId: LoopConstants.classNameIds.people,
				entityClassPK: id,
				name: `test${id}`
			};
		}

		it(
			'should update store with normalized entities',
			() => {
				const action = {
					entities: {
						people: {
							55: makePerson(55),
							56: makePerson(56)
						},
						posts: {
							5: {
								assetEntrySetId: 5,
								payload: makePayload(22)
							},
							24: {
								assetEntrySetId: 24,
								payload: makePayload(23)
							}
						}
					}
				};

				const state = reducer(
					Map().mergeIn(
						['posts', 1],
						{
							loading: true
						}
					),
					action
				);

				const postsState = state.get('posts');

				expect(postsState.getIn([1, 'loading'])).toBe(true);
				expect(postsState.getIn([24, 'loading'])).toBe(false);

				expect(postsState.getIn([24, 'data', 'payload', 'creator'])).toBe(23);
				expect(postsState.getIn([5, 'data', 'payload', 'creator'])).toBe(22);

				const peopleState = state.get('people');

				expect(peopleState.getIn([55, 'data', 'name'])).toBe('test55');
				expect(peopleState.getIn([56, 'data', 'name'])).toBe('test56');
			}
		);

		it(
			'should return the original state if no entities key is found',
			() => {
				const initialState = Map();

				const state = reducer(initialState, {});

				expect(state).toBe(initialState);
			}
		);

		it(
			'should return a Map by default',
			() => {
				const state = reducer(undefined, {});

				expect(state).toBeInstanceOf(Map);
			}
		);
	}
);