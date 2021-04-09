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
import com.liferay.osb.asah.upgrade.v2_10_0.DeleteActivityNaniteAsahTasksUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_10_0.PageReferrersUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_10_0.UserSessionsInteractionsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_11_0.EngagementsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_12_0.RunLogUpgradeStep;

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
			"2.9.0", "2.10.0", _deleteActivityNaniteAsahTasksUpgradeStep,
			_pageReferrersUpgradeStep, _userSessionsInteractionsUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"2.10.0", "2.11.0", _engagementsUpgradeStep);

		upgradeProcess.addUpgradeSteps("2.11.0", "2.12.0", _runLogUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private DeleteActivityNaniteAsahTasksUpgradeStep
		_deleteActivityNaniteAsahTasksUpgradeStep;

	@Autowired
	private EngagementsUpgradeStep _engagementsUpgradeStep;

	@Autowired
	private PageReferrersUpgradeStep _pageReferrersUpgradeStep;

	@Autowired
	private ProjectsIndexUpgradeStep _projectsIndexUpgradeStep;

	@Autowired
	private RunLogUpgradeStep _runLogUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

	@Autowired
	private UserSessionsInteractionsUpgradeStep
		_userSessionsInteractionsUpgradeStep;

}