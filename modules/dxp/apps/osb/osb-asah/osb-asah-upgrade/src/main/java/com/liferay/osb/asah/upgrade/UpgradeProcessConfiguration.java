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

import com.liferay.osb.asah.upgrade.v4_1_0.PageReferrersViewUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_1_0.SegmentUpgradeStep;

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

		upgradeProcess.addUpgradeSteps("4.0.9", "4.0.10", _segmentUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"4.0.10", "4.0.11", _pageReferrersViewUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private PageReferrersViewUpgradeStep _pageReferrersViewUpgradeStep;

	@Autowired
	private SegmentUpgradeStep _segmentUpgradeStep;

}