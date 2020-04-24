jest.unmock('../../lib/selectors');

import {Map} from 'immutable';

import mockStore from '../../tests/mock-store';
import FeaturedTopicsModal from '../FeaturedTopicsModal';
import {fetchFeaturedTopics} from '../../actions/topics';

const store = mockStore(
	Map().setIn(
		['lists', 'featuredTopics'],
		Map({loading: true})
	)
);

fetchFeaturedTopics.mockImplementation(() => Promise.resolve());

describe(
	'FeaturedTopicsModal',
	() => {
		it(
			'renders',
			() => {
				const component = new FeaturedTopicsModal({store});

				expect(component).toMatchSnapshot();
			}
		);
	}
);