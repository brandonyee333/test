jest.unmock('../../lib/util');
jest.unmock('../following');

import {follow} from '../following';
import {CALL_API} from '../../middleware/api';

const {classNameIds} = LoopConstants;

describe(
	'follow',
	() => {
		it(
			'should return an API action to follow/unfollow a post',
			() => {
				let action = follow(classNameIds.posts, 1);

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('addToStream.json');
				expect(apiCall.data.classNameId).toBe(classNameIds.posts);

				action = follow('posts', 1, false);

				expect(action[CALL_API].controllerMethod).toBe('removeFromStream.json');
			}
		);
	}
);