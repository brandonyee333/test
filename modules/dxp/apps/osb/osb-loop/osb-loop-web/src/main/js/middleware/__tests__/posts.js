jest.unmock('../../actions/crud');
jest.unmock('../posts');

import {actionTypes as feedActionTypes} from '../../actions/feeds';
import {actionTypes as postActionTypes} from '../../actions/posts';
import middleware from '../posts';

describe(
	'posts middleware',
	() => {
		it(
			'should parse payloads of a single post',
			() => {
				const name = 'test';

				const payload = JSON.stringify({name});

				const action = {
					data: {
						childAssetEntrySets: [
							{
								payload
							}
						],
						payload
					},
					type: postActionTypes.FETCH_SUCCESS
				};

				const next = jest.fn();

				middleware()(next)(action);

				const newAction = next.mock.calls[0][0];

				expect(newAction.data.payload.name).toBe(name);
				expect(newAction.data.childAssetEntrySets[0].payload.name).toBe(name);
			}
		);

		it(
			'should parse the payloads of an array of posts',
			() => {
				const name = 'test';
				const payload = JSON.stringify({name});

				const action = {
					data: [1, 2, 3].map(
						() => (
							{
								childAssetEntrySets: [1, 2, 3].map(
									() => (
										{
											payload
										}
									)
								),
								payload
							}
						)
					),
					type: feedActionTypes.FETCH_POSTS_SUCCESS
				};

				const next = jest.fn();

				middleware()(next)(action);

				const newAction = next.mock.calls[0][0];

				newAction.data.forEach(
					post => {
						expect(post.payload.name).toBe(name);

						post.childAssetEntrySets.forEach(comment => expect(comment.payload.name).toBe(name));
					}
				);
			}
		);
	}
);