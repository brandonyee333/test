import {fromJS, Map, OrderedMap} from 'immutable';

import LoopConstants from '../lib/loop-constants';
import {actionTypes as pageActionTypes} from '../actions/pages';
import {actionTypes as postActionTypes} from '../actions/posts';
import {actionTypes as followingActionTypes} from '../actions/following';
import {actionTypes as homeActionTypes} from '../actions/home';
import {actionTypes} from '../actions/feeds';
import {updateLoading} from './crud';
import {PAGES, POSTS} from '../lib/router-constants';

import {
	createReducer,
	getFeedId,
	getPostType,
	mergeWithPrev
} from '../lib/util';

const {
	announcements: announcementsStreamId,
	announcementsSticky: announcementsStickyStreamId,
	bookmarks: bookmarkStreamId,
	following: followingStreamId
} = LoopConstants.loopStreamAliasIds;

const {classNameIds} = LoopConstants;

const HOME_STREAMS_ARRAY = [
	announcementsStreamId,
	announcementsStickyStreamId,
	bookmarkStreamId,
	followingStreamId
];

function addPostToFeed(state, feedId, feedPost) {
	return state.updateIn(
		[feedId, 'posts'],
		OrderedMap(),
		posts => feedPost.concat(posts)
	);
}

function getChildFeeds(feedPosts, postsData) {
	return feedPosts.reduce(
		(result, feedPost) => {
			const childFeedId = feedPost.get('childFeedId');
			const feedPostId = feedPost.get('id');

			result[childFeedId] = getFeedObj(
				{
					postId: feedPostId,
					posts: getFeedAssetEntrySetOMap(postsData[feedPostId].childAssetEntrySets, childFeedId)
				}
			);

			return result;
		},
		{}
	);
}

function getFeedObj(data) {
	const {
		loading = false,
		postId,
		posts = OrderedMap()
	} = data;

	return {
		loading,
		postId,
		posts
	};
}

function getFeedPost(postId, feedId) {
	return [
		postId,
		fromJS(
			{
				childFeedId: `${feedId}-${postId}`,
				id: postId
			}
		)
	];
}

function getFeedAssetEntrySetOMap(idsArray, feedId) {
	return OrderedMap(
		idsArray.map(
			postId => getFeedPost(postId, feedId)
		)
	);
}

function removeFeed(state, feedId) {
	const childFeedIds = state.getIn(
		[feedId, 'posts'],
		OrderedMap()
	).valueSeq().map(post => post.get('childFeedId'));

	return state.filterNot(
		(value, key) => key === feedId || childFeedIds.contains(key)
	);
}

function removePostFromFeed(state, postId, feedId) {
	state = state.updateIn(
		[feedId, 'posts'],
		OrderedMap(),
		posts => posts.filterNot(post => post.get('id') === postId)
	);

	return removeFeed(state, getFeedId(postId, feedId));
}

function updateSearchData(state, action, type = POSTS) {
	const {data, entities, keywords} = action;

	const newFeedAssetEntrySet = getFeedAssetEntrySetOMap(data, keywords);

	state = state.updateIn(
		[keywords, type],
		OrderedMap(),
		posts => {
			return action.newPosts ? newFeedAssetEntrySet.merge(posts) : posts.merge(newFeedAssetEntrySet);
		}
	).setIn(
		[keywords, 'loading'],
		false
	);

	if (type === POSTS) {
		state = state.merge(getChildFeeds(newFeedAssetEntrySet, entities.posts));
	}

	return state;
}

function updateSearchLoading(loading) {
	return (state, {keywords}) => state.setIn([keywords, 'loading'], loading);
}

function updateRemovedBy(state, data) {
	const {
		feedId,
		postId,
		removedBy,
		toggle
	} = data;

	return state.updateIn(
		[feedId, 'posts'],
		OrderedMap(),
		posts => posts.map(
			post => {
				return post.get('id') !== postId ? post : post.set(
					'removedBy',
					toggle ? null : removedBy
				);
			}
		)
	);
}

function updateCommentsFeedLoading(value) {
	return (state, {commentFeedId}) => {
		return state.setIn(
			[commentFeedId, 'loading'],
			value
		);
	};
}

