jest.unmock('../../lib/selectors');

import {fetchFollowing} from '../../actions/people';

import mockStore from '../../tests/mock-store';
import sendRequest from '../../lib/request';
import Sidebar from '../Sidebar';

describe(
	'Sidebar',
	() => {
		let component;

		const store = mockStore();

		fetchFollowing.mockReturnValue(
			{
				then: sendRequest,
				type: 'TEST'
			}
		);

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				fetchFollowing.mockClear();
			}
		);

		it(
			'should render',
			() => {
				component = new Sidebar(
					{
						id: 0,
						store
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should fetchFollowing on attach',
			() => {
				component = new Sidebar(
					{
						id: 0,
						store
					}
				);

				expect(fetchFollowing.mock.calls.length).toBe(2);
			}
		);
	}
);