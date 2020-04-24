jest.unmock('../toolbar');

import {
	removeDisplay,
	setActiveTabIndex,
	setDisplay,
	setPageTitle
} from '../toolbar';

describe(
	'toolbar',
	() => {
		it(
			'should set the tab index',
			() => {
				const index = 4;

				const action = setActiveTabIndex(index);

				expect(typeof action).toBe('object');
				expect(action.index).toBe(index);
			}
		);

		it(
			'should call setDisplay action',
			() => {
				const display = 'foo';

				const action = setDisplay(display);

				expect(typeof action).toBe('object');
				expect(action.display).toBe(display);
			}
		);

		it(
			'should call removeDisplay action',
			() => {
				const action = removeDisplay();

				expect(typeof action).toBe('object');
			}
		);

		it(
			'should return a setPageTitle action',
			() => {
				const newTitle = 'foobar';

				const action = setPageTitle(newTitle);

				expect(typeof action).toBe('object');
				expect(action.pageTitle).toBe(newTitle);
			}
		);
	}
);