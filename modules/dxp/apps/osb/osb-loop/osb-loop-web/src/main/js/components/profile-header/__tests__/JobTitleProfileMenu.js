jest.unmock('../../../lib/selectors');

import {fromJS, Map} from 'immutable';

import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import JobTitleProfileMenu from '../JobTitleProfileMenu';

const ID = 1;

const state = Map().setIn(
	['people', ID, 'data'],
	fromJS(LoopAssets.getJobTitle(ID))
);

const store = mockStore(state);

describe(
	'JobTitleProfileMenu',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new JobTitleProfileMenu(
					{
						id: ID,
						store
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);