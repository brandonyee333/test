jest.unmock('../images');

import {CALL_API} from '../../middleware/api';
import {getController} from '../../lib/util';
import {setCoverImage, setProfileImage} from '../images';

const {classNameIds} = LoopConstants;

describe(
	'images',
	() => {
		const id = '123';

		const payload = {
			foo: 'bar'
		};

		const controller = getController(classNameIds.people);

		it(
			'should call setCoverImage action',
			() => {
				const action = setCoverImage(classNameIds.people, id, payload);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(id);

				const apiCall = action[CALL_API];

				expect(typeof apiCall).toBe('object');
				expect(apiCall.controller).toBe(controller);
				expect(apiCall.controllerMethod).toBe('setCoverImage.json');
				expect(apiCall.data.coverImagePayload).toBe(JSON.stringify(payload));
			}
		);

		it(
			'should call setProfileImage action',
			() => {
				const action = setProfileImage(classNameIds.people, id, payload);

				expect(typeof action).toBe('object');
				expect(action.id).toBe(id);

				const apiCall = action[CALL_API];

				expect(typeof apiCall).toBe('object');
				expect(apiCall.controller).toBe(controller);
				expect(apiCall.controllerMethod).toBe('setProfileImage.json');
				expect(apiCall.data.profileImagePayload).toBe(JSON.stringify(payload));
			}
		);
	}
);