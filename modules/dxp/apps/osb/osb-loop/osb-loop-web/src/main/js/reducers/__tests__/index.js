jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../index');

import reducer from '../index';

describe(
	'Index Reducer',
	() => {
		it(
			'should export a function',
			() => {
				expect(reducer instanceof Function).toBe(true);
			}
		);
	}
);