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

import com.liferay.lcs.advisor.LCSPortletStateAdvisor;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

/**
 * @author Igor Beslic
 */
public class LicenseManagerTask implements ScheduledTask {

	public LicenseManagerTask(
		LCSConnectionManager lcsConnectionManager,
		LCSPortletStateAdvisor lcsPortletStateAdvisor) {

		_lcsConnectionManager = lcsConnectionManager;
		_lcsPortletStateAdvisor = lcsPortletStateAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	@Override
	public void run() {
		if (_log.isTraceEnabled()) {
			_log.trace("Running license manager task");
		}

		Map<String, String> lcsConnectionMetadata =
			_lcsConnectionManager.getLCSConnectionMetadata();

		if (_log.isTraceEnabled()) {
			_log.trace("Checking LCS portlet state");
		}

		long currentTimeMills = System.currentTimeMillis();

		long licenseCheckTime = GetterUtil.getLong(
			lcsConnectionMetadata.get("licenseCheckTime"));

		if ((currentTimeMills - licenseCheckTime) < _LICENSE_CHECK_PERIOD) {
			LCSUtil.processLCSPortletState(
				_lcsPortletStateAdvisor.getLCSPortletState(false));

			return;
		}

		LCSPortletState lcsPortletState =
			_lcsPortletStateAdvisor.getLCSPortletState(true);

		if (_log.isTraceEnabled()) {
			_log.trace("LCS portlet state: " + lcsPortletState);
		}

		LCSUtil.processLCSPortletState(lcsPortletState);

		_lcsConnectionManager.putLCSConnectionMetadata(
			"licenseCheckTime", String.valueOf(currentTimeMills));
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private static final long _LICENSE_CHECK_PERIOD = 60000L * 15L;

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseManagerTask.class);

	private final LCSConnectionManager _lcsConnectionManager;
	private final LCSPortletStateAdvisor _lcsPortletStateAdvisor;

}