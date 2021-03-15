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

package com.liferay.osb.asah.batch.curator.bot;

import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualSegmentActivityFieldsNanite;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskManager;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskScheduler;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.spring.annotation.CacheEvict;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.TimeZone;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component
@EnableScheduling
@Profile("!test")
public class OSBAsahBatchCuratorBot {

	@CacheEvict(
		allProjects = true,
		value = {
			"getActivityTransformations", "getGraphQLExecutionResult",
			"getMembershipChangeTransformations"
		}
	)
	@Scheduled(cron = "0 0 0 * * ?")
	public void clearCache() {
	}

	@Scheduled(fixedDelay = DateUtil.HOUR * 6)
	public void curateExperiments() {
		_asahTaskManager.runNanitesForAllProjects("ExperimentNanite");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		try {
			ProjectIdThreadLocal.forProjects(
				_projectDog.getProjects(), this::_init);
		}
		catch (Exception e) {
			_log.error("Unable to schedule nanites", e);
		}
	}

	public void removeNanitesSchedule(String projectId) {
		ProjectIdThreadLocal.forProject(
			projectId,
			() -> {
				_unscheduleNanites();
				_asahTaskManager.removeAsahTasks();
			});
	}

	public void rescheduleNanites(String projectId) {
		ProjectIdThreadLocal.forProject(
			projectId,
			() -> {
				_unscheduleNanites();
				_scheduleNanites();
			});
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void runContentRecommendationDataSolutionNanite() {
		if (_contentRecommendationDataSolutionNaniteRunnable == null) {
			return;
		}

		try {
			ProjectIdThreadLocal.forProjects(
				_projectDog.getProjects(),
				_contentRecommendationDataSolutionNaniteRunnable);
		}
		catch (Exception e) {
			_log.error(
				"Unable to execute ContentRecommendationDataSolutionNanite", e);
		}
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE * 5)
	public void runDataControlTasks() {
		_asahTaskManager.runNanitesForAllProjects("DataControlNanite");
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE * 5)
	public void runDataExportTasks() {
		_asahTaskManager.runNanitesForAllProjects("DataExportNanite");
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void runDataprocSparkManagerMonitoringNanite() {
		if (_dataprocSparkManagerMonitoringNaniteRunnable == null) {
			return;
		}

		try {
			ProjectIdThreadLocal.forProjects(
				_projectDog.getProjects(),
				_dataprocSparkManagerMonitoringNaniteRunnable);
		}
		catch (Exception e) {
			_log.error(
				"Unable to execute DataprocSparkManagerMonitoringNanite", e);
		}
	}

	private String _buildCronExpression(int second, int minute) {
		return String.format("%d %d 0 * * ?", second, minute);
	}

	private Runnable _getDataRetentionRunnable() {
		return () -> _asahTaskManager.runNanites("DataRetentionNanite");
	}

	private Runnable _getDeleteDXPBatchEntitiesRunnable() {
		if (_deleteDXPBatchEntitiesNaniteRunnable == null) {
			return () -> {
			};
		}

		return _deleteDXPBatchEntitiesNaniteRunnable;
	}

	private Runnable _getDeleteTempFilesRunnable() {
		return () -> _asahTaskManager.runNanites("DeleteTempFilesNanite");
	}

	private Runnable _getInterestsRunnable() {
		return () -> {
			_asahTaskManager.runNanites("InterestThresholdScoreNanite");

			_asahTaskManager.runNanites("InterestTopicsNanite");

			_asahTaskManager.runNanites("IndividualInterestScoresNanite");
		};
	}

	private Runnable _getStaleDynamicIndividualSegmentsRunnable() {
		return () -> _asahTaskManager.runNanites(
			"StaleDynamicIndividualSegmentsNanite");
	}

	private void _init() {
		_individualSegmentActivityFieldsNanite.setAnalyticsConfigured(
			_dataSourceDog.isAnalyticsConfigured());

		_elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("status", "STARTED"), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.status = 'INTERRUPTED'", Collections.emptyMap()),
			"run-logs");

		_asahTaskManager.runNanites("DataRetentionNanite");

		_asahTaskManager.runNanites("DeleteTempFilesNanite");

		_asahTaskManager.runNanites("InterestThresholdScoreNanite");

		_asahTaskManager.runNanites("InterestTopicsNanite");

		_asahTaskManager.runNanites("IndividualInterestScoresNanite");

		_asahTaskManager.runNanites("StaleDynamicIndividualSegmentsNanite");

		_asahTaskManager.executeAsahTasks();

		_asahTaskManager.scheduleAsahTasks();

		_scheduleNanites();
	}

	private void _scheduleNanite(Runnable runnable, String scheduledTaskId) {
		String projectId = ProjectIdThreadLocal.getProjectId();

		String scopedScheduledTaskId = String.format(
			"%s#%s", projectId, scheduledTaskId);

		String cronExpression = _buildCronExpression(
			RandomUtils.nextInt(0, 60), RandomUtils.nextInt(0, 16));

		String timeZoneId = _timeZoneDog.getTimeZoneId();

		_asahTaskScheduler.schedule(
			new CronTrigger(cronExpression, TimeZone.getTimeZone(timeZoneId)),
			runnable, scopedScheduledTaskId);

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"%s scheduled to run at %s (%s) for project %s",
					scheduledTaskId, cronExpression, timeZoneId, projectId));
		}

		_scheduledTasks.put(projectId, scopedScheduledTaskId);
	}

	private void _scheduleNanites() {
		_scheduleNanite(_getDataRetentionRunnable(), "DataRetentionNanite");
		_scheduleNanite(
			_getDeleteDXPBatchEntitiesRunnable(),
			"DeleteDXPBatchEntitiesNanite");
		_scheduleNanite(_getDeleteTempFilesRunnable(), "DeleteTempFilesNanite");
		_scheduleNanite(_getInterestsRunnable(), "Interests");
		_scheduleNanite(
			_getStaleDynamicIndividualSegmentsRunnable(),
			"StaleDynamicIndividualSegments");
	}

	private void _unscheduleNanites() {
		String projectId = ProjectIdThreadLocal.getProjectId();

		for (String scheduledTask : _scheduledTasks.get(projectId)) {
			if (_log.isInfoEnabled()) {
				_log.info(scheduledTask + " unscheduled");
			}

			_asahTaskScheduler.unschedule(scheduledTask);
		}

		_scheduledTasks.remove(projectId);
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahBatchCuratorBot.class);

	@Autowired
	private AsahTaskManager _asahTaskManager;

	@Autowired
	private AsahTaskScheduler _asahTaskScheduler;

	@Autowired(required = false)
	@Qualifier("contentRecommendationDataSolutionNaniteRunnable")
	private Runnable _contentRecommendationDataSolutionNaniteRunnable;

	@Autowired(required = false)
	@Qualifier("dataprocSparkManagerMonitoringNaniteRunnable")
	private Runnable _dataprocSparkManagerMonitoringNaniteRunnable;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired(required = false)
	@Qualifier("deleteDXPBatchEntitiesNaniteRunnable")
	private Runnable _deleteDXPBatchEntitiesNaniteRunnable;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private IndividualSegmentActivityFieldsNanite
		_individualSegmentActivityFieldsNanite;

	@Autowired
	private ProjectDog _projectDog;

	private final MultiValuedMap<String, String> _scheduledTasks =
		new HashSetValuedHashMap<>();

	@Autowired
	private TimeZoneDog _timeZoneDog;

}