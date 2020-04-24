import AnnouncementTitle from '../AnnouncementTitle';

import mockStore from '../../../tests/mock-store';

describe(
	'AnnouncementTitle',
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
				component = new AnnouncementTitle(
					{
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);