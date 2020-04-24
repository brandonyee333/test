import {CALL_API} from '../middleware/api';

const controller = 'home';

export const actionTypes = {
	CLEAR_SEARCH: 'CLEAR_SEARCH',
	FULL_SEARCH_FAILURE: 'FULL_SEARCH_FAILURE',
	FULL_SEARCH_REQUEST: 'FULL_SEARCH_REQUEST',
	FULL_SEARCH_SUCCESS: 'FULL_SEARCH_SUCCESS'
};

export function clearSearch() {
	return {
		type: actionTypes.CLEAR_SEARCH
	};
}

export function fullSearch(data) {
	const {
		childAssetEntrySetsLimit,
		end,
		itemsPerPage,
		keywords,
		likedParticipantsLimit,
		sessionTime,
		start
	} = data;

	return {
		keywords,
		sessionTime,
		start: start + itemsPerPage,
		[CALL_API]: {
			controller,
			controllerMethod: 'fullSearch.json',
			data: {
				childAssetEntrySetsLimit,
				end,
				keywords,
				likedParticipantsLimit,
				start
			},
			method: 'GET',
			types: [actionTypes.FULL_SEARCH_REQUEST, actionTypes.FULL_SEARCH_SUCCESS, actionTypes.FULL_SEARCH_FAILURE]
		}
	};
}