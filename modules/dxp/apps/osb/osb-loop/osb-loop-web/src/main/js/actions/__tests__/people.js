jest.unmock('../crud');
jest.unmock('../people');
jest.unmock('../../lib/util');

import * as actions from '../people';
import {CALL_API} from '../../middleware/api';

describe(
	'fetchDivisions',
	() => {
		const {fetchDivisions} = actions;

		it(
			'should return an api call to fetch divisions',
			() => {
				const id = 1;

				const action = fetchDivisions(
					{
						end: 10,
						id,
						start: 0,
						type: 1
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewDivisions.json');
			}
		);

		it(
			'should return a default start',
			() => {
				const id = 1;

				const action = fetchDivisions(
					{
						end: 10,
						id,
						type: 1
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.start).toBeDefined();
			}
		);
	}
);

describe(
	'fetchFollowing',
	() => {
		const {fetchFollowing} = actions;

		it(
			'should return an api call to fetch following divisions',
			() => {
				const id = 1;

				const action = fetchFollowing(
					{
						classNameId: LoopConstants.classNameIds.topics,
						end: 10,
						id,
						start: 0
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewFollowingTopics.json');
			}
		);
	}
);

describe(
	'fetchLeadingDivisions',
	() => {
		const {fetchLeadingDivisions} = actions;

		it(
			'should return an api call to fetch leading divisions',
			() => {
				const id = 1;

				const action = fetchLeadingDivisions(id);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewLeadingDivisions.json');
			}
		);
	}
);

describe(
	'fetchManagers',
	() => {
		const {fetchManagers} = actions;

		it(
			'should return an api call to fetch managers',
			() => {
				const id = 1;

				const action = fetchManagers(
					{
						end: 10,
						id,
						start: 0
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewManagers.json');
			}
		);

		it(
			'should return a default start of 0',
			() => {
				const id = 1;

				const action = fetchManagers(
					{
						end: 10,
						id
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.start).toBe(0);
			}
		);
	}
);

describe(
	'fetchSubordinates',
	() => {
		const {fetchSubordinates} = actions;

		it(
			'should return an api call to fetch subordinates',
			() => {
				const id = 1;

				const action = fetchSubordinates(
					{
						end: 10,
						id,
						start: 0
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewSubordinates.json');
			}
		);

		it(
			'should return a default start of 0',
			() => {
				const id = 1;

				const action = fetchSubordinates(
					{
						end: 10,
						id
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.start).toBe(0);
			}
		);
	}
);

describe(
	'addPerson',
	() => {
		const {addPerson} = actions;

		it(
			'should return an api call to fetch following divisions',
			() => {
				const obj = {
					emailAddress: 'foo.bar@liferay.com',
					firstName: 'Foo',
					lastName: 'Bar'
				};

				const action = addPerson(obj);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('add.json');
				expect(apiCall.data.emailAddress).toBe(obj.emailAddress);
				expect(apiCall.data.firstName).toBe(obj.firstName);
				expect(apiCall.data.lastName).toBe(obj.lastName);
			}
		);
	}
);

describe(
	'fetchNewPeople',
	() => {
		const {fetchNewPeople} = actions;

		it(
			'should return an api call to fetch new people',
			() => {
				const action = fetchNewPeople();

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('viewNewPeople.json');
			}
		);
	}
);

describe(
	'fetchExpertise',
	() => {
		const {fetchExpertise} = actions;

		it(
			'should return an api call to fetch expertise',
			() => {
				const id = 1;

				const action = fetchExpertise(id);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewVerifiedLoopTopics.json');
			}
		);
	}
);