/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.lcs.license.messaging;

import com.liferay.lcs.advisor.LCSPortletStateAdvisor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.license.messaging.LicenseManagerMessageType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Igor Beslic
 */
public class LicenseManagerValidateSubscriptionMessageListener
	extends LicenseManagerBaseMessageListener {

	public LicenseManagerValidateSubscriptionMessageListener() {
		setAllowedLicenseManagerMessageType(
			LicenseManagerMessageType.VALIDATE_SUBSCRIPTION);
	}

	public void setLcsPortletStateAdvisor(
		LCSPortletStateAdvisor lcsPortletStateAdvisor) {

		_lcsPortletStateAdvisor = lcsPortletStateAdvisor;
	}

	@Override
	protected Message createResponseMessage(JSONObject jsonObject) {
		LicenseManagerMessageType licenseManagerMessageType =
			LicenseManagerMessageType.SUBSCRIPTION_VALID;

		if (_log.isTraceEnabled()) {
			_log.trace("Checking LCS portlet state");
		}

		LCSPortletState lcsPortletState =
			_lcsPortletStateAdvisor.getLCSPortletState(true);

		if (_log.isTraceEnabled()) {
			_log.trace("LCS portlet state: " + lcsPortletState);
		}

		return licenseManagerMessageType.createMessage(lcsPortletState);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseManagerValidateSubscriptionMessageListener.class);

	private LCSPortletStateAdvisor _lcsPortletStateAdvisor;

}