import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

export const actionTypes = createActionTypes('fetch', 'followers');

export function fetchFollowers({classNameId, end, id, start}) {
	return {
		[CALL_API]: {
			controller: 'feed',
			controllerMethod: 'viewFollowers.json',
			data: {
				classNameId,
				classPK: id,
				end,
				start
			},
			method: 'GET',
			types: [actionTypes.FETCH_FOLLOWERS_REQUEST, actionTypes.FETCH_FOLLOWERS_SUCCESS, actionTypes.FETCH_FOLLOWERS_FAILURE]
		},
		end,
		entityClassNameId: classNameId,
		id,
		start
	};
}