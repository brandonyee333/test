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

import com.liferay.osb.asah.batch.curator.bot.nanite.BaseActivitiesNanite;
import com.liferay.osb.asah.batch.curator.bot.scheduling.OSBAsahTaskManager;
import com.liferay.osb.asah.batch.curator.bot.scheduling.OSBAsahTaskScheduler;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component
@EnableScheduling
@Profile("!test")
public class OSBAsahBatchCuratorBot {

	@Scheduled(cron = "0 0 0 * * ?")
	public void clearCache() {
		for (String cacheName : _CACHE_NAMES) {
			Cache cache = _cacheManager.getCache(cacheName);

			cache.clear();

			if (_log.isInfoEnabled()) {
				_log.info("Cache cleared: " + cacheName);
			}
		}
	}

	@Scheduled(fixedDelay = DateUtil.HOUR * 6)
	public void curateExperiments() {
		_osbAsahTaskManager.runNanites("ExperimentNanite");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		BaseActivitiesNanite.setAnalyticsConfigured(
			_faroInfoDataSourceDog.isAnalyticsConfigured());

		_elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("status", "STARTED"), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.status = 'INTERRUPTED'", Collections.emptyMap()),
			"run-logs");

		_osbAsahTaskManager.runNanites("AssetEngagementScoresNanite");

		_osbAsahTaskManager.runNanites("DataRetentionNanite");

		_osbAsahTaskManager.runNanites("DeleteTempFilesNanite");

		_osbAsahTaskManager.runNanites(
			"IndividualEngagementScoresNanite",
			"IndividualSegmentEngagementScoresNanite",
			"AccountEngagementScoresNanite");

		_osbAsahTaskManager.runNanites("InterestThresholdScoreNanite");

		_osbAsahTaskManager.runNanites("InterestTopicsNanite");

		_osbAsahTaskManager.runNanites("IndividualInterestScoresNanite");

		_osbAsahTaskManager.runNanites("StaleDynamicIndividualSegmentsNanite");

		_osbAsahTaskManager.executeOSBAsahTasks();

		_osbAsahTaskManager.scheduleOSBAsahTasks();

		scheduleNanites();
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void runContentRecommendationDataSolutionNanite() {
		if (_contentRecommendationDataSolutionNaniteRunnable != null) {
			_contentRecommendationDataSolutionNaniteRunnable.run();
		}
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE * 5)
	public void runDataControlTasks() {
		_osbAsahTaskManager.runNanites("DataControlNanite");
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE * 5)
	public void runDataExportTasks() {
		_osbAsahTaskManager.runNanites("DataExportNanite");
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void runDataprocSparkManagerMonitoringNanite() {
		if (_dataprocSparkManagerMonitoringNaniteRunnable != null) {
			_dataprocSparkManagerMonitoringNaniteRunnable.run();
		}
	}

	public void scheduleNanites() {
		_scheduleNanite(_curateEngagements(), "Engagements");

		_scheduleNanite(_curateInterests(), "Interests");

		_scheduleNanite(
			_curateStaleDynamicIndividualSegments(),
			"StaleDynamicIndividualSegments");

		_scheduleNanite(_runDataRetentionNanite(), "DataRetentionNanite");

		_scheduleNanite(
			_runDeleteDXPBatchEntitiesNanite(), "DeleteDXPBatchEntitiesNanite");

		_scheduleNanite(_runDeleteTempFilesNanite(), "DeleteTempFilesNanite");
	}

	private Runnable _curateEngagements() {
		return () -> {
			_osbAsahTaskManager.runNanites("AssetEngagementScoresNanite");

			_osbAsahTaskManager.runNanites(
				"IndividualEngagementScoresNanite",
				"IndividualSegmentEngagementScoresNanite",
				"AccountEngagementScoresNanite");
		};
	}

	private Runnable _curateInterests() {
		return () -> {
			_osbAsahTaskManager.runNanites("InterestThresholdScoreNanite");

			_osbAsahTaskManager.runNanites("InterestTopicsNanite");

			_osbAsahTaskManager.runNanites("IndividualInterestScoresNanite");
		};
	}

	private Runnable _curateStaleDynamicIndividualSegments() {
		return () -> _osbAsahTaskManager.runNanites(
			"StaleDynamicIndividualSegmentsNanite");
	}

	private Runnable _runDataRetentionNanite() {
		return () -> _osbAsahTaskManager.runNanites("DataRetentionNanite");
	}

	private Runnable _runDeleteDXPBatchEntitiesNanite() {
		return () -> {
			if (_deleteDXPBatchEntitiesNaniteRunnable != null) {
				_deleteDXPBatchEntitiesNaniteRunnable.run();
			}
		};
	}

	private Runnable _runDeleteTempFilesNanite() {
		return () -> _osbAsahTaskManager.runNanites("DeleteTempFilesNanite");
	}

	private void _scheduleNanite(Runnable runnable, String taskId) {
		_osbAsahTaskScheduler.schedule(
			_CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY, runnable, taskId);
	}

	private static final String[] _CACHE_NAMES = {
		"getActivityTransformations", "getGraphQLExecutionResult",
		"getMembershipChangeTransformations"
	};

	private static final String _CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY =
		"${random.int[0,60]} ${random.int[0,15]} 0 * * ?";

	private static final Log _log = LogFactory.getLog(
		OSBAsahBatchCuratorBot.class);

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired(required = false)
	@Qualifier("contentRecommendationDataSolutionNaniteRunnable")
	private Runnable _contentRecommendationDataSolutionNaniteRunnable;

	@Autowired(required = false)
	@Qualifier("dataprocSparkManagerMonitoringNaniteRunnable")
	private Runnable _dataprocSparkManagerMonitoringNaniteRunnable;

	@Autowired(required = false)
	@Qualifier("deleteDXPBatchEntitiesNaniteRunnable")
	private Runnable _deleteDXPBatchEntitiesNaniteRunnable;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private OSBAsahTaskManager _osbAsahTaskManager;

	@Autowired
	private OSBAsahTaskScheduler _osbAsahTaskScheduler;

}