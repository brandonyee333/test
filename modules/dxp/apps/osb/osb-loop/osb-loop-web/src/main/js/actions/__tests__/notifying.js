jest.unmock('../../lib/util');
jest.unmock('../notifying');

import {notify, notifyEmail} from '../notifying';
import {CALL_API} from '../../middleware/api';

const {classNameIds} = LoopConstants;

describe(
	'notifying',
	() => {
		describe(
			'notify',
			() => {
				it(
					'should return an API action to toggle notifications for an entity',
					() => {
						let action = notify(classNameIds.posts, 1);

						const apiCall = action[CALL_API];

						expect(apiCall.controllerMethod).toBe('enableNotifications.json');
						expect(apiCall.data.classNameId).toBe(classNameIds.posts);

						action = notify(classNameIds.posts, 1, false);

						expect(action[CALL_API].controllerMethod).toBe('disableNotifications.json');
					}
				);
			}
		);

		describe(
			'notifyEmail',
			() => {
				it(
					'should return an API action to toggle notifications for an entity',
					() => {
						let action = notifyEmail(classNameIds.posts, 1);

						const apiCall = action[CALL_API];

						expect(apiCall.controllerMethod).toBe('enableNotifications.json');
						expect(apiCall.data.classNameId).toBe(LoopConstants.classNameIds.posts);

						action = notifyEmail(classNameIds.posts, 1, false);

						expect(action[CALL_API].controllerMethod).toBe('disableNotifications.json');
					}
				);
			}
		);
	}
);