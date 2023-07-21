/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.license.messaging;

import com.liferay.lcs.client.internal.constants.LCSDestinationNames;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.license.messaging.LicenseManagerMessageType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = "destination.name=" + LCSDestinationNames.LCS_REQUEST,
	service = MessageListener.class
)
public class LicenseManagerValidateSubscriptionMessageListener
	extends LicenseManagerBaseMessageListener {

	public LicenseManagerValidateSubscriptionMessageListener() {
		setAllowedLicenseManagerMessageType(
			LicenseManagerMessageType.VALIDATE_SUBSCRIPTION);
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Override
	protected Message createResponseMessage(JSONObject jsonObject) {
		LicenseManagerMessageType licenseManagerMessageType =
			LicenseManagerMessageType.SUBSCRIPTION_VALID;

		if (_log.isTraceEnabled()) {
			_log.trace(
				"Responding to license manager with " +
					LCSPortletState.UNDEFINED);
		}

		return licenseManagerMessageType.createMessage(
			LCSPortletState.UNDEFINED);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseManagerValidateSubscriptionMessageListener.class);

}