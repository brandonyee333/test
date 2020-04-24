jest.unmock('../../../lib/selectors');

import TopicProfileMenu from '../TopicProfileMenu';
import mockStore from '../../../tests/mock-store';

describe(
	'TopicProfileMenu',
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
				component = new TopicProfileMenu({store: mockStore()});

				expect(component).toMatchSnapshot();
			}
		);
	}
);