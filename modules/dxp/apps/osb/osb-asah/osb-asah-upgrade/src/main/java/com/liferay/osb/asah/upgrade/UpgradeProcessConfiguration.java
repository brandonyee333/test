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

package com.liferay.osb.asah.upgrade;

import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.upgrade.v0_0_0.SnapshotsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_9_0.ActivitiesUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_9_0.AnalyticsEventsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_9_0.DXPRawUsersUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_9_0.OSBAsahTasksUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_9_0.PageReferrersUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_9_0.PreferencesUpgradeStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class UpgradeProcessConfiguration {

	@Bean
	public UpgradeProcess upgradeProcess() {
		UpgradeProcess upgradeProcess = new UpgradeProcess();

		upgradeProcess.addUpgradeSteps(
			"0.0.0", ReleaseInfo.getVersion(), _snapshotsUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"2.8.0", "2.9.0", _activitiesUpgradeStep,
			_analyticsEventsUpgradeStep, _dxpRawUsersUpgradeStep,
			_osbAsahTasksUpgradeStep, _pageReferrersUpgradeStep,
			_preferencesUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private ActivitiesUpgradeStep _activitiesUpgradeStep;

	@Autowired
	private AnalyticsEventsUpgradeStep _analyticsEventsUpgradeStep;

	@Autowired
	private DXPRawUsersUpgradeStep _dxpRawUsersUpgradeStep;

	@Autowired
	private OSBAsahTasksUpgradeStep _osbAsahTasksUpgradeStep;

	@Autowired
	private PageReferrersUpgradeStep _pageReferrersUpgradeStep;

	@Autowired
	private PreferencesUpgradeStep _preferencesUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

}