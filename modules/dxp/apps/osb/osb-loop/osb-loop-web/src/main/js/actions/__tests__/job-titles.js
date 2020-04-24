jest.unmock('../job-titles');

import * as actions from '../job-titles';
import {CALL_API} from '../../middleware/api';

describe(
	'setInactive',
	() => {
		const {setInactive} = actions;

		it(
			'should return an api call to call deactivate',
			() => {
				const action = setInactive(
					{
						id: 1,
						inactive: true
					}
				);

				expect(action[CALL_API]).toMatchSnapshot();
			}
		);

		it(
			'should return an api call to call activate',
			() => {
				const action = setInactive(
					{
						id: 1,
						inactive: false
					}
				);

				expect(action[CALL_API]).toMatchSnapshot();
			}
		);
	}
);