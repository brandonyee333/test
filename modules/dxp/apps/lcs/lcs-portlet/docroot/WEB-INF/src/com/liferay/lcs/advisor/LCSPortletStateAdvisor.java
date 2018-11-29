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
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import javax.servlet.http.HttpServletResponse;

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
		catch (JSONWebServiceInvocationException jsonwsie) {
			StringBundler sb = new StringBundler(6);

			sb.append("Unable to invoke an LCS service.");

			if (jsonwsie.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
				sb.append(" The user credentials in the environment token ");
				sb.append("were rejected. Please regenerate, download, and ");
				sb.append("install a new token.");
			}

			if (jsonwsie.getMessage() != null) {
				sb.append(" Caused by: ");
				sb.append(jsonwsie.getMessage());
			}

			_log.error(sb.toString());
		}
		catch (JSONWebServiceSerializeException jsonwsse) {
			StringBundler sb = new StringBundler(4);

			sb.append("Error communicating with LCS. A message in an ");
			sb.append("unexpected format caused a serialization error.");

			if (jsonwsse.getMessage() != null) {
				sb.append(" Error details: ");
				sb.append(jsonwsse.getMessage());
			}

			_log.error(sb.toString());
		}
		catch (JSONWebServiceTransportException jsonwste) {
			StringBundler sb = new StringBundler(6);

			sb.append("Unable to communicate with LCS.");

			String message = jsonwste.getMessage();

			if (message != null) {
				if (message.contains("Not authorized")) {
					sb.append(" The user credentials in the environment ");
					sb.append("token were rejected. Please regenerate, ");
					sb.append("download, and install a new token.");
				}

				sb.append(" Error details: ");
				sb.append(message);
			}

			if ((message == null) || !message.contains("Not authorized")) {
				_lastLCSPortletState = LCSPortletState.NO_CONNECTION;
			}

			_log.error(sb.toString());
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