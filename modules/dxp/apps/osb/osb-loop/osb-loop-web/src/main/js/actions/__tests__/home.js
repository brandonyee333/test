jest.unmock('../home');

import {CALL_API} from '../../middleware/api';
import {actionTypes, clearSearch, fullSearch} from '../home';

describe(
	'Full Search',
	() => {
		const data = {
			childAssetEntrySetsLimit: 3,
			end: 5,
			itemsPerPage: 5,
			keywords: 'test',
			likedParticipantsLimit: 6,
			sessionTime: Date.now(),
			start: 1
		};

		it(
			'should clear the search',
			() => {
				const action = clearSearch();

				expect(typeof action).toBe('object');
				expect(action.type).toBe(actionTypes.CLEAR_SEARCH);
			}
		);

		it(
			'should call fullSearch action',
			() => {
				const action = fullSearch(data);

				const apiCall = action[CALL_API];

				expect(typeof action).toBe('object');
				expect(action.start).toBe(data.start + data.itemsPerPage);
				expect(apiCall.controllerMethod).toBe('fullSearch.json');
			}
		);
	}
);