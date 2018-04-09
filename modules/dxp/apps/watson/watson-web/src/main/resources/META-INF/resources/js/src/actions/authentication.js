import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const controller = 'incidents';

export const actionTypes = {
	...createActionTypes('CHECK', 'user_authentication_email'),
	...createActionTypes('submit', 'authentication_token')
};

export function checkUserAuthorizationStatus(data) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'checkUserAuthorizationStatus.json',
			data,
			types: [actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_REQUEST, actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_SUCCESS, actionTypes.CHECK_USER_AUTHENTICATION_EMAIL_FAILURE]
		}
	};
}

export function submitAuthenticationToken(data) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'submitAuthenticationToken.json',
			data,
			types: [actionTypes.SUBMIT_AUTHENTICATION_TOKEN_REQUEST, actionTypes.SUBMIT_AUTHENTICATION_TOKEN_SUCCESS, actionTypes.SUBMIT_AUTHENTICATION_TOKEN_FAILURE]
		}
	};
}