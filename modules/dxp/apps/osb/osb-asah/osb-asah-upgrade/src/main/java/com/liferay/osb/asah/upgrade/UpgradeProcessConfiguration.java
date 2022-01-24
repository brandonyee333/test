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
import com.liferay.osb.asah.upgrade.v3_0_1.EventDefinitionUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_10.SnapshotRepositoryUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_5.CommentPostedEventDefinitionUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_5.VoteEventDefinitionUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_6.EventAttributeIndexUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_0.ChannelMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_0.DataSourceMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_0.PagesUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_0.ProjectMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_0.SchemaUpgradeStep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class UpgradeProcessConfiguration {

	@Bean(name = "globalUpgradeSteps")
	public List<UpgradeStep> globalUpgradeSteps() {
		return Collections.singletonList(_projectMigrationUpgradeStep);
	}

	@Bean
	public UpgradeProcess upgradeProcess() {
		UpgradeProcess upgradeProcess = new UpgradeProcess();

		upgradeProcess.addUpgradeSteps(
			"0.0.0", ReleaseInfo.getVersion(), _snapshotsUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.0.0", "3.0.1", _eventDefinitionUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.0.1", "3.0.5", _commentPostedEventDefinitionUpgradeStep,
			_voteEventDefinitionUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.0.5", "3.0.6", _eventAttributeIndexUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.0.6", "3.0.10", _snapshotRepositoryUpgradeStep);

		List<UpgradeStep> upgradeSteps = new ArrayList<>();

		upgradeSteps.add(_schemaUpgradeStep);

		upgradeSteps.add(_channelMigrationUpgradeStep);
		upgradeSteps.add(_dataSourceMigrationUpgradeStep);
		upgradeSteps.add(_pagesUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.0.10", "3.1.0", upgradeSteps.toArray(new UpgradeStep[0]));

		return upgradeProcess;
	}

	@Autowired
	private ChannelMigrationUpgradeStep _channelMigrationUpgradeStep;

	@Autowired
	private CommentPostedEventDefinitionUpgradeStep
		_commentPostedEventDefinitionUpgradeStep;

	@Autowired
	private DataSourceMigrationUpgradeStep _dataSourceMigrationUpgradeStep;

	@Autowired
	private EventAttributeIndexUpgradeStep _eventAttributeIndexUpgradeStep;

	@Autowired
	private EventDefinitionUpgradeStep _eventDefinitionUpgradeStep;

	@Autowired
	private PagesUpgradeStep _pagesUpgradeStep;

	@Autowired
	private ProjectMigrationUpgradeStep _projectMigrationUpgradeStep;

	@Autowired
	private SchemaUpgradeStep _schemaUpgradeStep;

	@Autowired
	private SnapshotRepositoryUpgradeStep _snapshotRepositoryUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

	@Autowired
	private VoteEventDefinitionUpgradeStep _voteEventDefinitionUpgradeStep;

}