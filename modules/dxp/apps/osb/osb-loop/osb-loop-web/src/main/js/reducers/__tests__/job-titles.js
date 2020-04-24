jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../job-titles');

import {Map} from 'immutable';

import reducer from '../job-titles';
import {actionTypes} from '../../actions/job-titles';

describe(
	'Job Titles Reducer',
	() => {
		it(
			'should export a function',
			() => {
				expect(reducer instanceof Function).toBe(true);
			}
		);

		it(
			'should set remove an entity if the id is a string',
			() => {
				const entityClassPK = 0;
				const id = 'tests';

				const action = {
					data: {
						entityClassPK,
						name: 'tests'
					},
					id,
					type: actionTypes.FETCH_SUCCESS
				};

				const prevState = Map({[id]: {loading: true}});

				expect(prevState.get(id)).toBeTruthy();

				const state = reducer(prevState, action);

				expect(state.get(id)).toBeFalsy();
				expect(state.getIn([entityClassPK, 'loading'])).toBe(false);
			}
		);
	}
);