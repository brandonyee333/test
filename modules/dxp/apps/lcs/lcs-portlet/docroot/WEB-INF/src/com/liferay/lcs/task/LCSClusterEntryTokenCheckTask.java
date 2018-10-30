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

package com.liferay.lcs.task;

import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.exception.InvalidLCSClusterEntryTokenException;
import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.internal.event.LCSEventListener;
import com.liferay.lcs.oauth.OAuthUtil;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Beslic
 */
public class LCSClusterEntryTokenCheckTask implements Task {

	public LCSClusterEntryTokenCheckTask(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		TaskSchedulerService taskSchedulerService) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;

		_lcsEventListeners = new ArrayList<>();

		_lcsEventListeners.add(lcsClusterEntryTokenAdvisor);
		_lcsEventListeners.add(taskSchedulerService);

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		LCSUtil.processLCSPortletState(LCSPortletState.NOT_REGISTERED);

		try {
			_checkLCSClusterEntryToken();

			_notifyLCSEventListeners(
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS);
		}
		catch (Throwable throwable) {
			if (_log.isDebugEnabled()) {
				_log.debug(throwable.getMessage(), throwable);
			}
			else {
				if (_log.isWarnEnabled() &&
					Validator.isNotNull(throwable.getMessage())) {

					_log.warn(throwable.getMessage());
				}
			}

			if (OAuthUtil.hasOAuthTokenRejectedException(throwable)) {
				LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);

				if (_log.isWarnEnabled()) {
					_log.warn("A new OAuth token is required");
				}
			}

			_notifyLCSEventListeners(
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private void _checkLCSClusterEntryToken() throws Exception {
		_lcsClusterEntryTokenAdvisor.processLCSClusterEntryToken(
			LCSUtil.getLCSPortletBuildNumber());

		if (!LCSUtil.isLCSPortletAuthorized(
				_lcsClusterEntryTokenAdvisor.getLCSAccessSecret(),
				_lcsClusterEntryTokenAdvisor.getLCSAccessToken())) {

			StringBundler sb = new StringBundler(5);

			sb.append("The LCS activation token file contains revoked or ");
			sb.append("invalid OAuth credentials and will be deleted. ");
			sb.append("Regenerate the token file using the LCS platform ");
			sb.append("dashboard, and then download the token file and ");
			sb.append("deploy it.");

			throw new InvalidLCSClusterEntryTokenException(sb.toString());
		}

		LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);
	}

	private void _notifyLCSEventListeners(LCSEvent lcsEvent) {
		for (LCSEventListener lcsEventListener : _lcsEventListeners) {
			try {
				lcsEventListener.onLCSEvent(lcsEvent);
			}
			catch (Throwable t) {
				_log.error("Unable to notify listener", t);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSClusterEntryTokenCheckTask.class);

	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final List<LCSEventListener> _lcsEventListeners;

}