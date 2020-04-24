jest.unmock('../../../lib/selectors');

import {fromJS, Map} from 'immutable';

import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import PersonProfileMenu from '../PersonProfileMenu';

const ID = 1;

const state = Map().setIn(
	['people', ID, 'data'],
	fromJS(LoopAssets.getPerson(ID))
);

const store = mockStore(state);

describe(
	'PersonProfileMenu',
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
				component = new PersonProfileMenu(
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