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
import com.liferay.osb.asah.upgrade.v0_0_0.ProjectsIndexUpgradeStep;
import com.liferay.osb.asah.upgrade.v0_0_0.SnapshotsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_12_0.AsahMarkerUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_12_0.PreAsahMarkerUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_12_0.RunLogUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_13_0.ChannelsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_13_0.DataSourcesUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_13_0.SalesforceUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.CustomEventUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.UserSessionsUpgradeStep;

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
			"0.0.0", ReleaseInfo.getVersion(), _projectsIndexUpgradeStep,
			_snapshotsUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"2.11.0", "2.12.0", _preAsahMarkerUpgradeStep,
			_asahMarkerUpgradeStep, _runLogUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"2.12.0", "2.13.0", _dataSourcesUpgradeStep, _channelsUpgradeStep,
			_salesforceUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"2.13.0", "3.0.0", _customEventUpgradeStep,
			_userSessionsUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private AsahMarkerUpgradeStep _asahMarkerUpgradeStep;

	@Autowired
	private ChannelsUpgradeStep _channelsUpgradeStep;

	@Autowired
	private CustomEventUpgradeStep _customEventUpgradeStep;

	@Autowired
	private DataSourcesUpgradeStep _dataSourcesUpgradeStep;

	@Autowired
	private PreAsahMarkerUpgradeStep _preAsahMarkerUpgradeStep;

	@Autowired
	private ProjectsIndexUpgradeStep _projectsIndexUpgradeStep;

	@Autowired
	private RunLogUpgradeStep _runLogUpgradeStep;

	@Autowired
	private SalesforceUpgradeStep _salesforceUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

	@Autowired
	private UserSessionsUpgradeStep _userSessionsUpgradeStep;

}