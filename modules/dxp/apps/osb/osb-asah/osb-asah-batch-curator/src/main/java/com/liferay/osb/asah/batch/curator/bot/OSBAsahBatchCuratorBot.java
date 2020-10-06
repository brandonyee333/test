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
import com.liferay.osb.asah.batch.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
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

	@Scheduled(cron = _CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY)
	public void curateEngagements() {
		run("AssetEngagementScoresNanite");

		run(
			"IndividualEngagementScoresNanite",
			"IndividualSegmentEngagementScoresNanite",
			"AccountEngagementScoresNanite");
	}

	@Scheduled(fixedDelay = DateUtil.HOUR * 6)
	public void curateExperiments() {
		run("ExperimentNanite");
	}

	@Scheduled(cron = _CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY)
	public void curateInterests() {
		run("InterestThresholdScoreNanite");

		run("InterestTopicsNanite");

		run("IndividualInterestScoresNanite");
	}

	@Scheduled(cron = _CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY)
	public void curateStaleDynamicIndividualSegments() {
		run("StaleDynamicIndividualSegmentsNanite");
	}

	public void executeOSBAsahTask(
		boolean force, JSONObject osbAsahTaskJSONObject) {

		String className = osbAsahTaskJSONObject.getString("className");

		if (className.equals("UpdateDynamicMembershipsNanite")) {
			_updateDynamicMembershipsNaniteThreadPoolTaskExecutor.execute(
				new NanitesRunnable(false, osbAsahTaskJSONObject));
		}
		else {
			_threadPoolTaskExecutor.execute(
				new NanitesRunnable(force, osbAsahTaskJSONObject));
		}
	}

	@PostConstruct
	public void init() {
		for (Nanite nanite : _nanites) {
			Class<?> clazz = nanite.getClass();

			_nanitesMap.put(clazz.getSimpleName(), nanite);
		}
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

		run("AssetEngagementScoresNanite");

		run("DataRetentionNanite");

		run("DeleteTempFilesNanite");

		run(
			"IndividualEngagementScoresNanite",
			"IndividualSegmentEngagementScoresNanite",
			"AccountEngagementScoresNanite");

		run("InterestThresholdScoreNanite");

		run("InterestTopicsNanite");

		run("IndividualInterestScoresNanite");

		run("StaleDynamicIndividualSegmentsNanite");

		run("UpgradeNanite");

		_executeOSBAsahTasks();

		_scheduleOSBAsahTasks();
	}

	public void run(String... naniteClassNames) {
		_threadPoolTaskExecutor.execute(new NanitesRunnable(naniteClassNames));
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void runContentRecommendationDataSolutionNanite() {
		run("ContentRecommendationDataSolutionNanite");
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE * 5)
	public void runDataControlTasks() {
		run("DataControlNanite");
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE * 5)
	public void runDataExportTasks() {
		run("DataExportNanite");
	}

	@Scheduled(cron = _CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY)
	public void runDataRetentionNanite() {
		run("DataRetentionNanite");
	}

	@Scheduled(cron = _CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY)
	public void runDeleteDXPBatchEntitiesNanite() {
		if (_deleteDXPBatchEntitiesRunnable != null) {
			_deleteDXPBatchEntitiesRunnable.run();
		}
	}

	@Scheduled(cron = _CRON_EXPRESSION_AT_MIDNIGHT_WITH_RANDOM_DELAY)
	public void runDeleteTempFilesNanite() {
		run("DeleteTempFilesNanite");
	}

	@Scheduled(fixedDelay = DateUtil.MINUTE)
	public void runSparkManagerMonitoringNanite() {
		run("SparkManagerMonitoringNanite");
	}

	public void scheduleOSBAsahTask(JSONObject osbAsahTaskJSONObject) {
		Nanite nanite = _nanitesMap.get(
			osbAsahTaskJSONObject.getString("className"));

		if (nanite == null) {
			throw new IllegalArgumentException(
				"Unable to schedule nanite with class name " +
					osbAsahTaskJSONObject.getString("className"));
		}

		String cronExpression = osbAsahTaskJSONObject.getString(
			"cronExpression");

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Scheduling task %s according CRON expression %s",
					osbAsahTaskJSONObject, cronExpression));
		}

		ScheduledFuture<?> scheduledFuture = _threadPoolTaskScheduler.schedule(
			new OSBAsahScheduledTaskRunnable(nanite, osbAsahTaskJSONObject),
			new CronTrigger(cronExpression));

		_scheduledFuturesMap.put(
			osbAsahTaskJSONObject.getString("id"), scheduledFuture);
	}

	public void unscheduleOSBAsahTask(JSONObject osbAsahTaskJSONObject) {
		ScheduledFuture<?> scheduledFuture = _scheduledFuturesMap.remove(
			osbAsahTaskJSONObject.getString("id"));

		if (scheduledFuture == null) {
			throw new IllegalArgumentException(
				"Unable to unschedule OSB Asah task " +
					osbAsahTaskJSONObject.getString("id"));
		}

		scheduledFuture.cancel(false);
	}

	private boolean _checkNanite(String className) {
		JSONObject latestRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				null, _elasticsearchInvoker, className);

		if ((latestRunLogJSONObject != null) &&
			Objects.equals(
				latestRunLogJSONObject.getString("status"), "STARTED")) {

			_log.error("Nanite is already running: " + latestRunLogJSONObject);

			return true;
		}

		return false;
	}

	@Bean(name = "deleteDXPBatchEntitiesRunnable")
	@ConditionalOnGoogleApplicationCredentials
	private Runnable _deleteDXPBatchEntitiesRunnable() {
		return () -> run("DeleteDXPBatchEntitiesNanite");
	}

	private void _executeOSBAsahTasks() {
		try {
			JSONArrayIterator.of(
				"OSBAsahTasks", _elasticsearchInvoker,
				osbAsahTaskJSONObject -> {
					executeOSBAsahTask(true, osbAsahTaskJSONObject);

					return null;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("cronExpression"))
			).iterate();
		}
		catch (Exception e) {
			_log.error("Unable to run existing tasks on startup", e);
		}
	}

	private void _scheduleOSBAsahTasks() {
		try {
			JSONArrayIterator.of(
				"OSBAsahTasks", _elasticsearchInvoker,
				osbAsahTaskJSONObject -> {
					scheduleOSBAsahTask(osbAsahTaskJSONObject);

					return null;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("cronExpression"))
			).iterate();
		}
		catch (Exception e) {
			_log.error("Unable to schedule existing tasks on startup", e);
		}
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
	@Qualifier("deleteDXPBatchEntitiesRunnable")
	private Runnable _deleteDXPBatchEntitiesRunnable;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private List<Nanite> _nanites;

	private final Map<String, Nanite> _nanitesMap = new HashMap<>();

	@Autowired
	private RunLogger _runLogger;

	private final Map<String, ScheduledFuture<?>> _scheduledFuturesMap =
		new HashMap<>();
	private final ExecutorService _threadPoolTaskExecutor =
		Executors.newFixedThreadPool(10);

	@Autowired
	private ThreadPoolTaskScheduler _threadPoolTaskScheduler;

	private final ExecutorService
		_updateDynamicMembershipsNaniteThreadPoolTaskExecutor =
			new ThreadPoolExecutor(
				1, 1, 0, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<>(100000),
				new OSBAsahRetryRejectedExecutionHandler());

	private static class OSBAsahRetryRejectedExecutionHandler
		implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(
			Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

			if (_log.isInfoEnabled()) {
				_log.info("Queue is full. Retrying in 5 seconds.");
			}

			try {
				Thread.sleep(5000);
			}
			catch (InterruptedException ie) {
				_log.error(ie, ie);
			}

			threadPoolExecutor.execute(runnable);
		}

	}

	private class NanitesRunnable implements Runnable {

		public NanitesRunnable(
			boolean force, JSONObject osbAsahTaskJSONObject) {

			_contextJSONObject = osbAsahTaskJSONObject.optJSONObject("context");
			_force = force;

			_naniteClassNames = new String[] {
				osbAsahTaskJSONObject.getString("className")
			};

			_osbAsahTaskId = osbAsahTaskJSONObject.optString("id");
		}

		public NanitesRunnable(String... naniteClassNames) {
			_contextJSONObject = null;
			_force = false;
			_naniteClassNames = naniteClassNames;
			_osbAsahTaskId = null;
		}

		@Override
		public void run() {
			for (String naniteClassName : _naniteClassNames) {
				Nanite nanite = _nanitesMap.get(naniteClassName);

				if (nanite == null) {
					_log.error(
						"Unable to get nanite with class name " +
							naniteClassName);

					continue;
				}

				if (((!_force && (nanite instanceof BaseActivitiesNanite)) ||
					 nanite.isLogRunEnabled()) &&
					_checkNanite(naniteClassName)) {

					if (_osbAsahTaskId != null) {
						_elasticsearchInvoker.delete(
							"OSBAsahTasks", _osbAsahTaskId);
					}

					continue;
				}

				long start = System.currentTimeMillis();

				try {
					nanite.logStart(_contextJSONObject);

					nanite.run(_contextJSONObject);

					nanite.logCompleted(
						_contextJSONObject, System.currentTimeMillis() - start,
						null);
				}
				catch (Exception e) {
					_log.error(e, e);

					nanite.logFailed(
						_contextJSONObject, System.currentTimeMillis() - start,
						null, e);
				}
				finally {
					if (_osbAsahTaskId != null) {
						_elasticsearchInvoker.delete(
							"OSBAsahTasks", _osbAsahTaskId);
					}
				}
			}
		}

		private final JSONObject _contextJSONObject;
		private final boolean _force;
		private final String[] _naniteClassNames;
		private final String _osbAsahTaskId;

	}

	private class OSBAsahScheduledTaskRunnable implements Runnable {

		public OSBAsahScheduledTaskRunnable(
			Nanite nanite, JSONObject osbAsahTaskJSONObject) {

			_nanite = nanite;
			_osbAsahTaskJSONObject = osbAsahTaskJSONObject;
		}

		@Override
		public void run() {
			Class<?> clazz = _nanite.getClass();

			if (_nanite.isLogRunEnabled() &&
				_checkNanite(clazz.getSimpleName())) {

				return;
			}

			String osbAsahTaskId = _osbAsahTaskJSONObject.getString("id");
			long start = System.currentTimeMillis();

			try {
				JSONObject contextJSONObject =
					_osbAsahTaskJSONObject.optJSONObject("context");

				_nanite.run(contextJSONObject);

				_nanite.logCompleted(
					contextJSONObject, System.currentTimeMillis() - start,
					osbAsahTaskId);
			}
			catch (Exception e) {
				_log.error(e, e);

				_nanite.logFailed(
					_osbAsahTaskJSONObject.optJSONObject("context"),
					System.currentTimeMillis() - start, osbAsahTaskId, e);
			}
		}

		private final Nanite _nanite;
		private final JSONObject _osbAsahTaskJSONObject;

	}

}