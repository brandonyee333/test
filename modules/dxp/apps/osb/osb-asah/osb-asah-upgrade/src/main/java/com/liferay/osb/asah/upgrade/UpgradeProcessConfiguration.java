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
import com.liferay.osb.asah.upgrade.v4_0_0.AsahMarkerMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.AsahTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.BlockedKeywordMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.CSVUserMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DataControlTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DataExportTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DatabaseSchemaUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.ExperimentMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.InterestTopicMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.ItemRecommendationMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.JobMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.JobRunMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.RunLogMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentFilterUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SequenceUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SuppressionMigrationUpgradeStep;

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
			"0.0.0", ReleaseInfo.getVersion(), _snapshotsUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.6.0", "3.7.0", _databaseSchemaUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.0", "3.7.1", _asahMarkerMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.1", "3.7.2", _asahTaskMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.2", "3.7.3", _blockedKeywordMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.3", "3.7.4", _csvUserMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.4", "3.7.5", _dataControlTaskMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.5", "3.7.6", _dataExportTaskMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.7", "3.7.8", _experimentMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.8", "3.7.9", _interestTopicMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.9", "3.8.0", _itemRecommendationMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.1", "3.8.2", _jobMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.1", "3.8.2", _jobRunMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.2", "3.8.3", _runLogMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.3", "3.8.4", _segmentMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.4", "3.8.5", _suppressionMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps("3.8.5", "3.8.6", _sequenceUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.6", "4.0.0", _segmentFilterUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private AsahMarkerMigrationUpgradeStep _asahMarkerMigrationUpgradeStep;

	@Autowired
	private AsahTaskMigrationUpgradeStep _asahTaskMigrationUpgradeStep;

	@Autowired
	private BlockedKeywordMigrationUpgradeStep
		_blockedKeywordMigrationUpgradeStep;

	@Autowired
	private CSVUserMigrationUpgradeStep _csvUserMigrationUpgradeStep;

	@Autowired
	private DatabaseSchemaUpgradeStep _databaseSchemaUpgradeStep;

	@Autowired
	private DataControlTaskMigrationUpgradeStep
		_dataControlTaskMigrationUpgradeStep;

	@Autowired
	private DataExportTaskMigrationUpgradeStep
		_dataExportTaskMigrationUpgradeStep;

	@Autowired
	private ExperimentMigrationUpgradeStep _experimentMigrationUpgradeStep;

	@Autowired
	private InterestTopicMigrationUpgradeStep
		_interestTopicMigrationUpgradeStep;

	@Autowired
	private ItemRecommendationMigrationUpgradeStep
		_itemRecommendationMigrationUpgradeStep;

	@Autowired
	private JobMigrationUpgradeStep _jobMigrationUpgradeStep;

	@Autowired
	private JobRunMigrationUpgradeStep _jobRunMigrationUpgradeStep;

	@Autowired
	private RunLogMigrationUpgradeStep _runLogMigrationUpgradeStep;

	@Autowired
	private SegmentFilterUpgradeStep _segmentFilterUpgradeStep;

	@Autowired
	private SegmentMigrationUpgradeStep _segmentMigrationUpgradeStep;

	@Autowired
	private SequenceUpgradeStep _sequenceUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

	@Autowired
	private SuppressionMigrationUpgradeStep _suppressionMigrationUpgradeStep;

}