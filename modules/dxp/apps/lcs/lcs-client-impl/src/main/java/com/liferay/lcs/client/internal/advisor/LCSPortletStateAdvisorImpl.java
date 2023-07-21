/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.advisor;

import com.liferay.lcs.client.advisor.LCSPortletStateAdvisor;
import com.liferay.lcs.client.platform.exception.LCSClientAuthenticationException;
import com.liferay.lcs.client.platform.portal.LCSSubscriptionEntry;
import com.liferay.lcs.client.platform.portal.LCSSubscriptionEntryClient;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSPortletStateAdvisor.class)
public class LCSPortletStateAdvisorImpl implements LCSPortletStateAdvisor {

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
			if (e instanceof LCSClientAuthenticationException) {
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
		LCSPortletStateAdvisorImpl.class);

	private LCSPortletState _lastLCSPortletState =
		LCSPortletState.NO_SUBSCRIPTION;
	private long _lastLicenseCheckTime;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private LCSSubscriptionEntryClient _lcsSubscriptionEntryClient;

}