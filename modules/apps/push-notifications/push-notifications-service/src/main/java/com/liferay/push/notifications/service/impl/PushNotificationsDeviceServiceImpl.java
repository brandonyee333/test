/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.push.notifications.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.push.notifications.constants.PushNotificationsActionKeys;
import com.liferay.push.notifications.model.PushNotificationsDevice;
import com.liferay.push.notifications.service.base.PushNotificationsDeviceServiceBaseImpl;
import com.liferay.push.notifications.service.permission.PushNotificationsPermission;

import java.util.List;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class PushNotificationsDeviceServiceImpl
	extends PushNotificationsDeviceServiceBaseImpl {

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public PushNotificationsDevice addPushNotificationsDevice(
			String token, String platform)
		throws PortalException {

		PushNotificationsPermission.check(
			getPermissionChecker(), PushNotificationsActionKeys.MANAGE_DEVICES);

		PushNotificationsDevice pushNotificationsDevice =
			pushNotificationsDevicePersistence.fetchByToken(token);

		if (pushNotificationsDevice == null) {
			pushNotificationsDevice =
				pushNotificationsDeviceLocalService.addPushNotificationsDevice(
					getGuestOrUserId(), platform, token);
		}
		else if (!platform.equals("sms")) {
			long userId = getGuestOrUserId();

			pushNotificationsDevice.setUserId(userId);

			pushNotificationsDeviceLocalService.updatePushNotificationsDevice(
				pushNotificationsDevice);
		}

		return pushNotificationsDevice;
	}

	@Override
	public PushNotificationsDevice deletePushNotificationsDevice(
			long pushNotificationsDeviceId)
		throws PortalException {

		PushNotificationsPermission.check(
			getPermissionChecker(), PushNotificationsActionKeys.MANAGE_DEVICES);

		return pushNotificationsDeviceLocalService.
			deletePushNotificationsDevice(pushNotificationsDeviceId);
	}

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public PushNotificationsDevice deletePushNotificationsDevice(String token)
		throws PortalException {

		PushNotificationsPermission.check(
			getPermissionChecker(), PushNotificationsActionKeys.MANAGE_DEVICES);

		PushNotificationsDevice pushNotificationsDevice =
			pushNotificationsDevicePersistence.fetchByToken(token);

		if (pushNotificationsDevice == null) {
			if (_log.isInfoEnabled()) {
				_log.info("No device found with token " + token);
			}
		}
		else {
			long userId = getGuestOrUserId();

			if (pushNotificationsDevice.getUserId() == userId) {
				pushNotificationsDevice =
					pushNotificationsDeviceLocalService.
						deletePushNotificationsDevice(token);
			}
			else if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"Device found with token ", token,
						" does not belong to user ", String.valueOf(userId)));
			}
		}

		return pushNotificationsDevice;
	}

	@Override
	public void sendPushNotification(long[] toUserIds, String payload)
		throws PortalException {

		PushNotificationsPermission.check(
			getPermissionChecker(),
			PushNotificationsActionKeys.SEND_PUSH_NOTIFICATION);

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		pushNotificationsDeviceLocalService.sendPushNotification(
			toUserIds, payloadJSONObject);
	}

	@Override
	public void sendPushNotification(
			String platform, List<String> tokens, String payload)
		throws PortalException {

		PushNotificationsPermission.check(
			getPermissionChecker(),
			PushNotificationsActionKeys.SEND_PUSH_NOTIFICATION);

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		pushNotificationsDeviceLocalService.sendPushNotification(
			platform, tokens, payloadJSONObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PushNotificationsDeviceServiceImpl.class);

}