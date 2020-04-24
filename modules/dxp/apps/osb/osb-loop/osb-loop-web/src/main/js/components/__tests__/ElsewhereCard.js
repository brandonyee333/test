jest.unmock('../../lib/selectors');

import {fromJS, Map} from 'immutable';

import ElsewhereCard from '../ElsewhereCard';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

const facebookSn = 'foo';
const gitHubSn = 'bar';
const linkedInSn = 'baz';
const twitterSn = 'foobar';

const ID = 0;

const state = fromJS(
	{
		people: Map().set(
			ID,
			fromJS(
				{
					data: {
						...LoopAssets.getPerson(ID),
						facebookSn,
						gitHubSn,
						linkedInSn,
						twitterSn
					}
				}
			)
		)
	}
);

const store = mockStore(state);

describe(
	'ElsewhereCard',
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
				component = new ElsewhereCard(
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