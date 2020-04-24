jest.unmock('../crud');
jest.unmock('../posts');

import * as postActions from '../posts';
import {CALL_API} from '../../middleware/api';

describe(
	'bookmarkPost',
	() => {
		const {bookmarkPost} = postActions;

		it(
			'should return a bookmarkPost action',
			() => {
				const id = 1;

				const action = bookmarkPost(id);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(id);
				expect(action.bookmarked).toBe(true);
				expect(action[CALL_API].data.loopStreamId).toBe(LoopConstants.loopStreamAliasIds.bookmarks);
			}
		);

		it(
			'should have a removedBy property',
			() => {
				const id = 1;

				let action = bookmarkPost(id, true, LoopConstants.loopStreamAliasIds.bookmarks);

				expect(action.removedBy).toBeTruthy();

				action = bookmarkPost(id, false, LoopConstants.loopStreamAliasIds.bookmarks);

				expect(action.removedBy).toBeTruthy();
			}
		);
	}
);

describe(
	'createPost',
	() => {
		const {createPost} = postActions;

		it(
			'should return a like post action',
			() => {
				const id = 5;

				const action = createPost(
					{
						name: 'test',
						streamId: id
					}
				);

				expect(typeof action).toBe('object');
				expect(action.streamId).toBe(id);
				expect(action[CALL_API].data.name).toBe('test');
			}
		);
	}
);

describe(
	'followPost',
	() => {
		const {followPost} = postActions;

		it(
			'should have a removedBy property',
			() => {
				const id = 1;

				let action = followPost(id, true, LoopConstants.loopStreamAliasIds.following);

				expect(action.removedBy).toBeTruthy();

				action = followPost(id, false, LoopConstants.loopStreamAliasIds.following);

				expect(action.removedBy).toBeTruthy();
			}
		);

		it(
			'should not have a removedBy property if streamId is not following',
			() => {
				const id = 1;

				const action = followPost(id, true, LoopConstants.loopStreamAliasIds.announcements);

				expect(action.removedBy).toBe(null);
			}
		);
	}
);

describe(
	'likePost',
	() => {
		const {likePost} = postActions;

		it(
			'should return a likePost action',
			() => {
				const id = 5;

				const action = likePost(id);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(id);

				const apiCall = action[CALL_API];

				expect(typeof apiCall).toBe('object');
				expect(apiCall.controllerMethod).toBe('like.json');
			}
		);

		it(
			'should should call unlike.json',
			() => {
				const id = 5;

				const action = likePost(id, false);

				expect(action[CALL_API].controllerMethod).toBe('unlike.json');
			}
		);
	}
);

describe(
	'updatePost',
	() => {
		const {updatePost} = postActions;

		it(
			'should return an action witha  streamId',
			() => {
				const streamId = 3;

				const action = updatePost({streamId});

				expect(action.streamId).toBe(streamId);
			}
		);
	}
);