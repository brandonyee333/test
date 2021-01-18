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
import com.liferay.osb.asah.upgrade.v2_10_0.DeleteActivityNaniteOSBAsahTasksUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_10_0.PageReferrersUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_10_0.UserSessionsInteractionsUpgradeStep;

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
			"2.9.0", "2.10.0", _deleteActivityNaniteOSBAsahTasksUpgradeStep,
			_pageReferrersUpgradeStep, _userSessionsInteractionsUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private DeleteActivityNaniteOSBAsahTasksUpgradeStep
		_deleteActivityNaniteOSBAsahTasksUpgradeStep;

	@Autowired
	private PageReferrersUpgradeStep _pageReferrersUpgradeStep;

	@Autowired
	private ProjectsIndexUpgradeStep _projectsIndexUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

	@Autowired
	private UserSessionsInteractionsUpgradeStep
		_userSessionsInteractionsUpgradeStep;

}