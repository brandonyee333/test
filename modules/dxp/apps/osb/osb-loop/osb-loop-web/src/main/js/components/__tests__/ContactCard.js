jest.unmock('../../lib/selectors');

import {fromJS} from 'immutable';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import ContactCard from '../ContactCard';

const ID = 0;

const state = fromJS(
	{
		people: [
			{
				data: LoopAssets.getPerson(ID)
			}
		]
	}
);

const store = mockStore(state);

describe(
	'ContactCard',
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
				component = new ContactCard(
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