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

package com.liferay.lcs.runnable;

import com.liferay.lcs.advisor.LCSAlertAdvisor;
import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.exception.InvalidLCSClusterEntryTokenException;
import com.liferay.lcs.exception.LCSHandshakeException;
import com.liferay.lcs.oauth.OAuthUtil;
import com.liferay.lcs.rest.client.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.ResourceBundle;
import java.util.concurrent.Future;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
public class LCSConnectorRunnable implements Runnable {

	public LCSConnectorRunnable(boolean delayRun) {
		_delayRun = delayRun;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		_delayRun();

		LCSUtil.processLCSPortletState(LCSPortletState.NOT_REGISTERED);

		_lcsAlertAdvisor.clear();

		while (!_stop) {
			try {
				Future<?> future = _activateLCS();

				future.get();

				break;
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e.getMessage(), e);
				}
				else {
					if (_log.isWarnEnabled() &&
						Validator.isNotNull(e.getMessage())) {

						_log.warn(e.getMessage());
					}
				}

				if (e instanceof LCSHandshakeException) {
					_lcsClusterEntryTokenAdvisor.checkLCSClusterEntryTokenError(
						(LCSHandshakeException)e);
				}
				else if (e instanceof NoSuchLCSSubscriptionEntryException) {
					if (_log.isWarnEnabled()) {
						ResourceBundle resourceBundle =
							ResourceBundleUtil.getBundle(
								"content.Language", getClass());

						_log.warn(
							ResourceBundleUtil.getString(
								resourceBundle,
								"exceeded-subscription-number"));
					}
				}
				else if (OAuthUtil.hasOAuthTokenRejectedException(e)) {
					LCSUtil.processLCSPortletState(
						LCSPortletState.NO_CONNECTION);

					if (_log.isWarnEnabled()) {
						_log.warn("A new OAuth token is required");
					}
				}

				if (_log.isInfoEnabled()) {
					_log.info("LCS portlet is not connected");
				}

				try {
					if (_log.isInfoEnabled()) {
						_log.info("Retry in 60 seconds");
					}

					Thread.sleep(60000);
				}
				catch (InterruptedException ie) {
					if (_log.isWarnEnabled()) {
						_log.warn("Interrupted while waiting for connection");
					}
				}
			}
			catch (Throwable throwable) {
				_log.error(
					"Current thread will be terminated because unable to " +
						"recover from error. Please report error to support.",
					throwable);

				stop();
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Thread terminated");
		}
	}

	public void setLCSAlertAdvisor(LCSAlertAdvisor lcsAlertAdvisor) {
		_lcsAlertAdvisor = lcsAlertAdvisor;
	}

	public void setLCSClusterEntryTokenAdvisor(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
	}

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager) {

		_lcsConnectionManager = lcsConnectionManager;
	}

	public void stop() {
		_stop = true;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private Future<?> _activateLCS() throws Exception {
		_lcsClusterEntryTokenAdvisor.processLCSClusterEntryToken(
			LCSUtil.getLCSPortletBuildNumber());

		LCSUtil.setUpJSONWebServiceClientCredentials(
			_lcsClusterEntryTokenAdvisor.getLCSAccessSecret(),
			_lcsClusterEntryTokenAdvisor.getLCSAccessToken());

		if (!LCSUtil.isLCSPortletAuthorized()) {
			_lcsClusterEntryTokenAdvisor.deleteLCSCLusterEntryTokenFile();

			StringBundler sb = new StringBundler(5);

			sb.append("The LCS activation token file contains revoked or ");
			sb.append("invalid OAuth credentials and will be deleted. ");
			sb.append("Regenerate the token file using the LCS platform ");
			sb.append("dashboard, and then download the token file and ");
			sb.append("deploy it.");

			throw new InvalidLCSClusterEntryTokenException(sb.toString());
		}

		LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);

		return _lcsConnectionManager.start();
	}

	private void _delayRun() {
		if (!_delayRun) {
			return;
		}

		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Thread run delayed for 60 seconds");
			}

			Thread.sleep(60000);
		}
		catch (InterruptedException ie) {
			if (_log.isWarnEnabled()) {
				_log.warn("Interrupted while waiting for delay");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSConnectorRunnable.class);

	private final boolean _delayRun;
	private LCSAlertAdvisor _lcsAlertAdvisor;
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private LCSConnectionManager _lcsConnectionManager;
	private boolean _stop;

}