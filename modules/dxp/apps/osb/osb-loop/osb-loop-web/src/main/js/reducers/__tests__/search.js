jest.unmock('../../actions/crud');
jest.unmock('../../actions/divisions');
jest.unmock('../../actions/home');
jest.unmock('../../actions/people');
jest.unmock('../../actions/topics');
jest.unmock('../../lib/util');
jest.unmock('../search');

import {Map} from 'immutable';

import LoopAssets from '../../tests/loop-assets';
import reducer from '../search';
import {actionTypes} from '../../actions/home';
import {actionTypes as divisionsActionTypes} from '../../actions/divisions';

describe(
	'Search Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should clear search state',
			() => {
				const action = {
					type: actionTypes.CLEAR_SEARCH
				};

				const state = reducer(
					Map().mergeIn(
						['posts'],
						LoopAssets.getPost()
					),
					action
				);

				expect(state.get('loading')).toBeFalsy();
			}
		);

		it(
			'should set loading on full search request',
			() => {
				const action = {
					type: actionTypes.FULL_SEARCH_REQUEST
				};

				const state = reducer(Map(), action);

				expect(state.get('loading')).toBe(true);
			}
		);

		it(
			'should set loading on full search failure',
			() => {
				const action = {
					type: actionTypes.FULL_SEARCH_FAILURE
				};

				const state = reducer(Map(), action);

				expect(state.get('loading')).toBe(false);
			}
		);

		it(
			'should update data on full search success',
			() => {
				const action = {
					data: {
						divisions: 1,
						pages: 1,
						people: 1,
						posts: 1,
						topics: 1
					},
					type: actionTypes.FULL_SEARCH_SUCCESS
				};

				const state = reducer(Map(), action);

				expect(state.get('loading')).toBe(false);

				Object.keys(action.data).forEach(
					key => expect(state.get(key)).toBe(1)
				);
			}
		);

		it(
			'should update division data on division search success',
			() => {
				const data = [1, 2, 3];

				const action = {
					data,
					type: divisionsActionTypes.SEARCH_SUCCESS
				};

				const state = reducer(Map(), action);

				expect(state.getIn(['divisions', 'results']).toJS()).toEqual(data);
			}
		);

		it(
			'should update division loading on division search success',
			() => {
				const data = [1, 2, 3];

				const action = {
					data,
					type: divisionsActionTypes.SEARCH_SUCCESS
				};

				const state = reducer(
					Map().setIn(['divisions', 'loading'], true),
					action
				);

				expect(state.getIn(['divisions', 'loading'])).toBe(false);
			}
		);

		it(
			'should update division loading on division search failure',
			() => {
				const data = [1, 2, 3];

				const action = {
					data,
					type: divisionsActionTypes.SEARCH_FAILURE
				};

				const state = reducer(
					Map().setIn(['divisions', 'loading'], true),
					action
				);

				expect(state.getIn(['divisions', 'loading'])).toBe(false);
			}
		);
	}
);