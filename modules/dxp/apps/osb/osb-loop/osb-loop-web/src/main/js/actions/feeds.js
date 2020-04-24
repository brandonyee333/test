import LoopConstants from '../lib/loop-constants';
import {CALL_API} from '../middleware/api';
import {createActionTypes, streamIdToKeys} from '../lib/util';

const controller = 'feed';

export const actionTypes = {
	...createActionTypes('fetch', 'comments'),
	...createActionTypes('fetch', 'posts'),
	...createActionTypes('get_message', 'posts'),
	CLEAR_FEED: 'CLEAR_FEED',
	CLEAR_MESSAGE_INFO_TRUNCATED: 'CLEAR_MESSAGE_INFO_TRUNCATED',
	FILTER_REMOVED_POSTS: 'FILTER_REMOVED_POSTS',
	REMOVE_POST: 'REMOVE_POST'
};

export function clearFeed() {
	return {
		type: actionTypes.CLEAR_FEED
	};
}

export function clearMessageInfoTruncated(postId) {
	return {
		postId,
		type: actionTypes.CLEAR_MESSAGE_INFO_TRUNCATED
	};
}

export function fetchComments(request) {
	const {
		commentFeedId,
		itemsPerPage,
		parentId,
		replaceCommentFeed,
		sessionTime,
		start = 0
	} = request;

	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewOldComments.json',
			data: {
				createTime: sessionTime,
				end: itemsPerPage,
				parentAssetEntrySetId: parentId,
				start
			},
			types: [actionTypes.FETCH_COMMENTS_REQUEST, actionTypes.FETCH_COMMENTS_SUCCESS, actionTypes.FETCH_COMMENTS_FAILURE]
		},
		commentFeedId,
		parentId,
		replaceCommentFeed,
		sessionTime
	};
}

const {childAssetEntrySetsLimit, likedParticipantsLimit, loopStreamAliasIds} = LoopConstants;

export function fetchPosts(request) {
	const {
		id,
		itemsPerPage,
		newPosts,
		sessionTime
	} = request;

	const data = {
		childAssetEntrySetsLimit,
		end: itemsPerPage,
		likedParticipantsLimit,
		loopStreamAliasId: id,
		start: 0
	};

	const {classNameId, classPK} = streamIdToKeys(id);

	let method;

	if (classPK) {
		data.classNameId = classNameId;
		data.classPK = classPK;
		data.createTime = sessionTime;

		method = newPosts ? 'viewNewFeed' : 'viewOldFeed';
	}
	else {
		data.time = sessionTime;

		method = 'viewMyFeed';

		if (id === loopStreamAliasIds.following) {
			data.stickyTime = sessionTime;
		}
		else if (id === loopStreamAliasIds.announcementsSticky) {
			data.stickyOnly = true;
		}
	}

	return {
		id,
		newPosts,
		sessionTime,
		[CALL_API]: {
			controller,
			controllerMethod: `${method}.json`,
			data,
			types: [actionTypes.FETCH_POSTS_REQUEST, actionTypes.FETCH_POSTS_SUCCESS, actionTypes.FETCH_POSTS_FAILURE]
		}
	};
}

export function filterRemovedPosts(id) {
	return {
		id,
		type: actionTypes.FILTER_REMOVED_POSTS
	};
}

export function getMessage(feedId, id, truncated) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'getMessage.json',
			data: {
				id,
				truncated
			},
			types: [actionTypes.GET_MESSAGE_POSTS_REQUEST, actionTypes.GET_MESSAGE_POSTS_SUCCESS, actionTypes.GET_MESSAGE_POSTS_FAILURE]
		},
		feedId,
		id
	};
}

export function removePost(id, postId) {
	return {
		id,
		postId,
		type: actionTypes.REMOVE_POST
	};
}