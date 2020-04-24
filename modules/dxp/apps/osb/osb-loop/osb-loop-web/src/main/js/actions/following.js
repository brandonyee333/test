import LoopConstants from '../lib/loop-constants';
import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';
import {INCLUDE} from '../middleware/include';

export const actionTypes = createActionTypes('follow', 'entity');

export function follow(classNameId, id, following = true, followingType = LoopConstants.followingType.full) {
	const method = following ? 'addToStream' : 'removeFromStream';

	return {
		[CALL_API]: {
			controller: 'feed',
			controllerMethod: `${method}.json`,
			data: {
				classNameId,
				classPK: id,
				followingType,
				loopStreamAliasId: LoopConstants.loopStreamAliasIds.following
			},
			types: [actionTypes.FOLLOW_ENTITY_REQUEST, actionTypes.FOLLOW_ENTITY_SUCCESS, actionTypes.FOLLOW_ENTITY_FAILURE]
		},
		[INCLUDE]: {
			classNameId,
			id
		},
		entityClassNameId: classNameId,
		id
	};
}