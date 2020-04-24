jest.unmock('../entities');
jest.unmock('../../lib/util');

import * as actions from '../entities';
import {fetchDivision} from '../divisions';

describe(
	'fetchEntity',
	() => {
		const {fetchEntity} = actions;

		it(
			'should return an call to `fetchDivision`',
			() => {
				const id = 123;

				fetchEntity(LoopConstants.classNameIds.divisions, id);

				expect(fetchDivision).toHaveBeenCalled();
			}
		);
	}
);