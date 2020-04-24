jest.unmock('../../actions/dirty-state');
jest.unmock('../../lib/util');
jest.unmock('../dirty-state');

import {Set} from 'immutable';

import reducer from '../dirty-state';
import {actionTypes} from '../../actions/dirty-state';

describe(
	'Dirty-State Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should add a dirty state on add dirty state',
			() => {
				const id = '1';

				const action = {
					id,
					type: actionTypes.ADD_DIRTY_STATE
				};

				const state = reducer(Set(), action);

				expect(state.has(id)).toBe(true);
			}
		);

		it(
			'should remove a dirty state on remove dirty state',
			() => {
				const id = '2';

				const action = {
					id,
					type: actionTypes.REMOVE_DIRTY_STATE
				};

				const state = reducer(Set(id), action);

				expect(state.has(id)).toBe(false);
			}
		);
	}
);