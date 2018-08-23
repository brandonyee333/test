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
import com.liferay.lcs.util.LCSAlert;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class LCSPortletBuildNumberCheckRunnable implements Runnable {

	public LCSPortletBuildNumberCheckRunnable(
		int latestLCSPortletBuildNumber, LCSAlertAdvisor lcsAlertAdvisor) {

		_latestLCSPortletBuildNumber = latestLCSPortletBuildNumber;
		_lcsAlertAdvisor = lcsAlertAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		try {
			_checkLCSPortletBuildNumber();
		}
		catch (Throwable t) {
			_log.error("Unable to check latest LCS Portlet build number", t);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private void _checkLCSPortletBuildNumber() {
		if (_latestLCSPortletBuildNumber > LCSUtil.getLCSPortletBuildNumber()) {
			_lcsAlertAdvisor.add(
				LCSAlert.WARNING_LCS_PORTLET_NEW_VERSION_AVAILABLE);
		}
		else {
			_lcsAlertAdvisor.remove(
				LCSAlert.WARNING_LCS_PORTLET_NEW_VERSION_AVAILABLE);
		}

		return;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortletBuildNumberCheckRunnable.class);

	private final int _latestLCSPortletBuildNumber;
	private final LCSAlertAdvisor _lcsAlertAdvisor;

}