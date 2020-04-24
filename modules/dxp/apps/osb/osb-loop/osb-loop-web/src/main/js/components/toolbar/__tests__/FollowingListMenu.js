jest.unmock('../../../lib/selectors');

import FollowingListMenu from '../FollowingListMenu';
import mockStore from '../../../tests/mock-store';

describe(
	'FollowingListMenu',
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
				component = new FollowingListMenu(
					{
						entityClassNameId: LoopConstants.classNameIds.people,
						icon: '',
						id: 123,
						label: '',
						store: mockStore(),
						url: '#'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);