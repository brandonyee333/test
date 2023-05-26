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

import com.liferay.osb.asah.upgrade.v4_0_0.AsahMarkerMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.BigQuerySchemaUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.BlockedKeywordMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.CustomFieldMappingMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DataControlTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.DataExportTaskMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.ExperimentMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.InterestTopicMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.ItemRecommendationMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.JobMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.JobRunMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.PostgreSQLSchemaUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.RunLogMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentFilterUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SequenceUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_0.SuppressionMigrationUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_1.SegmentMembershipFilterUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_2.SegmentMembershipsCountUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_2.SegmentReferencedObjectsUpgradeStep;
import com.liferay.osb.asah.upgrade.v4_0_2.StaticMembershipMigrationUpgradeStep;

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
			"3.6.0", "3.6.1", _bigQuerySchemaUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.6.1", "3.6.2", _customFieldMappingMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.6.2", "3.7.0", _postgreSQLSchemaUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.0", "3.7.2", _asahMarkerMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.2", "3.7.3", _blockedKeywordMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.3", "3.7.4", _dataControlTaskMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.4", "3.7.5", _dataExportTaskMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.5", "3.7.6", _experimentMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.6", "3.7.7", _interestTopicMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.7", "3.7.8", _itemRecommendationMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.8", "3.7.9", _jobMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.7.9", "3.8.0", _jobRunMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.0", "3.8.1", _runLogMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.1", "3.8.2", _segmentMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.2", "3.8.3", _suppressionMigrationUpgradeStep);
		upgradeProcess.addUpgradeSteps("3.8.3", "3.8.4", _sequenceUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"3.8.4", "4.0.0", _segmentFilterUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"4.0.0", "4.0.1", _segmentMembershipFilterUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"4.0.1", "4.0.2", _segmentMembershipsCountUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"4.0.2", "4.0.3", _segmentReferencedObjectsUpgradeStep);
		upgradeProcess.addUpgradeSteps(
			"4.0.3", "4.0.4", _staticMembershipMigrationUpgradeStep);

		return upgradeProcess;
	}

	@Autowired
	private AsahMarkerMigrationUpgradeStep _asahMarkerMigrationUpgradeStep;

	@Autowired
	private BigQuerySchemaUpgradeStep _bigQuerySchemaUpgradeStep;

	@Autowired
	private BlockedKeywordMigrationUpgradeStep
		_blockedKeywordMigrationUpgradeStep;

	@Autowired
	private CustomFieldMappingMigrationUpgradeStep
		_customFieldMappingMigrationUpgradeStep;

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
	private PostgreSQLSchemaUpgradeStep _postgreSQLSchemaUpgradeStep;

	@Autowired
	private RunLogMigrationUpgradeStep _runLogMigrationUpgradeStep;

	@Autowired
	private SegmentFilterUpgradeStep _segmentFilterUpgradeStep;

	@Autowired
	private SegmentMembershipFilterUpgradeStep
		_segmentMembershipFilterUpgradeStep;

	@Autowired
	private SegmentMembershipsCountUpgradeStep
		_segmentMembershipsCountUpgradeStep;

	@Autowired
	private SegmentMigrationUpgradeStep _segmentMigrationUpgradeStep;

	@Autowired
	private SegmentReferencedObjectsUpgradeStep
		_segmentReferencedObjectsUpgradeStep;

	@Autowired
	private SequenceUpgradeStep _sequenceUpgradeStep;

	@Autowired
	private StaticMembershipMigrationUpgradeStep
		_staticMembershipMigrationUpgradeStep;

	@Autowired
	private SuppressionMigrationUpgradeStep _suppressionMigrationUpgradeStep;

}