import {List} from 'immutable';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import SharedToSummary from '../SharedToSummary';

describe(
	'SharedToSummary',
	() => {
		it(
			'renders',
			() => {
				const store = mockStore();

				const component = new SharedToSummary(
					{
						creator: {},
						entitiesIList: List([LoopAssets.getPerson()]),
						store
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);