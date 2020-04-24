import LoopConstants from '../lib/loop-constants';
import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const {notificationTypes} = LoopConstants;

export const actionTypes = {
	...createActionTypes('notify', 'entity'),
	...createActionTypes('notify_email', 'entity')
};

export function notify(classNameId, id, notifying = true) {
	const method = notifying ? 'enableNotifications' : 'disableNotifications';

	return {
		[CALL_API]: {
			controller: 'feed',
			controllerMethod: `${method}.json`,
			data: {
				classNameId,
				classPK: id,
				type: notificationTypes.inApp
			},
			types: [actionTypes.NOTIFY_ENTITY_REQUEST, actionTypes.NOTIFY_ENTITY_SUCCESS, actionTypes.NOTIFY_ENTITY_FAILURE]
		},
		entityClassNameId: classNameId,
		id,
		notifying
	};
}

export function notifyEmail(classNameId, id, notifyingEmail = true) {
	const method = notifyingEmail ? 'enableNotifications' : 'disableNotifications';

	return {
		[CALL_API]: {
			controller: 'feed',
			controllerMethod: `${method}.json`,
			data: {
				classNameId,
				classPK: id,
				type: notificationTypes.email
			},
			types: [actionTypes.NOTIFY_EMAIL_ENTITY_REQUEST, actionTypes.NOTIFY_EMAIL_ENTITY_SUCCESS, actionTypes.NOTIFY_EMAIL_ENTITY_FAILURE]
		},
		entityClassNameId: classNameId,
		id,
		notifyingEmail
	};
}