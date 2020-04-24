import {CALL_API} from '../middleware/api';
import {createActionTypes, getController} from '../lib/util';

export const actionTypes = {
	...createActionTypes('set', 'cover_image'),
	...createActionTypes('set', 'profile_image')
};

export function setCoverImage(classNameId, id, coverImagePayload) {
	return {
		id,
		[CALL_API]: {
			controller: getController(classNameId),
			controllerMethod: 'setCoverImage.json',
			data: {
				coverImagePayload: JSON.stringify(coverImagePayload),
				id
			},
			types: [actionTypes.SET_COVER_IMAGE_REQUEST, actionTypes.SET_COVER_IMAGE_SUCCESS, actionTypes.SET_COVER_IMAGE_FAILURE]
		}
	};
}

export function setProfileImage(classNameId, id, profileImagePayload) {
	return {
		id,
		[CALL_API]: {
			controller: getController(classNameId),
			controllerMethod: 'setProfileImage.json',
			data: {
				id,
				profileImagePayload: JSON.stringify(profileImagePayload)
			},
			types: [actionTypes.SET_PROFILE_IMAGE_REQUEST, actionTypes.SET_PROFILE_IMAGE_SUCCESS, actionTypes.SET_PROFILE_IMAGE_FAILURE]
		}
	};
}