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

import com.liferay.osb.asah.upgrade.v2_5_1.PagesUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_6_0.OSBAsahTasksIndexMappingUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_6_0.PagesIndexMappingUpgradeStep;

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

		upgradeProcess.addUpgradeSteps("2.5.0", "2.5.1", _pagesUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"2.5.1", "2.6.0", _osbAsahTasksIndexMappingUpgradeStep,
			_pagesIndexMappingUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private OSBAsahTasksIndexMappingUpgradeStep
		_osbAsahTasksIndexMappingUpgradeStep;

	@Autowired
	private PagesIndexMappingUpgradeStep _pagesIndexMappingUpgradeStep;

	@Autowired
	private PagesUpgradeStep _pagesUpgradeStep;

}