jest.unmock('../../actions/crud');
jest.unmock('../../actions/pages');
jest.unmock('../../tests/loop-assets');
jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../pages');

import {fromJS, is, Map} from 'immutable';

import LoopAssets from '../../tests/loop-assets';
import reducer from '../pages';
import {actionTypes} from '../../actions/pages';

describe(
	'Pages Reducer',
	() => {
		it(
			'should update pages on add page success',
			() => {
				const data = fromJS(LoopAssets.getPage(1));

				const action = {
					data: data.toJS(),
					type: actionTypes.ADD_SUCCESS
				};

				const state = reducer(Map(), action);

				const pageState = state.get(1);

				expect(is(pageState.get('data'), data)).toBeTruthy();
			}
		);
	}
);