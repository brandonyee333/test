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

import com.liferay.osb.asah.upgrade.v2_7_3.ActivitiesUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_8_0.PageReferrersUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_8_0.RunLogCleanupUpgradeStep;

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
			"2.7.1", "2.7.3", _activitiesUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"2.7.3", "2.8.0", _pageReferrersUpgradeStep,
			_runLogCleanupUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private ActivitiesUpgradeStep _activitiesUpgradeStep;

	@Autowired
	private PageReferrersUpgradeStep _pageReferrersUpgradeStep;

	@Autowired
	private RunLogCleanupUpgradeStep _runLogCleanupUpgradeStep;

}