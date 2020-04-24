import {Map, OrderedSet} from 'immutable';

import LoopConstants from '../lib/loop-constants';
import {actionTypes} from '../actions/following';
import {classNameIdToSchema, createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.FOLLOW_ENTITY_SUCCESS]: (state, {entityClassNameId, data, id}) => {

		const {
			followersCount,
			following,
			followingType,
			notifying,
			notifyingEmail
		} = data;

		const schema = classNameIdToSchema(entityClassNameId);

		return state.mergeIn(
			[schema, id, 'data'],
			{
				followersCount,
				following,
				followingType,
				notifying,
				notifyingEmail
			}
		).updateIn(
			['people', LoopConstants.currentPerson.entityClassPK, 'following', schema],
			Map(),
			followingSchema => {
				const dataList = followingSchema.get('data', OrderedSet());
				const total = followingSchema.get('total', 0);

				return Map(
					{
						data: following ? dataList.add(id) : dataList.filterNot(entityId => entityId === id),
						total: following ? total + 1 : total - 1
					}
				);
			}
		);
	}
};

export default createReducer(Map(), actionHandlers);