import {Map} from 'immutable';

import AlertFeed from '../AlertFeed';
import mockStore from '../../tests/mock-store';

describe(
	'AlertFeed',
	() => {
		const store = mockStore(Map().setIn(['alerts'], Map()));

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
				component = new AlertFeed({store});

				expect(component).toMatchSnapshot();
			}
		);
	}
);