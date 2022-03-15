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
import com.liferay.osb.asah.upgrade.v3_1_3.OrganizationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_4.FieldMappingUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.AsahMarkerMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.AsahTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.BlockedKeywordMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.DataControlTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.DataExportTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.DatabaseSchemaUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.ExperimentMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.JobMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_2_0.JobRunMigrationUpgradeStep;

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

		upgradeProcess.addUpgradeSteps(
			"3.1.0", "3.1.3", _organizationUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.1.3", "3.1.4", _fieldMappingUpgradeStep);

		List<UpgradeStep> upgradeSteps = new ArrayList<>();

		upgradeSteps.add(_databaseSchemaUpgradeStep);

		upgradeSteps.add(_asahMarkerMigrationUpgradeStep);
		upgradeSteps.add(_asahTaskMigrationUpgradeStep);
		upgradeSteps.add(_blockedKeywordMigrationUpgradeStep);
		upgradeSteps.add(_dataControlTaskMigrationUpgradeStep);
		upgradeSteps.add(_dataExportTaskMigrationUpgradeStep);
		upgradeSteps.add(_experimentMigrationUpgradeStep);
		upgradeSteps.add(_jobMigrationUpgradeStep);
		upgradeSteps.add(_jobRunMigrationUpgradeStep);

		upgradeProcess.addUpgradeSteps(
			"3.1.4", "3.2.0", upgradeSteps.toArray(new UpgradeStep[0]));

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
	private FieldMappingUpgradeStep _fieldMappingUpgradeStep;

	@Autowired
	private JobMigrationUpgradeStep _jobMigrationUpgradeStep;

	@Autowired
	private JobRunMigrationUpgradeStep _jobRunMigrationUpgradeStep;

	@Autowired
	private OrganizationUpgradeStep _organizationUpgradeStep;

	@Autowired
	private SnapshotsUpgradeStep _snapshotsUpgradeStep;

}