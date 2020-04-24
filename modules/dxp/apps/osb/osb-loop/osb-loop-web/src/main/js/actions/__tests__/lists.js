jest.unmock('../lists');
jest.unmock('../../lib/util');

import {actionTypes, clearLists, fetchIndexList} from '../lists';
import {CALL_API} from '../../middleware/api';
import {getController} from '../../lib/util';

describe(
	'lists',
	() => {
		const classNameId = LoopConstants.classNameIds.people;
		const end = 10;
		const listName = 123;
		const start = 0;

		it(
			'should call fetchIndex action',
			() => {
				const action = fetchIndexList(
					{
						classNameId,
						end,
						listName,
						start
					}
				);

				expect(typeof action).toBe('object');

				expect(action.entityClassNameId).toBe(classNameId);
				expect(action.end).toBe(end);
				expect(action.listName).toBe(listName);
				expect(action.start).toBe(start);

				const apiCall = action[CALL_API];

				expect(apiCall.controller).toBe(getController(classNameId));
				expect(apiCall.controllerMethod).toBe('index.json');
			}
		);

		it(
			'should return type for clearing lists',
			() => {
				const action = clearLists();

				expect(typeof action).toBe('object');
				expect(action.type).toBe(actionTypes.CLEAR_LISTS);
			}
		);
	}
);