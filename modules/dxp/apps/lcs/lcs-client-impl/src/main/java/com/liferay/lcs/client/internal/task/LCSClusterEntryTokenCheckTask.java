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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.exception.LCSClusterEntryTokenDecryptException;
import com.liferay.lcs.client.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.client.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.exception.InvalidLCSClusterEntryTokenException;
import com.liferay.lcs.client.internal.platform.portal.LCSPortalClient;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	name = "com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask",
	service = Task.class
)
public class LCSClusterEntryTokenCheckTask implements Task {

	public LCSClusterEntryTokenCheckTask() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public LCSClusterEntryTokenCheckTask(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSEventManager lcsEventManager, LCSPortalClient lcsPortalClient) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsEventManager = lcsEventManager;
		_lcsPortalClient = lcsPortalClient;
	}

	@Override
	public void run() {
		LCSUtil.processLCSPortletState(LCSPortletState.NOT_REGISTERED);

		try {
			_checkLCSClusterEntryToken();

			_lcsEventManager.publish(
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS);
		}
		catch (Throwable throwable) {
			LCSEvent lcsEvent = LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED;

			if (throwable instanceof InvalidLCSClusterEntryTokenException ||
				throwable instanceof LCSClusterEntryTokenDecryptException) {

				lcsEvent =
					LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED;
			}
			else if (throwable instanceof
						MissingLCSClusterEntryTokenException) {

				lcsEvent = LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING;
			}
			else if (throwable instanceof
						MultipleLCSClusterEntryTokenException) {

				lcsEvent = LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS;
			}

			_log.error(
				"Unable to validate the environment token file", throwable);

			_lcsEventManager.publish(lcsEvent);
		}
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
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
		_lcsClusterEntryTokenAdvisor.processLCSClusterEntryToken();

		if (!_lcsPortalClient.isAuthorized(
				_lcsClusterEntryTokenAdvisor.getLCSAccessSecret(),
				_lcsClusterEntryTokenAdvisor.getLCSAccessToken())) {

			throw new InvalidLCSClusterEntryTokenException(
				"The environment token is invalid. Please regenerate, " +
					"download, and install a new token.");
		}

		LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSClusterEntryTokenCheckTask.class);

	@Reference
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;

	@Reference
	private LCSEventManager _lcsEventManager;

	@Reference
	private LCSPortalClient _lcsPortalClient;

}