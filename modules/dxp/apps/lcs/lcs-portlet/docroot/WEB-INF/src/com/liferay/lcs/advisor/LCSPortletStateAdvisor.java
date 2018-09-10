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

	public LCSPortletState getLCSPortletState(boolean checkSubscription) {
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
			if (jsonwsie.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
				if (_log.isWarnEnabled()) {
					StringBundler sb = new StringBundler(9);

					sb.append("User permissions may be invalid. If user who ");
					sb.append("generated LCS activation token file was ");
					sb.append("removed from LCS project, or user permissions ");
					sb.append("were degraded, LCS portlet will not be able ");
					sb.append("to communicate to the LCS platform after the ");
					sb.append("server is rebooted or after the LCS portlet ");
					sb.append("is redeployed. The LCS platform returned the ");
					sb.append("following error message: ");
					sb.append(jsonwsie.getMessage());

					_log.warn(sb.toString());
				}
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug("Remote service unavailable", jsonwsie);
				}
				else {
					_log.error("Remote service unavailable");
				}
			}
		}
		catch (JSONWebServiceSerializeException jsonwsse) {
			if (_log.isDebugEnabled()) {
				_log.debug("Remote service unavailable", jsonwsse);
			}
			else {
				_log.error("Remote service unavailable");
			}
		}
		catch (JSONWebServiceTransportException jsonwste) {
			String message = jsonwste.getMessage();

			if ((message != null) && message.contains("Not authorized")) {
				if (_log.isWarnEnabled()) {
					StringBundler sb = new StringBundler(8);

					sb.append("OAuth credentials may be invalid. If ");
					sb.append("credentials were revoked, the LCS portlet ");
					sb.append("will not be able to communicate to the LCS ");
					sb.append("platform after the server is rebooted or ");
					sb.append("after the LCS portlet is redeployed. The LCS ");
					sb.append("platform returned the following error ");
					sb.append("message: ");
					sb.append(message);

					_log.warn(sb.toString());
				}
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug("Remote service unavailable", jsonwste);
				}
				else {
					_log.error("Remote service unavailable");
				}

				_lastLCSPortletState = LCSPortletState.NO_CONNECTION;
			}
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

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortletStateAdvisor.class);

	private LCSPortletState _lastLCSPortletState =
		LCSPortletState.NO_SUBSCRIPTION;
	private LCSKeyAdvisor _lcsKeyAdvisor;
	private LCSSubscriptionEntryClient _lcsSubscriptionEntryClient;

}