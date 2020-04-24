import AnnouncementCarousel from '../AnnouncementCarousel';
import mockStore from '../../tests/mock-store';
import {fetchPosts} from '../../actions/feeds';

const store = mockStore();

describe(
	'AnnouncementCarousel',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		beforeEach(
			() => {
				fetchPosts.mockReturnValue({type: 'test'});
			}
		);

		it(
			'renders',
			() => {
				component = new AnnouncementCarousel({store});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call fetchPosts when attaching',
			() => {
				component = new AnnouncementCarousel({store});

				expect(fetchPosts).toBeCalled();

				fetchPosts.mockClear();
			}
		);
	}
);