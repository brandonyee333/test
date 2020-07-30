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

import com.liferay.osb.asah.upgrade.v2_7_0.AssetsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.CanonicalUrlMappingUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.FaroInfoIndividualsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.SessionActivitiesUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.UnprocessedAnalyticsEventsUpgradeStep;

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
			"2.6.1", "2.7.0", _assetsUpgradeStep,
			_canonicalUrlMappingUpgradeStep, _faroInfoIndividualsUpgradeStep,
			_sessionActivitiesUpgradeStep,
			_unprocessedAnalyticsEventsUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private AssetsUpgradeStep _assetsUpgradeStep;

	@Autowired
	private CanonicalUrlMappingUpgradeStep _canonicalUrlMappingUpgradeStep;

	@Autowired
	private FaroInfoIndividualsUpgradeStep _faroInfoIndividualsUpgradeStep;

	@Autowired
	private SessionActivitiesUpgradeStep _sessionActivitiesUpgradeStep;

	@Autowired
	private UnprocessedAnalyticsEventsUpgradeStep
		_unprocessedAnalyticsEventsUpgradeStep;

}