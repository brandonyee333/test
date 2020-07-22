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

import com.liferay.osb.asah.upgrade.v2_6_0.ActivitiesIndexMappingUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_6_0.CerebroInfoUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_6_0.FaroInfoOrganizationsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_6_0.OSBAsahTasksIndexMappingUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_6_0.PagesIndexMappingUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_6_1.RemoveDanglingFieldMappingsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.AssetsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.CanonicalUrlMappingUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.JobRunsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.JobsUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.PageActivitiesContextUpgradeStep;
import com.liferay.osb.asah.upgrade.v2_7_0.SessionActivitiesUpgradeStep;

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
			"2.5.1", "2.6.0", _activitiesIndexMappingUpgradeStep,
			_cerebroInfoUpgradeStep, _faroInfoOrganizationsUpgradeStep,
			_osbAsahTasksIndexMappingUpgradeStep,
			_pagesIndexMappingUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"2.6.0", "2.6.1", _removeDanglingFieldMappingsUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"2.6.1", "2.7.0", _assetsUpgradeStep,
			_canonicalUrlMappingUpgradeStep, _jobRunsUpgradeStep,
			_jobsUpgradeStep, _pageActivitiesContextUpgradeStep,
			_sessionActivitiesUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private ActivitiesIndexMappingUpgradeStep
		_activitiesIndexMappingUpgradeStep;

	@Autowired
	private AssetsUpgradeStep _assetsUpgradeStep;

	@Autowired
	private CanonicalUrlMappingUpgradeStep _canonicalUrlMappingUpgradeStep;

	@Autowired
	private CerebroInfoUpgradeStep _cerebroInfoUpgradeStep;

	@Autowired
	private FaroInfoOrganizationsUpgradeStep _faroInfoOrganizationsUpgradeStep;

	@Autowired
	private JobRunsUpgradeStep _jobRunsUpgradeStep;

	@Autowired
	private JobsUpgradeStep _jobsUpgradeStep;

	@Autowired
	private OSBAsahTasksIndexMappingUpgradeStep
		_osbAsahTasksIndexMappingUpgradeStep;

	@Autowired
	private PageActivitiesContextUpgradeStep _pageActivitiesContextUpgradeStep;

	@Autowired
	private PagesIndexMappingUpgradeStep _pagesIndexMappingUpgradeStep;

	@Autowired
	private RemoveDanglingFieldMappingsUpgradeStep
		_removeDanglingFieldMappingsUpgradeStep;

	@Autowired
	private SessionActivitiesUpgradeStep _sessionActivitiesUpgradeStep;

}