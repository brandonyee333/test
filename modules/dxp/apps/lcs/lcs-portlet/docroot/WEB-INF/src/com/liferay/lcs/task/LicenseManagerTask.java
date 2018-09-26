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
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class LicenseManagerTask implements ScheduledTask {

	public LicenseManagerTask(
		LCSGatewayService lcsGatewayService,
		LCSPortletStateAdvisor lcsPortletStateAdvisor) {

		_lcsGatewayService = lcsGatewayService;
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

		long currentTimeMills = System.currentTimeMillis();

		long lastLicenseCheckTime =
			_lcsPortletStateAdvisor.getLastLicenseCheckTime();

		if ((currentTimeMills - lastLicenseCheckTime) < _LICENSE_CHECK_PERIOD) {
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

		_lcsPortletStateAdvisor.updateLicenseCheckTime();
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

	private final LCSGatewayService _lcsGatewayService;
	private final LCSPortletStateAdvisor _lcsPortletStateAdvisor;

}