jest.unmock('../../actions/crud');
jest.unmock('../../actions/feeds');
jest.unmock('../../actions/following');
jest.unmock('../../actions/posts');
jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../feeds');

import {range} from 'lodash';

import {
	fromJS,
	is,
	Map,
	OrderedMap
} from 'immutable';

import LoopAssets from '../../tests/loop-assets';
import reducer from '../feeds';
import {actionTypes as followingActionTypes} from '../../actions/following';
import {actionTypes as homeActionTypes} from '../../actions/home';
import {actionTypes as postActionTypes} from '../../actions/posts';
import {actionTypes} from '../../actions/feeds';
import {getFeedId} from '../../lib/util';

const {classNameIds, loopStreamAliasIds, postTypes} = LoopConstants;

const {
	announcements: announcementsStreamId,
	announcementsSticky: announcementsStickyStreamId,
	bookmarks: bookmarksStreamId,
	following: followingStreamId
} = loopStreamAliasIds;

const defaultStreamId = 113;

const postId23 = 23;
const postId35 = 35;
const postId81 = 81;
const postId191 = 191;

describe(
	'Feeds Reducer',
	() => {
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

		function getFeedPostOMap(idsArray, feedId) {
			return OrderedMap(
				idsArray.map(
					postId => getFeedPost(postId, feedId)
				)
			);
		}

		function makePosts(...ids) {
			return ids.map(
				id => (
					{
						id
					}
				)
			);
		}

		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		const fetchPostsSuccessAction = {
			data: [postId23, postId81],
			entities: {
				posts: {
					[postId23]: {
						assetEntrySetId: postId23,
						childAssetEntrySets: [4, 5, 6]
					},
					[postId81]: {
						assetEntrySetId: postId81,
						childAssetEntrySets: [1, 2, 3]
					}
				}
			},
			id: defaultStreamId,
			type: actionTypes.FETCH_POSTS_SUCCESS
		};

		it(
			'should update loading on fetch comments failure',
			() => {
				const commentFeedId = '0-1';

				const action = {
					commentFeedId,
					type: actionTypes.FETCH_COMMENTS_FAILURE
				};

				const state = reducer(
					fromJS(
						{
							[commentFeedId]: {loading: true}
						}
					),
					action
				);

				expect(state.getIn([commentFeedId, 'loading'])).toBe(false);
			}
		);

		it(
			'should update loading on fetch comments request',
			() => {
				const commentFeedId = '0-1';

				const action = {
					commentFeedId,
					type: actionTypes.FETCH_COMMENTS_REQUEST
				};

				const state = reducer(
					fromJS(
						{
							[commentFeedId]: {loading: false}
						}
					),
					action
				);

				expect(state.getIn([commentFeedId, 'loading'])).toBe(true);
			}
		);

		it(
			'should update feed state on fetch comments success',
			() => {
				const commentFeedId = `${defaultStreamId}-1`;

				const action = {
					commentFeedId,
					data: {results: [postId23, 4, 5]},
					parentId: 1,
					type: actionTypes.FETCH_COMMENTS_SUCCESS
				};

				const state = reducer(
					fromJS(
						{
							[commentFeedId]: {
								loading: false,
								posts: getFeedPostOMap([1, 2], defaultStreamId)
							}
						}
					),
					action
				);

				const feed = state.get(commentFeedId);

				expect(feed).toBeTruthy();

				expect(feed.get('loading')).toBe(false);

				expect(feed.get('posts').first().get('id')).toBe(postId23);
			}
		);

		it(
			'should update feed state on get message posts success',
			() => {
				const action = {
					feedId: defaultStreamId,
					id: postId23,
					type: actionTypes.GET_MESSAGE_POSTS_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[defaultStreamId],
						{
							loading: false,
							posts: getFeedPostOMap([postId23, 2], defaultStreamId)
						}
					),
					action
				);

				const feed = state.get(defaultStreamId);

				expect(feed).toBeTruthy();

				expect(feed.get('loading')).toBe(false);
			}
		);

		it(
			'should update feed state on fetch posts request',
			() => {
				const action = {
					id: postId23,
					type: actionTypes.FETCH_POSTS_REQUEST
				};

				const state = reducer(Map(), action);

				const feed = state.get(postId23);

				expect(feed).toBeTruthy();

				expect(feed.get('loading')).toBe(true);
			}
		);

		it(
			'should update feed state on fetch posts success',
			() => {
				const initialState = Map().mergeIn(
					[defaultStreamId],
					{
						loading: true
					}
				);

				let state = reducer(initialState, fetchPostsSuccessAction);

				const feed = state.get(defaultStreamId);

				const posts = getFeedPostOMap([postId23, postId81, postId35, postId191], defaultStreamId);

				expect(feed.get('loading')).toBe(false);
				expect(is(feed.get('posts'), fromJS(posts.slice(0, 2)))).toBe(true);

				state = reducer(
					state,
					{
						...fetchPostsSuccessAction,
						data: [postId35, postId191],
						entities: {
							posts: {
								[postId35]: {
									assetEntrySetId: postId35,
									childAssetEntrySets: [7, 8, 9]
								},
								[postId191]: {
									assetEntrySetId: postId191,
									childAssetEntrySets: [10, 11, 12]
								}
							}
						},
						streamId: defaultStreamId
					}
				);

				expect(state.getIn([defaultStreamId, 'posts'])).toEqual(posts);
			}
		);

		it(
			'should prepend *new* posts on fetch posts success',
			() => {
				const initialState = Map().mergeIn(
					[defaultStreamId],
					{
						loading: true,
						posts: getFeedPostOMap([postId35, postId191], defaultStreamId)
					}
				);

				const action = {
					...fetchPostsSuccessAction,
					newPosts: true
				};

				const state = reducer(initialState, action);

				const posts = getFeedPostOMap([postId23, postId81, postId35, postId191], defaultStreamId);

				expect(is(state.getIn([defaultStreamId, 'posts']), fromJS(posts))).toBe(true);
			}
		);

		it(
			'should update feed state on fetch posts failure',
			() => {
				const action = {
					error: 'error',
					id: defaultStreamId,
					type: actionTypes.FETCH_POSTS_FAILURE
				};

				const state = reducer(Map(), action);

				expect(state.getIn([defaultStreamId, 'loading'])).toBe(false);
			}
		);

		it(
			'should update feed state on destroy success',
			() => {
				const action = {
					id: postId23,
					type: postActionTypes.DESTROY_SUCCESS
				};

				const state = reducer(
					Map(
						fromJS(
							{
								0: {
									posts: makePosts(1, 2, postId23)
								},
								1: {
									posts: makePosts(3, 4, postId23)
								},
								2: {
									posts: makePosts(6, 7, 8)
								}
							}
						)
					),
					action
				);

				expect(state.getIn(['0', 'posts']).size).toBe(2);
				expect(state.getIn(['1', 'posts']).size).toBe(2);
				expect(state.getIn(['2', 'posts']).size).toBe(3);
			}
		);

		it(
			'should update feed state on create post success',
			() => {
				const streamId = 5;

				const action = {
					data: {
						...LoopAssets.getPost(postId23)
					},
					streamId,
					type: postActionTypes.CREATE_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[streamId],
						{
							loading: false,
							posts: getFeedPostOMap([2, 3], streamId)
						}
					),
					action
				);

				const feedState = state.get(streamId);

				expect(feedState.get('posts').first().get('id')).toBe(postId23);
			}
		);

		it(
			'should update feed posts on filterRemovedPosts',
			() => {
				const action = {
					id: defaultStreamId,
					type: actionTypes.FILTER_REMOVED_POSTS
				};

				const state = reducer(
					Map().mergeIn(
						[defaultStreamId],
						{
							posts: [
								{
									id: 2,
									removedBy: 'bookmark'
								},
								{
									childFeedId: '2-3',
									id: 3,
									removedBy: 'follow'
								},
								{
									id: postId23,
									removedBy: null
								},
								{
									id: 5
								}
							]
						}
					).mergeIn(
						['2-3'],
						{
							posts: []
						}
					),
					action
				);

				expect(state.get('2-3')).toBeFalsy();

				const postsState = state.getIn([defaultStreamId, 'posts']);

				expect(postsState.size).toBe(2);
				expect(postsState.getIn([0, 'id'])).toBe(postId23);
			}
		);

		it(
			'should remove post on removePost',
			() => {
				const action = {
					id: defaultStreamId,
					postId: postId23,
					type: actionTypes.REMOVE_POST
				};

				const state = reducer(
					Map().mergeIn(
						[defaultStreamId],
						{
							posts: getFeedPostOMap([postId23, postId35, 27], defaultStreamId)
						}
					).mergeIn(
						[getFeedId(postId23, defaultStreamId)],
						{
							posts: getFeedPostOMap([28, 29])
						}
					),
					action
				);

				const postsState = state.getIn([defaultStreamId, 'posts']);

				expect(postsState.size).toBe(2);
				expect(postsState.first().get('id')).toBe(postId35);
			}
		);

		it(
			'should update removedBy on follow post success',
			() => {
				const action = {
					data: {
						following: true
					},
					entityClassNameId: classNameIds.posts,
					following: true,
					id: postId23,
					include: Map(
						{
							childAssetEntrySets: [],
							id: postId23
						}
					),
					type: followingActionTypes.FOLLOW_ENTITY_SUCCESS
				};

				let state = reducer(
					Map().mergeIn(
						[followingStreamId],
						{
							posts: getFeedPostOMap([postId23, postId23 + 1, postId23 + 2], followingStreamId)
						}
					),
					action
				);

				expect(state.getIn([followingStreamId, 'posts', postId23]).get('removedBy')).toBeFalsy();

				action.removedBy = 'follow';
				action.following = false;
				action.data.following = false;

				state = reducer(state, action);

				expect(state.getIn([followingStreamId, 'posts', postId23]).get('removedBy')).toBeTruthy();

				action.removedBy = null;
				action.following = false;
				action.data.following = false;

				state = reducer(state, action);

				expect(state.getIn([followingStreamId, 'posts', postId23])).toBeFalsy();
			}
		);

		it(
			'should return original state on follow entity success if schema is not posts',
			() => {
				const action = {
					schema: 'people',
					type: followingActionTypes.FOLLOW_ENTITY_SUCCESS
				};

				const initialState = Map();

				const state = reducer(initialState, action);

				expect(state).toBe(initialState);
			}
		);

		it(
			'should update removedBy on bookmark post success',
			() => {
				const action = {
					bookmarked: false,
					id: postId23,
					post: Map(
						{
							childAssetEntrySets: [],
							postId: postId23
						}
					),
					removedBy: 'bookmark',
					type: postActionTypes.BOOKMARK_POST_SUCCESS
				};

				let state = reducer(
					Map().mergeIn(
						[bookmarksStreamId],
						{
							posts: getFeedPostOMap([postId23, postId23 + 1, postId23 + 2], bookmarksStreamId)
						}
					),
					action
				);

				expect(state.getIn([bookmarksStreamId, 'posts']).first().get('removedBy')).toBeTruthy();

				action.bookmarked = true;

				state = reducer(state, action);

				expect(state.getIn([bookmarksStreamId, 'posts']).first().get('removedBy')).toBeFalsy();
			}
		);

		it(
			'should add post to bookmarks stream on post success',
			() => {
				const action = {
					bookmarked: true,
					id: postId23,
					include: Map(
						{
							childAssetEntrySets: [],
							postId: postId23
						}
					),
					type: postActionTypes.BOOKMARK_POST_SUCCESS
				};

				let state = reducer(
					Map().mergeIn(
						[bookmarksStreamId],
						{
							posts: getFeedPostOMap([postId23 + 1, postId23 + 2], bookmarksStreamId)
						}
					),
					{}
				);

				expect(state.getIn([bookmarksStreamId, 'posts']).first().get('id')).not.toBe(postId23);

				state = reducer(state, action);

				expect(state.getIn([bookmarksStreamId, 'posts']).first().get('id')).toBe(postId23);
			}
		);

		it(
			'should clear all feeds',
			() => {
				const action = {
					type: actionTypes.CLEAR_FEED
				};

				const state = reducer(
					Map().mergeIn(
						[defaultStreamId],
						{
							posts: makePosts(1, 2)
						}
					),
					action
				);

				expect(state.isEmpty()).toBe(true);
			}
		);

		it(
			'should update feed state on update post success',
			() => {
				const action = {
					data: {
						...LoopAssets.getPost(postId23)
					},
					streamId: bookmarksStreamId,
					type: postActionTypes.CREATE_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[followingStreamId],
						{
							loading: false,
							posts: getFeedPostOMap([2, 3, postId23], followingStreamId)
						}
					),
					action
				);

				const feedState = state.get(followingStreamId);

				expect(feedState.get('posts').first().get('id')).toBe(postId23);
			}
		);

		it(
			'should update search loading on full search request',
			() => {
				const keywords = 'foobar';

				const action = {
					keywords,
					type: homeActionTypes.FULL_SEARCH_REQUEST
				};

				const state = reducer(Map(), action);

				expect(state.getIn([keywords, 'loading'])).toBe(true);
			}
		);

		it(
			'should update search on full search success',
			() => {
				const keywords = 'foobar""';

				const action = {
					data: {
						pages: {
							results: [6, 7],
							total: 3
						},
						posts: {
							results: [1, 2, 3],
							total: 3
						}
					},
					entities: {
						pages: range(6, 8).reduce(
							(result, id) => {
								result[id] = LoopAssets.getPage(id);

								return result;
							},
							{}
						),
						posts: range(1, 4).reduce(
							(result, id) => {
								result[id] = LoopAssets.getPost(id);

								return result;
							},
							{}
						)
					},
					keywords,
					type: homeActionTypes.FULL_SEARCH_SUCCESS
				};

				const initialState = fromJS(
					{
						[keywords]: {
							pages: getFeedPostOMap([8]),
							posts: getFeedPostOMap([5, 4])
						}
					}
				);

				const state = reducer(initialState, action);

				const pageIds = state.getIn([keywords, 'pages']).keySeq().toJS();
				const postIds = state.getIn([keywords, 'posts']).keySeq().toJS();

				expect(pageIds).toEqual([8, 6, 7]);
				expect(postIds).toEqual([5, 4, 1, 2, 3]);
			}
		);

		it(
			'should ignore comments',
			() => {
				const action = {
					data: {},
					type: postActionTypes.UPDATE_SUCCESS
				};

				const initialState = Map();

				const state = reducer(initialState, action);

				expect(state).toBe(initialState);
			}
		);

		it(
			'should add a comment to it\'s parent\'s feed',
			() => {
				const parentAssetEntrySetId = 3;

				const action = {
					data: {
						...LoopAssets.getPost(postId23),
						parentAssetEntrySetId
					},
					streamId: announcementsStreamId,
					type: postActionTypes.CREATE_SUCCESS
				};

				const feedId = getFeedId(parentAssetEntrySetId, followingStreamId);

				const state = reducer(
					Map().mergeIn(
						[feedId],
						{
							postId: parentAssetEntrySetId,
							posts: getFeedPostOMap([postId35, postId81], feedId)
						}
					).mergeIn(
						[getFeedId(postId191, followingStreamId)],
						{
							postId: postId191,
							posts: OrderedMap()
						}
					),
					action
				);

				const postIds = state.getIn([feedId, 'posts']).keySeq().toJS();

				expect(postIds).toEqual([postId35, postId81, postId23]);
			}
		);

		it(
			'should add new announcement posts to the announcement stream',
			() => {
				const action = {
					data: {
						...LoopAssets.getPost(postId23),
						type: postTypes.announcement
					},
					streamId: announcementsStreamId,
					type: postActionTypes.CREATE_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[announcementsStreamId],
						{
							posts: OrderedMap()
						}
					),
					action
				);

				const postIds = state.getIn([announcementsStreamId, 'posts']).keySeq().toJS();

				expect(postIds).toEqual([postId23]);
			}
		);

		it(
			'should add sticky posts to the announcements sticky stream',
			() => {
				const action = {
					data: {
						...LoopAssets.getPost(postId23),
						stickyTime: Date.now() + 10000,
						type: postTypes.announcement
					},
					streamId: announcementsStickyStreamId,
					type: postActionTypes.CREATE_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[announcementsStickyStreamId],
						{
							posts: OrderedMap()
						}
					),
					action
				);

				const postIds = state.getIn([announcementsStickyStreamId, 'posts']).keySeq().toJS();

				expect(postIds).toEqual([postId23]);
			}
		);

		it(
			'should update announcements stream if a post is updated',
			() => {
				const action = {
					data: {
						...LoopAssets.getPost(postId23),
						type: postTypes.announcement
					},
					streamId: announcementsStreamId,
					type: postActionTypes.UPDATE_SUCCESS
				};

				const state = reducer(Map(), action);

				expect(state.getIn([announcementsStreamId, 'posts']).size).toBe(1);
			}
		);

		it(
			'should remove a post from announcements stream if no longer an announcement',
			() => {
				const action = {
					data: {
						...LoopAssets.getPost(postId23),
						type: postTypes.post
					},
					streamId: announcementsStreamId,
					type: postActionTypes.UPDATE_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[announcementsStreamId],
						{
							posts: getFeedPostOMap([postId23], announcementsStreamId)
						}
					),
					action
				);

				const postIds = state.getIn([announcementsStreamId, 'posts']).keySeq().toJS();

				expect(postIds).toEqual([]);
			}
		);

		it(
			'should add a post to the sticky stream when updated',
			() => {
				const action = {
					data: {
						...LoopAssets.getPost(postId23),
						stickyTime: Date.now() + 10000,
						type: postTypes.announcement
					},
					streamId: announcementsStickyStreamId,
					type: postActionTypes.UPDATE_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[announcementsStickyStreamId],
						{
							posts: getFeedPostOMap([postId23], announcementsStickyStreamId)
						}
					),
					action
				);

				const postIds = state.getIn([announcementsStickyStreamId, 'posts']).keySeq().toJS();

				expect(postIds).toEqual([postId23]);
			}
		);
	}
);