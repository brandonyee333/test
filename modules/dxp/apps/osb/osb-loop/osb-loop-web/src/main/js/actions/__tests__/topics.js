jest.unmock('../topics');

import * as actions from '../topics';
import {CALL_API} from '../../middleware/api';

describe(
	'fetchExperts',
	() => {
		const {fetchExperts} = actions;

		it(
			'should return an api call to fetch experts',
			() => {
				const end = 5;
				const id = 1;
				const start = 0;

				const action = fetchExperts(
					{
						end,
						id,
						start
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewVerifiedExperts.json');
			}
		);
	}
);

describe(
	'setOwnTopic',
	() => {
		const {setOwnTopic} = actions;

		it(
			'should return an api call to fetch experts',
			() => {
				const id = 1;

				const action = setOwnTopic(id);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('setOwnTopic.json');
			}
		);
	}
);

describe(
	'removeOwnTopic',
	() => {
		const {removeOwnTopic} = actions;

		it(
			'should return an api call to fetch experts',
			() => {
				const id = 1;

				const action = removeOwnTopic(id);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('removeOwnTopic.json');
			}
		);
	}
);

describe(
	'addFeaturedTopic',
	() => {
		const {addFeaturedTopic} = actions;

		it(
			'should return an api call to add a featured topic',
			() => {
				const id = 1;

				const action = addFeaturedTopic(id);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('addFeatured.json');
				expect(apiCall.data.id).toBe(id);
			}
		);
	}
);

describe(
	'fetchFeaturedTopics',
	() => {
		const {fetchFeaturedTopics} = actions;

		it(
			'should return an api call to view featured topics',
			() => {
				const action = fetchFeaturedTopics();

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('viewFeatured.json');
			}
		);
	}
);

describe(
	'removeFeaturedTopic',
	() => {
		const {removeFeaturedTopic} = actions;

		it(
			'should return an api call to view featured topics',
			() => {
				const id = 1;

				const action = removeFeaturedTopic(id);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('removeFeatured.json');
				expect(apiCall.data.id).toBe(id);
			}
		);
	}
);