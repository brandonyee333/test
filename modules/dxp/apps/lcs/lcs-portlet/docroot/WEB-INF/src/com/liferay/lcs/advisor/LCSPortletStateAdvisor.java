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

package com.liferay.lcs.advisor;

import com.liferay.lcs.rest.client.LCSSubscriptionEntry;
import com.liferay.lcs.rest.client.LCSSubscriptionEntryClient;
import com.liferay.lcs.rest.client.exception.LCSClientRemoteAuthorizationException;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class LCSPortletStateAdvisor {

	public long getLastLicenseCheckTime() {
		return _lastLicenseCheckTime;
	}

	public LCSPortletState getLCSPortletState(boolean checkSubscription) {
		if (_log.isTraceEnabled()) {
			_log.trace("Checking LCS portlet state");
		}

		if (!checkSubscription) {
			return _lastLCSPortletState;
		}

		try {
			LCSSubscriptionEntry lcsSubscriptionEntry =
				_lcsSubscriptionEntryClient.fetchLCSSubscriptionEntry(
					_lcsKeyAdvisor.getKey());

			if (lcsSubscriptionEntry == null) {
				_lastLCSPortletState = LCSPortletState.NO_SUBSCRIPTION;
			}
			else {
				_lastLCSPortletState = LCSPortletState.GOOD;
			}

			return _lastLCSPortletState;
		}
		catch (Exception e) {
			if (e instanceof LCSClientRemoteAuthorizationException) {
				_lastLCSPortletState = LCSPortletState.NO_CONNECTION;
			}

			_log.error("Remote service unavailable", e);
		}

		return _lastLCSPortletState;
	}

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	public void setLCSSubscriptionEntryClient(
		LCSSubscriptionEntryClient lcsSubscriptionEntryClient) {

		_lcsSubscriptionEntryClient = lcsSubscriptionEntryClient;
	}

	public long updateLicenseCheckTime() {
		_lastLicenseCheckTime = System.currentTimeMillis();

		return _lastLicenseCheckTime;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortletStateAdvisor.class);

	private LCSPortletState _lastLCSPortletState =
		LCSPortletState.NO_SUBSCRIPTION;
	private long _lastLicenseCheckTime;
	private LCSKeyAdvisor _lcsKeyAdvisor;
	private LCSSubscriptionEntryClient _lcsSubscriptionEntryClient;

}