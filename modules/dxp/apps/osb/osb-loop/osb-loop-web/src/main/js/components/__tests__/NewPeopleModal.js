jest.unmock('../../lib/selectors');

import {Map} from 'immutable';

import mockStore from '../../tests/mock-store';
import NewPeopleModal from '../NewPeopleModal';
import {fetchNewPeople} from '../../actions/people';

const store = mockStore(
	Map().setIn(
		['lists', 'newPeople'],
		Map({loading: true})
	)
);

fetchNewPeople.mockImplementation(() => Promise.resolve());

describe(
	'NewPeopleModal',
	() => {
		it(
			'renders',
			() => {
				const component = new NewPeopleModal({store});

				expect(component).toMatchSnapshot();
			}
		);
	}
);