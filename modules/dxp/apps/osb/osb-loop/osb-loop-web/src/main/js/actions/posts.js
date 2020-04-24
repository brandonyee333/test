import createBase from './crud';
import LoopConstants from '../lib/loop-constants';
import {CALL_API} from '../middleware/api';
import {INCLUDE} from '../middleware/include';
import {createActionTypes} from '../lib/util';
import {follow} from './following';

const name = 'post';

const controller = 'feed';

const base = createBase(
	{
		controller,
		name,
		plural: 'posts'
	}
);

const {
	classNameIds,
	loopStreamAliasIds: {
		bookmarks: bookmarksStreamId,
		following: followingStreamId
	}
} = LoopConstants;

const actionTypes = {
	...base.actionTypes,
	...createActionTypes('bookmark', name),
	...createActionTypes('like', name)
};

const {
	create,
	destroy,
	fetch,
	hydrate,
	search,
	update
} = base.actions;

export function bookmarkPost(id, bookmark = true, feedId) {
	const method = bookmark ? 'addToStream' : 'removeFromStream';

	return {
		bookmarked: bookmark,
		[CALL_API]: {
			controller,
			controllerMethod: `${method}.json`,
			data: {
				classNameId: classNameIds.posts,
				classPK: id,
				loopStreamId: bookmarksStreamId
			},
			types: [actionTypes.BOOKMARK_POST_REQUEST, actionTypes.BOOKMARK_POST_SUCCESS, actionTypes.BOOKMARK_POST_FAILURE]
		},
		feedId,
		[INCLUDE]: {
			classNameId: classNameIds.posts,
			id
		},
		id,
		removedBy: feedId === bookmarksStreamId ? 'bookmark' : null
	};
}

export function createPost({streamId, ...data}) {
	return {
		...create(data),
		streamId
	};
}

export function followPost(id, following, feedId) {
	return {
		...follow(classNameIds.posts, id, following),
		removedBy: feedId === followingStreamId ? 'follow' : null
	};
}

export function likePost(id, like = true) {
	const method = like ? 'like' : 'unlike';

	return {
		id,
		[CALL_API]: {
			controller,
			controllerMethod: `${method}.json`,
			data: {id},
			types: [actionTypes.LIKE_POST_REQUEST, actionTypes.LIKE_POST_SUCCESS, actionTypes.LIKE_POST_FAILURE]
		}
	};
}

export function updatePost({streamId, ...data}) {
	return {
		...update(data),
		streamId
	};
}

export {
	actionTypes,
	destroy as destroyPost,
	fetch as fetchPost,
	hydrate as hydratePost,
	search as searchPosts
};