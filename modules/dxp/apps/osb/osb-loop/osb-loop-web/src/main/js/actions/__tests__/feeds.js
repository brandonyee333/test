jest.unmock('../feeds');
jest.unmock('../../lib/util');

import * as feedActions from '../feeds';
import {CALL_API} from '../../middleware/api';

const {actionTypes} = feedActions;

describe(
	'clearFeed',
	() => {
		const {clearFeed} = feedActions;

		it(
			'should return a clearFeed action',
			() => {
				const action = clearFeed();

				expect(typeof action).toBe('object');
				expect(action.type).toBe(actionTypes.CLEAR_FEED);
			}
		);
	}
);

describe(
	'clearMessageInfoTruncated',
	() => {
		const {clearMessageInfoTruncated} = feedActions;

		it(
			'should return a clearMessageInfoTruncated action',
			() => {
				const action = clearMessageInfoTruncated();

				expect(typeof action).toBe('object');
				expect(action.type).toBe(actionTypes.CLEAR_MESSAGE_INFO_TRUNCATED);
			}
		);
	}
);

describe(
	'fetchComments',
	() => {
		const {fetchComments} = feedActions;

		it(
			'should return a fetchComments action',
			() => {
				const action = fetchComments(
					{
						commentFeedId: 0,
						itemsPerPage: 1,
						parentId: 1,
						sessionTime: 1
					}
				);

				expect(typeof action).toBe('object');
				expect(action.parentId).toBe(1);

				const apiCall = action[CALL_API];

				expect(typeof apiCall).toBe('object');
				expect(apiCall.controllerMethod).toBe('viewOldComments.json');
				expect(apiCall.data.createTime).toBeTruthy();
			}
		);
	}
);

describe(
	'fetchPosts',
	() => {
		const baseArgs = {
			id: '50-51',
			itemsPerPage: 10,
			sessionTime: 1
		};

		const {fetchPosts} = feedActions;

		it(
			'should return a fetchPosts action',
			() => {
				const id = 20;

				const action = fetchPosts(
					{
						id,
						itemsPerPage: 10,
						sessionTime: 1
					}
				);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(id);

				const apiCall = action[CALL_API];

				expect(typeof apiCall).toBe('object');
				expect(apiCall.controllerMethod).toBe('viewMyFeed.json');
				expect(apiCall.data.time).toBeTruthy();
			}
		);

		it(
			'should call correct API method when a string id is passed',
			() => {
				const action = fetchPosts(baseArgs);

				expect(action[CALL_API].controllerMethod).toBe('viewOldFeed.json');
			}
		);

		it(
			'should call correct API method when posts for a view page are requested',
			() => {
				const action = fetchPosts(baseArgs);

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('viewOldFeed.json');
				expect(apiCall.data.createTime).toBeTruthy();
			}
		);

		it(
			'should call correct API method when new feed posts are requested',
			() => {
				const action = fetchPosts(
					{
						...baseArgs,
						newPosts: true
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('viewNewFeed.json');
				expect(apiCall.data.createTime).toBeTruthy();
			}
		);

		it(
			'should set sticky time if fetching the following stream',
			() => {
				const action = fetchPosts(
					{
						...baseArgs,
						id: LoopConstants.loopStreamAliasIds.following
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.stickyTime).toBe(baseArgs.sessionTime);
			}
		);

		it(
			'should set stickyOnly if fetching sticky announcements',
			() => {
				const action = fetchPosts(
					{
						...baseArgs,
						id: LoopConstants.loopStreamAliasIds.announcementsSticky
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.stickyOnly).toBe(true);
			}
		);
	}
);

describe(
	'filterRemovedPosts',
	() => {
		const {filterRemovedPosts} = feedActions;

		it(
			'should return a filterRemovedPosts action',
			() => {
				const action = filterRemovedPosts(1);

				expect(typeof action).toBe('object');
				expect(action.type).toBe(actionTypes.FILTER_REMOVED_POSTS);
				expect(action.id).toBe(1);
			}
		);
	}
);

describe(
	'getMessage',
	() => {
		const {getMessage} = feedActions;

		it(
			'should return a getMessage action',
			() => {
				const action = getMessage(1, 2);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(2);
				expect(action[CALL_API].data.id).toBe(2);
				expect(action.feedId).toBe(1);
			}
		);
	}
);

describe(
	'removePost',
	() => {
		const {removePost} = feedActions;

		it(
			'should return a removePost action',
			() => {
				const action = removePost(1, 2);

				expect(typeof action).toBe('object');
				expect(action.type).toBe(actionTypes.REMOVE_POST);
				expect(action.id).toBe(1);
				expect(action.postId).toBe(2);
			}
		);
	}
);