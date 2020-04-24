jest.unmock('../crud');
jest.unmock('../pages');

import LoopConstants from '../../lib/loop-constants';
import {CALL_API} from '../../middleware/api';

import {
	actionTypes,
	addPage,
	clearPages,
	fetchPages
} from '../pages';

const {classNameIds} = LoopConstants;

describe(
	'fetchPages',
	() => {
		it(
			'should return type for adding page',
			() => {
				const message = 'test test';
				const ownerClassNameId = classNameIds.divisions;
				const ownerId = 1;
				const title = 'test';

				const action = addPage(
					{
						ownerClassNameId,
						ownerId,
						payload: {
							message,
							type: 'text'
						},
						title
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('add.json');
				expect(apiCall.data.classNameId).toBe(ownerClassNameId);
				expect(apiCall.data.classPK).toBe(ownerId);
				expect(apiCall.data.payload.message).toBe(message);
				expect(apiCall.data.title).toBe(title);
			}
		);

		it(
			'should return type for clearing pages',
			() => {
				const action = clearPages();

				expect(typeof action).toBe('object');
				expect(action.type).toBe(actionTypes.CLEAR_PAGES);
			}
		);

		it(
			'should return an api call to fetch pages',
			() => {
				const ownerClassNameId = LoopConstants.classNameIds.divisions;
				const ownerId = 1;
				const reverseSort = true;
				const sortFieldName = 'title';

				const action = fetchPages(
					{
						end: 10,
						ownerClassNameId,
						ownerId,
						reverseSort,
						sortFieldName,
						start: 0
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.reverseSort).toBe(reverseSort);
				expect(apiCall.data.classNameId).toBe(ownerClassNameId);
				expect(apiCall.data.classPK).toBe(ownerId);
				expect(apiCall.data.sortFieldName).toBe(sortFieldName);
				expect(apiCall.controllerMethod).toBe('index.json');
			}
		);
	}
);