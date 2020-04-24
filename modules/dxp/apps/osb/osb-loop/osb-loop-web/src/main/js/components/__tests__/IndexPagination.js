import {List} from 'immutable';

import IndexPagination from '../IndexPagination';
import mockStore from '../../tests/mock-store';

describe(
	'IndexPagination',
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
				component = new IndexPagination(
					{
						entitiesIList: List([]),
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);