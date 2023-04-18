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
import com.liferay.osb.asah.upgrade.v4_0_0.CustomAssetDashboardMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DXPEntityMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DataControlTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DataExportTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DatabaseSchemaUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.ExperimentMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.JobMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.JobRunMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentFilterUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SequenceUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SuppressionMigrationUpgradeStep;

import java.util.ArrayList;
import java.util.List;

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

		List<UpgradeStep> upgradeSteps = new ArrayList<>();

		upgradeSteps.add(_databaseSchemaUpgradeStep);

		upgradeSteps.add(_asahMarkerMigrationUpgradeStep);
		upgradeSteps.add(_asahTaskMigrationUpgradeStep);
		upgradeSteps.add(_blockedKeywordMigrationUpgradeStep);
		upgradeSteps.add(_csvUserMigrationUpgradeStep);
		upgradeSteps.add(_customAssetDashboardMigrationUpgradeStep);
		upgradeSteps.add(_dataControlTaskMigrationUpgradeStep);
		upgradeSteps.add(_dataExportTaskMigrationUpgradeStep);
		upgradeSteps.add(_dxpEntityMigrationUpgradeStep);
		upgradeSteps.add(_experimentMigrationUpgradeStep);
		upgradeSteps.add(_jobMigrationUpgradeStep);
		upgradeSteps.add(_jobRunMigrationUpgradeStep);
		upgradeSteps.add(_sequenceUpgradeStep);
		upgradeSteps.add(_suppressionMigrationUpgradeStep);
		upgradeSteps.add(_segmentFilterUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.6.0", "4.0.0", upgradeSteps.toArray(new UpgradeStep[0]));

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
	private CustomAssetDashboardMigrationUpgradeStep
		_customAssetDashboardMigrationUpgradeStep;

	@Autowired
	private DatabaseSchemaUpgradeStep _databaseSchemaUpgradeStep;

	@Autowired
	private DataControlTaskMigrationUpgradeStep
		_dataControlTaskMigrationUpgradeStep;

	@Autowired
	private DataExportTaskMigrationUpgradeStep
		_dataExportTaskMigrationUpgradeStep;

	@Autowired
	private DXPEntityMigrationUpgradeStep _dxpEntityMigrationUpgradeStep;

	@Autowired
	private ExperimentMigrationUpgradeStep _experimentMigrationUpgradeStep;

	@Autowired
	private JobMigrationUpgradeStep _jobMigrationUpgradeStep;

	@Autowired
	private JobRunMigrationUpgradeStep _jobRunMigrationUpgradeStep;

	@Autowired
	private SegmentFilterUpgradeStep _segmentFilterUpgradeStep;

	@Autowired
	private SequenceUpgradeStep _sequenceUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

	@Autowired
	private SuppressionMigrationUpgradeStep _suppressionMigrationUpgradeStep;

}