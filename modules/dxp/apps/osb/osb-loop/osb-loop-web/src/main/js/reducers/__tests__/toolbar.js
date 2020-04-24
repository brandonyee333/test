jest.unmock('../../actions/toolbar');
jest.unmock('../../lib/util');
jest.unmock('../toolbar');

import {Map} from 'immutable';

import reducer from '../toolbar';
import {actionTypes} from '../../actions/toolbar';

describe(
	'toolbar Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should set toolbar display',
			() => {
				const display = 'foo';

				const action = {
					display,
					type: actionTypes.SET_TOOLBAR_DISPLAY
				};

				const state = reducer(Map(), action);

				expect(state.get('display')).toBe(display);
			}
		);

		it(
			'should remove toolbar display',
			() => {
				const action = {
					type: actionTypes.REMOVE_TOOLBAR_DISPLAY
				};

				const state = reducer(Map({display: 'foo'}), action);

				expect(state.get('display')).toBeFalsy();
			}
		);

		it(
			'should set toolbar tab index',
			() => {
				const index = 4;

				const action = {
					index,
					type: actionTypes.SET_ACTIVE_TAB_INDEX
				};

				const state = reducer(Map({activeTabIndex: 1}), action);

				expect(state.get('activeTabIndex')).toBe(index);
			}
		);

		it(
			'should set toolbar pageTitle',
			() => {
				Liferay.Loop.SPA._metalRouter = {};

				Liferay.Loop.SPA._metalRouter.setDefaultTitle = jest.fn();

				const pageTitle = 'foobar';

				const action = {
					pageTitle,
					type: actionTypes.SET_PAGE_TITLE
				};

				const state = reducer(Map({pageTitle: 'baz'}), action);

				expect(state.get('pageTitle')).toBe(pageTitle);
			}
		);
	}
);