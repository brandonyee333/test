jest.unmock('../../lib/selectors');

import {Map} from 'immutable';

import mockStore from '../../tests/mock-store';
import NewDivisionsModal from '../NewDivisionsModal';
import {fetchNewDivisions} from '../../actions/divisions';

const store = mockStore(
	Map().setIn(
		['lists', 'newDivisions'],
		Map({loading: true})
	)
);

fetchNewDivisions.mockImplementation(() => Promise.resolve());

describe(
	'NewDivisionsModal',
	() => {
		it(
			'renders',
			() => {
				const component = new NewDivisionsModal({store});

				expect(component).toMatchSnapshot();
			}
		);
	}
);