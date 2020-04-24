jest.unmock('../dirty-state');

import {addDirtyState, removeDirtyState} from '../dirty-state';

describe(
	'dirty-state',
	() => {
		const id = '123';

		it(
			'should call addDirtyState action',
			() => {
				const action = addDirtyState(id);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(id);
			}
		);

		it(
			'should call removeDirtyState action',
			() => {
				const action = removeDirtyState(id);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(id);
			}
		);
	}
);