const actionHandlers = {
	[actionTypes.CLEAR_FEED]: state => state.clear(),

	[actionTypes.FETCH_COMMENTS_FAILURE]: updateCommentsFeedLoading(false),

	[actionTypes.FETCH_COMMENTS_REQUEST]: updateCommentsFeedLoading(true),

	[actionTypes.FETCH_COMMENTS_SUCCESS]: (state, action) => {
		const {
			commentFeedId,
			data: {results},
			parentId,
			replaceCommentFeed
		} = action;

		const newComments = getFeedAssetEntrySetOMap(results, commentFeedId);

		const currComments = state.getIn([commentFeedId, 'posts'], OrderedMap());

		return state.mergeIn(
			[commentFeedId],
			getFeedObj(
				{
					postId: parentId,
					posts: replaceCommentFeed ? newComments : newComments.merge(currComments)
				}
			)
		);
	},

	[actionTypes.FETCH_POSTS_FAILURE]: updateLoading(false),

	[actionTypes.FETCH_POSTS_REQUEST]: updateLoading(true),

	[actionTypes.FETCH_POSTS_SUCCESS]: (state, action) => {
		const {
			data,
			entities,
			id,
			newPosts
		} = action;

		const newFeedPosts = getFeedAssetEntrySetOMap(data, id);

		return state.updateIn(
			[id, 'posts'],
			OrderedMap(),
			posts => {
				return newPosts ? newFeedPosts.merge(posts) : posts.merge(newFeedPosts);
			}
		).setIn(
			[id, 'loading'],
			false
		).merge(
			getChildFeeds(newFeedPosts, entities.posts)
		);
	},

	[actionTypes.FILTER_REMOVED_POSTS]: (state, {id}) => {
		const deletedChildFeedIds = [];

		return state.updateIn(
			[id, 'posts'],
			OrderedMap(),
			posts => posts.filterNot(
				post => {
					const childFeedId = post.get('childFeedId');
					const removedBy = post.get('removedBy');

					if (childFeedId && removedBy) {
						deletedChildFeedIds.push(childFeedId);
					}

					return removedBy;
				}
			)
		).filterNot(
			(feed, key) => deletedChildFeedIds.includes(key)
		);
	},

	[actionTypes.REMOVE_POST]: (state, {id, postId}) => {
		return removePostFromFeed(state, postId, id);
	},

	[followingActionTypes.FOLLOW_ENTITY_SUCCESS]: (state, action) => {
		const {
			data,
			entityClassNameId,
			id,
			removedBy
		} = action;

		if (entityClassNameId === classNameIds.posts) {
			const post = action.include;

			const {following} = data;

			const childFeedId = getFeedId(id, followingStreamId);

			if (following && !removedBy) {
				const newPost = OrderedMap([getFeedPost(id, followingStreamId)]);

				state = state.updateIn(
					[followingStreamId, 'posts'],
					OrderedMap(),
					posts => mergeWithPrev(newPost, posts)
				).mergeIn(
					[childFeedId],
					getFeedObj(
						{
							postId: id,
							posts: getFeedAssetEntrySetOMap(post.get('childAssetEntrySets'), childFeedId)
						}
					)
				);
			}
			else if (!following && !removedBy) {
				state = state.updateIn(
					[followingStreamId, 'posts'],
					OrderedMap(),
					posts => posts.filterNot(
						post => post.get('id') === id
					)
				);
			}
			else {
				state = updateRemovedBy(
					state,
					{
						feedId: followingStreamId,
						postId: id,
						removedBy,
						toggle: following
					}
				);
			}
		}

		return state;
	},

	[homeActionTypes.FULL_SEARCH_FAILURE]: updateSearchLoading(false),

	[homeActionTypes.FULL_SEARCH_REQUEST]: updateSearchLoading(true),

	[homeActionTypes.FULL_SEARCH_SUCCESS]: (state, action) => {
		const {
			data: {pages, posts},
			entities,
			keywords
		} = action;

		const newFeedPages = getFeedAssetEntrySetOMap(pages.results, keywords);
		const newFeedPosts = getFeedAssetEntrySetOMap(posts.results, keywords);

		return state.updateIn(
			[keywords, 'pages'],
			OrderedMap(),
			pages => pages.merge(newFeedPages)
		).updateIn(
			[keywords, 'posts'],
			OrderedMap(),
			posts => posts.merge(newFeedPosts)
		).setIn(
			[keywords, 'loading'],
			false
		).merge(
			getChildFeeds(newFeedPosts, entities.posts)
		);
	},

	[pageActionTypes.SEARCH_FAILURE]: updateSearchLoading(false),

	[pageActionTypes.SEARCH_REQUEST]: updateSearchLoading(true),

	[pageActionTypes.SEARCH_SUCCESS]: (state, action) => updateSearchData(state, action, PAGES),

	[postActionTypes.BOOKMARK_POST_SUCCESS]: (state, action) => {
		const {bookmarked, id, removedBy} = action;

		const post = action.include;

		const childFeedId = getFeedId(id, bookmarkStreamId);

		if (bookmarked && !removedBy) {
			const newPost = OrderedMap([getFeedPost(id, bookmarkStreamId)]);

			state = state.updateIn(
				[bookmarkStreamId, 'posts'],
				OrderedMap(),
				posts => mergeWithPrev(newPost, posts)
			).mergeIn(
				[childFeedId],
				getFeedObj(
					{
						postId: id,
						posts: getFeedAssetEntrySetOMap(post.get('childAssetEntrySets'), childFeedId)
					}
				)
			);
		}
		else {
			state = updateRemovedBy(
				state,
				{
					feedId: bookmarkStreamId,
					postId: id,
					removedBy,
					toggle: bookmarked
				}
			);
		}

		return state;
	},

	[postActionTypes.CREATE_SUCCESS]: (state, action) => {
		const {data, streamId} = action;

		const {
			assetEntrySetId,
			parentAssetEntrySetId,
			stickyTime,
			type
		} = data;

		const newPost = OrderedMap([getFeedPost(assetEntrySetId, streamId)]);

		const childFeedObj = getFeedObj({postId: assetEntrySetId});

		if (parentAssetEntrySetId) {
			state = state.map(
				feed => {
					if (feed.get('postId') === parentAssetEntrySetId) {
						feed = feed.update(
							'posts',
							OrderedMap(),
							posts => posts.merge(newPost)
						);
					}

					return feed;
				}
			);
		}
		else if (HOME_STREAMS_ARRAY.includes(streamId)) {
			const sticky = stickyTime > Date.now();

			if (type === getPostType('announcement')) {
				state = addPostToFeed(
					state,
					announcementsStreamId,
					newPost
				).mergeIn(
					[getFeedId(assetEntrySetId, announcementsStreamId)],
					childFeedObj
				);
			}

			if (sticky) {
				state = addPostToFeed(state, announcementsStickyStreamId, newPost);
			}
			else {
				state = addPostToFeed(
					state,
					followingStreamId,
					newPost
				).mergeIn(
					[getFeedId(assetEntrySetId, followingStreamId)],
					childFeedObj
				);
			}
		}
		else {
			state = addPostToFeed(
				state,
				streamId,
				newPost
			).mergeIn(
				[getFeedId(assetEntrySetId, streamId)],
				childFeedObj
			);
		}

		return state;
	},

	[postActionTypes.DESTROY_SUCCESS]: (state, {id}) => {
		return state.filterNot(
			(value, key) => value.get('postId') === id
		).map(
			feed => feed.update(
				'posts',
				OrderedMap(),
				posts => posts.filterNot(
					post => post.get('id') === id
				)
			)
		);
	},

	[postActionTypes.FETCH_SUCCESS]: (state, action) => {
		const {
			data: {
				assetEntrySetId: postId,
				childAssetEntrySets
			},
			entities
		} = action;

		const feedPostOMap = getFeedAssetEntrySetOMap(childAssetEntrySets, postId);

		let retVal = state.mergeIn(
			[postId],
			getFeedObj(
				{
					postId,
					posts: feedPostOMap
				}
			)
		);

		if (childAssetEntrySets.length && entities.posts) {
			retVal = retVal.merge(
				getChildFeeds(
					feedPostOMap,
					entities.posts
				)
			);
		}

		return retVal;
	},

	[postActionTypes.SEARCH_FAILURE]: updateSearchLoading(false),

	[postActionTypes.SEARCH_REQUEST]: updateSearchLoading(true),

	[postActionTypes.SEARCH_SUCCESS]: updateSearchData,

	[postActionTypes.UPDATE_SUCCESS]: (state, action) => {
		const {data, streamId} = action;

		const {
			assetEntrySetId,
			parentAssetEntrySetId,
			stickyTime,
			type
		} = data;

		if (!parentAssetEntrySetId && HOME_STREAMS_ARRAY.includes(streamId)) {
			const newPost = OrderedMap([getFeedPost(assetEntrySetId, streamId)]);

			const announcement = type === getPostType('announcement');
			const sticky = stickyTime > Date.now();

			if (announcement) {
				state = addPostToFeed(state, announcementsStreamId, newPost);
			}
			else {
				state = removePostFromFeed(state, assetEntrySetId, announcementsStreamId);
			}

			if (announcement && sticky) {
				state = addPostToFeed(state, announcementsStickyStreamId, newPost);

				state = removePostFromFeed(state, assetEntrySetId, followingStreamId);
			}
			else {
				state = addPostToFeed(state, followingStreamId, newPost);

				state = removePostFromFeed(state, assetEntrySetId, announcementsStickyStreamId);
			}
		}

		return state;
	}
};

export default createReducer(Map(), actionHandlers);