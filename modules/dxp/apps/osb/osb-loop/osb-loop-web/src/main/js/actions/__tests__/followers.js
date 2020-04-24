jest.unmock('../followers');
jest.unmock('../../lib/util');

import {CALL_API} from '../../middleware/api';
import {fetchFollowers} from '../followers';

const {classNameIds} = LoopConstants;

describe(
	'fetchFollowers',
	() => {
		it(
			'should return a fetchFollowers action for a person',
			() => {
				const action = fetchFollowers(
					{
						end: 10,
						start: 0
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.controller).toBe('feed');
				expect(apiCall.controllerMethod).toBe('viewFollowers.json');
			}
		);

		it(
			'should return a fetchFollowers action for divisions',
			() => {
				const classNameId = classNameIds.divisions;
				const end = 10;
				const id = 1234;
				const start = 0;

				const action = fetchFollowers(
					{
						classNameId,
						end,
						id,
						start
					}
				);

				const {data} = action[CALL_API];

				expect(data.classNameId).toBe(classNameId);
				expect(data.classPK).toBe(id);
				expect(data.end).toBe(end);
				expect(data.start).toBe(start);
			}
		);

		it(
			'should return a fetchFollowers action for people',
			() => {
				const classNameId = classNameIds.people;
				const end = 10;
				const id = 1234;
				const start = 0;

				const action = fetchFollowers(
					{
						classNameId,
						end,
						id,
						start
					}
				);

				const {data} = action[CALL_API];

				expect(data.classNameId).toBe(classNameId);
				expect(data.classPK).toBe(id);
				expect(data.end).toBe(end);
				expect(data.start).toBe(start);
			}
		);

		it(
			'should return a fetchFollowers action for topics',
			() => {
				const classNameId = classNameIds.topics;
				const end = 10;
				const id = 1234;
				const start = 0;

				const action = fetchFollowers(
					{
						classNameId,
						end,
						id,
						start
					}
				);

				const {data} = action[CALL_API];

				expect(data.classNameId).toBe(classNameId);
				expect(data.classPK).toBe(id);
				expect(data.end).toBe(end);
				expect(data.start).toBe(start);
			}
		);
	}
);