jest.unmock('../../actions/crud');
jest.unmock('../../actions/feeds');
jest.unmock('../../actions/posts');
jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../posts');

import {Map} from 'immutable';

import reducer from '../posts';
import {actionTypes} from '../../actions/posts';
import {actionTypes as feedActionTypes} from '../../actions/feeds';

describe(
	'Posts Reducer',
	() => {
		it(
			'should set commentCount on fetch success',
			() => {
				const id = 5;

				const action = {
					data: {
						assetEntrySetId: 5,
						childAssetEntrySets: [1, 2, 3]
					},
					id,
					type: actionTypes.FETCH_SUCCESS
				};

				const state = Map();

				expect(state.get(id)).toBeFalsy();

				const postState = reducer(state, action);

				expect(postState.getIn([id, 'commentCount'])).toBe(3);
			}
		);

		it(
			'should update post on like post success',
			() => {
				const id = 5;

				const action = {
					data: {
						assetEntrySetId: id,
						assetEntrySetLikesCount: 7,
						payload: {
							likedParticipants: {
								liked: true
							}
						}
					},
					id,
					type: actionTypes.LIKE_POST_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[id],
						{
							data: {
								assetEntrySetLikesCount: 6,
								payload: {
									likedParticipants: {
										liked: false
									}
								}
							},
							loading: false
						}
					),
					action
				);

				const postState = state.get(id);

				expect(postState.get('loading')).toBe(false);
				expect(postState.getIn(['data', 'assetEntrySetLikesCount'])).toBe(7);
				expect(postState.getIn(['data', 'payload', 'likedParticipants', 'liked'])).toBe(true);
			}
		);

		it(
			'should default to false if unable to find key in payload',
			() => {
				const id = 5;

				const action = {
					data: {
						assetEntrySetId: id,
						assetEntrySetLikesCount: 7,
						payload: {}
					},
					id,
					type: actionTypes.LIKE_POST_SUCCESS
				};

				const state = reducer(Map(), action);

				const postState = state.get(id);

				expect(postState.getIn(['data', 'payload', 'likedParticipants', 'liked'])).toBe(false);
			}
		);

		it(
			'should update parent post on create success',
			() => {
				const action = {
					data: {
						assetEntrySetId: 2,
						parentAssetEntrySetId: 1
					},
					type: actionTypes.CREATE_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[1],
						{
							data: {
								childAssetEntrySets: [50, 51, 52]
							},
							loading: false
						}
					),
					action
				);

				expect(state.getIn([1, 'data', 'childAssetEntrySets']).size).toBe(4);
			}
		);

		it(
			'should return original state if no parent on create success',
			() => {
				const action = {
					data: {
						assetEntrySetId: 2
					},
					type: actionTypes.CREATE_SUCCESS
				};

				const state = reducer(Map(), action);

				expect(state.size).toBe(1);
			}
		);

		it(
			'should update parent post childAssetEntrySets on destroy success',
			() => {
				const action = {
					id: 52,
					previous: {
						data: {
							assetEntrySetId: 52,
							parentAssetEntrySetId: 1
						}
					},
					type: actionTypes.DESTROY_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[1],
						{
							data: {
								childAssetEntrySets: [50, 51, 52]
							},
							loading: false
						}
					),
					action
				);

				expect(state.getIn([1, 'data', 'childAssetEntrySets']).size).toBe(2);
			}
		);

		it(
			'should return original state if no parent on destroy success',
			() => {
				const action = {
					id: 52,
					previous: {
						data: {
							assetEntrySetId: 52
						}
					},
					type: actionTypes.DESTROY_SUCCESS
				};

				const initialState = Map();

				const state = reducer(initialState, action);

				expect(state).toBe(initialState);
			}
		);

		it(
			'should update parent post childAssetEntrySets on fetch comments success',
			() => {
				const total = 3;

				const action = {
					data: {
						results: [2, 3, 6],
						total
					},
					parentId: 1,
					type: feedActionTypes.FETCH_COMMENTS_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[1],
						{
							loading: false
						}
					),
					action
				);

				const postIMap = state.getIn([1, 'data']);

				const assetSet = postIMap.get('childAssetEntrySets').toJS();

				expect(postIMap.get('childAssetEntrySetsCount')).toBe(total);

				expect(postIMap.get('childAssetEntrySets').size).toBe(postIMap.get('childAssetEntrySetsCount'));

				expect(assetSet[0]).toBe(2);
				expect(assetSet[2]).toBe(6);
			}
		);

		it(
			'should return total matching childAssetEntrySetsCount on fetch comments success',
			() => {
				const total = 4;

				const action = {
					data: {
						results: [2, 3, 4, 5],
						total
					},
					parentId: 1,
					type: feedActionTypes.FETCH_COMMENTS_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[1],
						{
							loading: false
						}
					),
					action
				);

				expect(state.getIn([1, 'data', 'childAssetEntrySetsCount'])).toBe(total);
			}
		);

		it(
			'should update post on bookmark post success',
			() => {
				const id = 1;

				const action = {
					bookmarked: true,
					id,
					type: actionTypes.BOOKMARK_POST_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[id],
						{
							data: {
								payload: {
									bookmarked: false
								}
							},
							loading: false
						}
					),
					action
				);

				expect(state.getIn([id, 'data', 'payload', 'bookmarked'])).toBe(true);
			}
		);

		it(
			'should update post on post update success',
			() => {
				const id = 1;

				const postData = {
					payload: {
						message: 'hello'
					}
				};

				let state = Map().mergeIn(
					[id],
					{
						data: postData,
						loading: false,
						messageInfo: Map(
							{
								message: 'hello'
							}
						)
					}
				);

				const action = {
					data: {
						payload: {
							message: 'world'
						}
					},
					id,
					type: actionTypes.UPDATE_SUCCESS
				};

				expect(state.getIn([id, 'data', 'payload', 'message'])).toBe('hello');

				state = reducer(state, action);

				expect(state.getIn([id, 'data', 'payload', 'message'])).toBe('world');
				expect(state.getIn([id, 'messageInfo', 'message'])).toBe('world');
			}
		);

		it(
			'should add messageInfo to post object on GET_MESSAGE_POSTS_SUCCESS',
			() => {
				const id = 1;

				const postData = {
					payload: {
						message: 'hello'
					}
				};

				let state = Map().mergeIn(
					[id],
					{
						data: postData,
						loading: false
					}
				);

				const action = {
					data: {
						message: 'message',
						truncated: true
					},
					id,
					type: feedActionTypes.GET_MESSAGE_POSTS_SUCCESS
				};

				state = reducer(state, action);

				expect(state.getIn([id, 'messageInfo', 'message'])).toBe('message');

				expect(state.getIn([id, 'messageInfo', 'truncated'])).toBe(true);
			}
		);

		it(
			'should update post on like post request',
			() => {
				const id = 5;

				const action = {
					id,
					type: actionTypes.LIKE_POST_REQUEST
				};

				const state = reducer(
					Map().mergeIn(
						[id],
						{
							data: {
								assetEntrySetLikesCount: 6,
								payload: {
									likedParticipants: {
										liked: false
									}
								}
							}
						}
					),
					action
				);

				const postState = state.get(id);

				expect(postState.getIn(['data', 'assetEntrySetLikesCount'])).toBe(7);
				expect(postState.getIn(['data', 'payload', 'likedParticipants', 'liked'])).toBe(true);
			}
		);

		it(
			'should revert post on like post failure',
			() => {
				const id = 5;

				const action = {
					id,
					type: actionTypes.LIKE_POST_FAILURE
				};

				const state = reducer(
					Map().mergeIn(
						[id],
						{
							data: {
								assetEntrySetLikesCount: 6,
								payload: {
									likedParticipants: {
										liked: true
									}
								}
							}
						}
					),
					action
				);

				const postState = state.get(id);

				expect(postState.getIn(['data', 'assetEntrySetLikesCount'])).toBe(5);
				expect(postState.getIn(['data', 'payload', 'likedParticipants', 'liked'])).toBe(false);
			}
		);

		it(
			'should remove the truncated value from the messageInfo data',
			() => {
				const postId = 1;

				let state = Map().mergeIn(
					[postId],
					{
						messageInfo: Map(
							{
								truncated: true
							}
						)
					}
				);

				const action = {
					postId,
					type: feedActionTypes.CLEAR_MESSAGE_INFO_TRUNCATED
				};

				state = reducer(state, action);

				expect(state.getIn([postId, 'messageInfo', 'truncated'])).toBe(undefined);
			}
		);

		it(
			'should fetch post on FETCH_SUCCESS',
			() => {
				const id = 5;

				const action = {
					data: {...LoopAssets.getPost(5)},
					id,
					type: actionTypes.FETCH_SUCCESS
				};

				const state = reducer(
					Map().mergeIn(
						[action.data.assetEntrySetId],
						{
							data: action.data,
							loading: false
						}
					),
					action
				);

				const postState = state.get(id);

				expect(postState.getIn(['data', 'assetEntrySetId'])).toBe(id);
				expect(postState.get('loading')).toBe(false);
			}
		);
	}
);