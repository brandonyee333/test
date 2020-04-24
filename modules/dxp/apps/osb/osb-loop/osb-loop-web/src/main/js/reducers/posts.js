import {fromJS, List, Map} from 'immutable';
import {get} from 'lodash';

import createBaseReducer from './crud';
import {actionTypes as feedActionTypes} from '../actions/feeds';
import {actionTypes} from '../actions/posts';
import {composeReducers, createReducer} from '../lib/util';

const actionHandlers = {
	[actionTypes.BOOKMARK_POST_SUCCESS]: (state, action) => {
		const {bookmarked, id} = action;

		return state.setIn(
			[id, 'data', 'payload', 'bookmarked'],
			bookmarked
		);
	},

	[actionTypes.CREATE_SUCCESS]: (state, action) => {
		const {assetEntrySetId, parentAssetEntrySetId} = action.data;

		return !parentAssetEntrySetId ? state : state.update(
			parentAssetEntrySetId,
			post => post.update(
				'data',
				Map(),
				data => data.update(
					'childAssetEntrySets',
					List(),
					comments => comments.push(assetEntrySetId)
				).update(
					'childAssetEntrySetsCount',
					0,
					count => count + 1
				)
			)
		);
	},

	[actionTypes.DESTROY_SUCCESS]: (state, action) => {
		const parentAssetEntrySetId = get(action, 'previous.data.parentAssetEntrySetId');

		const postId = action.id;

		return !parentAssetEntrySetId ? state : state.update(
			parentAssetEntrySetId,
			post => post.update(
				'data',
				Map(),
				data => data.update(
					'childAssetEntrySets',
					List(),
					ids => ids.filterNot(id => id === postId)
				).update(
					'childAssetEntrySetsCount',
					0,
					count => Math.max(count - 1, 0)
				)
			)
		);
	},

	[actionTypes.LIKE_POST_FAILURE]: (state, {id}) => {
		const postData = state.getIn([id, 'data']);

		const expectedLiked = postData.getIn(['payload', 'likedParticipants', 'liked'], false);

		const actualLikeTotal = postData.get('assetEntrySetLikesCount', 0) + (expectedLiked ? -1 : 1);

		return state.mergeDeepIn(
			[id, 'data'],
			fromJS(
				{
					assetEntrySetLikesCount: actualLikeTotal,
					payload: {
						likedParticipants: {liked: !expectedLiked}
					}
				}
			)
		);
	},

	[actionTypes.LIKE_POST_REQUEST]: (state, {id}) => {
		const postData = state.getIn([id, 'data']);

		const previousLiked = postData.getIn(['payload', 'likedParticipants', 'liked'], false);

		const newLikeTotal = postData.get('assetEntrySetLikesCount', 0) + (previousLiked ? -1 : 1);

		return state.mergeDeepIn(
			[id, 'data'],
			fromJS(
				{
					assetEntrySetLikesCount: newLikeTotal,
					payload: {
						likedParticipants: {liked: !previousLiked}
					}
				}
			)
		);
	},

	[actionTypes.LIKE_POST_SUCCESS]: (state, action) => {
		let liked = false;

		const {data, id} = action;

		const {likedParticipants} = data.payload;

		if (likedParticipants && likedParticipants.liked) {
			liked = likedParticipants.liked;
		}

		return state.mergeDeepIn(
			[id, 'data'],
			fromJS(
				{
					assetEntrySetLikesCount: data.assetEntrySetLikesCount,
					payload: {
						likedParticipants: {liked}
					}
				}
			)
		);
	},

	[feedActionTypes.CLEAR_MESSAGE_INFO_TRUNCATED]: (state, {postId}) => {
		return state.deleteIn([postId, 'messageInfo', 'truncated']);
	},

	[feedActionTypes.FETCH_COMMENTS_SUCCESS]: (state, action) => {
		const {
			data: {
				results,
				total
			},
			parentId
		} = action;

		return state.update(
			parentId,
			post => post.updateIn(
				['data', 'childAssetEntrySets'],
				List(),
				comments => results.reduceRight(
					(result, next) => {
						return result.includes(next) ? result : result.unshift(next);
					},
					comments
				)
			)
		).setIn(
			[parentId, 'data', 'childAssetEntrySetsCount'],
			total
		);
	},

	[feedActionTypes.GET_MESSAGE_POSTS_SUCCESS]: (state, action) => {
		const {data, id} = action;

		return state.mergeIn([id, 'messageInfo'], data);
	}
};

const overrideHandlers = {
	[actionTypes.FETCH_SUCCESS]: (state, {data}) => {
		const {assetEntrySetId: postId} = data;

		return state.setIn(
			[postId],
			fromJS(
				{
					commentCount: data.childAssetEntrySets.length,
					data,
					loading: false
				}
			)
		);
	},

	[actionTypes.UPDATE_SUCCESS]: (state, action) => {
		const {data, id} = action;

		return state.mergeIn(
			[id],
			{
				data: {
					...data,
					childAssetEntrySets: state.getIn([id, 'data', 'childAssetEntrySets']),
					childAssetEntrySetsCount: state.getIn([id, 'data', 'childAssetEntrySetsCount'])
				},
				loading: false
			}
		).updateIn(
			[id, 'messageInfo'],
			messageInfo => {
				let retVal = messageInfo;

				if (messageInfo && messageInfo.get('message')) {
					retVal = messageInfo.set('message', data.payload.message);
				}

				return retVal;
			}
		);
	}
};

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			overrideHandlers,
			primaryKey: 'assetEntrySetId'
		}
	),
	createReducer(Map(), actionHandlers)
);