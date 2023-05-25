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

import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.common.repository.DataControlTaskRepository;
import com.liferay.osb.asah.common.repository.DataExportTaskRepository;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.common.repository.InterestTopicRepository;
import com.liferay.osb.asah.common.repository.ItemRecommendationRepository;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.repository.JobRunRepository;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.SuppressionRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnProperty(
	matchIfMissing = true, value = "osb.asah.verify.process.enabled"
)
public class VerifyProcessRunner {

	public void run() {
		for (Project project : _projectDog.getProjects()) {
			if (!Objects.equals(
					project.getVersion(), ReleaseInfo.getVersion())) {

				continue;
			}

			try {
				ProjectIdThreadLocal.setProjectId(project.getId());

				if (_log.isInfoEnabled()) {
					_log.info("Verifying project: " + project.getId());
				}

				_run();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Verification completed for project: " +
							project.getId());
				}
			}
			catch (Exception exception) {
				_log.error(
					"Verification failed for project: " + project.getId(),
					exception);
			}
			finally {
				ProjectIdThreadLocal.remove();
			}
		}
	}

	private void _run() {
		long cerebroInfoAsahMarkersCount =
			_cerebroInfoElasticsearchInvoker.count(
				"OSBAsahMarkers", QueryBuilders.matchAllQuery());
		long dxpRawMarkersCount = _dxpRawElasticsearchInvoker.count(
			"OSBAsahMarkers", QueryBuilders.matchAllQuery());
		long faroInfoAsahMarkersCount = _faroInfoElasticsearchInvoker.count(
			"OSBAsahMarkers", QueryBuilders.matchAllQuery());

		long expectedAsahMarkersCount =
			cerebroInfoAsahMarkersCount + dxpRawMarkersCount +
				faroInfoAsahMarkersCount;

		_verifyState(
			_asahMarkerRepository.count(), expectedAsahMarkersCount,
			"AsahMarker");

		_verifyState(
			_blockedKeywordRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"blocked-keywords", QueryBuilders.matchAllQuery()),
			"BlockedKeywords");

		_verifyState(
			_dataControlTaskRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"data-control-tasks", QueryBuilders.matchAllQuery()),
			"DataControlTask");

		_verifyState(
			_dataExportTaskRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"data-export-tasks", QueryBuilders.matchAllQuery()),
			"DataExportTask");

		_verifyState(
			_experimentRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"experiments", QueryBuilders.matchAllQuery()),
			"Experiment");

		_verifyState(
			_interestTopicRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"interest-topics", QueryBuilders.matchAllQuery()),
			"InterestTopic");

		_verifyState(
			_itemRecommendationRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"recommended-items", QueryBuilders.matchAllQuery()),
			"ItemRecommendation");

		_verifyState(
			_jobRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"jobs", QueryBuilders.matchAllQuery()),
			"Job");

		_verifyState(
			_jobJobRunRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"job-runs", QueryBuilders.matchAllQuery()),
			"JobRun");

		long dxpRawRunLogsCount = _dxpRawElasticsearchInvoker.count(
			"run-logs", QueryBuilders.matchAllQuery());
		long faroInfoRunLogsCount = _faroInfoElasticsearchInvoker.count(
			"run-logs", QueryBuilders.matchAllQuery());

		long expectedRunLogsCount = dxpRawRunLogsCount + faroInfoRunLogsCount;

		_verifyState(_runLogRepository.count(), expectedRunLogsCount, "RunLog");

		_verifyState(
			_segmentRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"individual-segments", QueryBuilders.matchAllQuery()),
			"Segment");

		_verifyState(
			_suppressionRepository.count(),
			_faroInfoElasticsearchInvoker.count(
				"suppressions", QueryBuilders.matchAllQuery()),
			"Suppression");
	}

	private void _verifyState(
		long actualRowsCount, long expectedRowsCount, String upgradeStepName) {

		if (_log.isInfoEnabled()) {
			_log.info("Verifying " + upgradeStepName);
		}

		Assert.state(
			actualRowsCount == expectedRowsCount,
			String.format(
				"Unexpected %s rows count. Actual %d, Expected: %d",
				upgradeStepName, actualRowsCount, expectedRowsCount));
	}

	private static final Log _log = LogFactory.getLog(
		VerifyProcessRunner.class);

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

	@Autowired
	private BlockedKeywordRepository _blockedKeywordRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DataControlTaskRepository _dataControlTaskRepository;

	@Autowired
	private DataExportTaskRepository _dataExportTaskRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ExperimentRepository _experimentRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private InterestTopicRepository _interestTopicRepository;

	@Autowired
	private ItemRecommendationRepository _itemRecommendationRepository;

	@Autowired
	private JobRunRepository _jobJobRunRepository;

	@Autowired
	private JobRepository _jobRepository;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private RunLogRepository _runLogRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private SuppressionRepository _suppressionRepository;

}