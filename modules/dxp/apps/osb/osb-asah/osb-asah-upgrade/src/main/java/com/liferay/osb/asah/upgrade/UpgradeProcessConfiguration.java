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
import com.liferay.osb.asah.upgrade.v3_0_0.AssetsUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.ChannelsUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.CustomEventUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.DXPEntityUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.DataSourcesUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.IndividualsUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.SalesforceUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_0.UserSessionsUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_1.EventDefinitionUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_5.CommentPostedEventDefinitionUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_5.VoteEventDefinitionUpgradeStep;

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
			"2.12.3", "3.0.0", _assetsUpgradeStep, _channelsUpgradeStep,
			_customEventUpgradeStep, _dataSourcesUpgradeStep,
			_dxpEntityUpgradeStep, _individualsUpgradeStep,
			_salesforceUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.0.0", "3.0.1", _eventDefinitionUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.0.1", "3.0.5", _commentPostedEventDefinitionUpgradeStep,
			_voteEventDefinitionUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private AssetsUpgradeStep _assetsUpgradeStep;

	@Autowired
	private ChannelsUpgradeStep _channelsUpgradeStep;

	@Autowired
	private CommentPostedEventDefinitionUpgradeStep
		_commentPostedEventDefinitionUpgradeStep;

	@Autowired
	private CustomEventUpgradeStep _customEventUpgradeStep;

	@Autowired
	private DataSourcesUpgradeStep _dataSourcesUpgradeStep;

	@Autowired
	private DXPEntityUpgradeStep _dxpEntityUpgradeStep;

	@Autowired
	private EventDefinitionUpgradeStep _eventDefinitionUpgradeStep;

	@Autowired
	private IndividualsUpgradeStep _individualsUpgradeStep;

	@Autowired
	private ProjectsIndexUpgradeStep _projectsIndexUpgradeStep;

	@Autowired
	private SalesforceUpgradeStep _salesforceUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

	@Autowired
	private UserSessionsUpgradeStep _userSessionsUpgradeStep;

	@Autowired
	private VoteEventDefinitionUpgradeStep _voteEventDefinitionUpgradeStep;

}