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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.run.logger.RunLogger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.CacheEvict;
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
public class OSBAsahBatchCuratorBot {

	@CacheEvict(
		allEntries = true,
		value = {
			"getActivityTransformations", "getGraphQLExecutionResult",
			"getMembershipChangeTransformations"
		}
	)
	@Scheduled(cron = "0 0 0 * * ?")
	public void clearCache() {
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void curateEngagements() {
		run("AssetEngagementScoresNanite");

		run(
			"IndividualEngagementScoresNanite",
			"IndividualSegmentEngagementScoresNanite",
			"AccountEngagementScoresNanite");
	}

	@Scheduled(cron = "0 0 */6 * * ?")
	public void curateExperiments() {
		run("ExperimentNanite");
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void curateInterests() {
		run("InterestThresholdScoreNanite");

		run("InterestTopicsNanite");

		run("IndividualInterestScoresNanite");
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void curateStaleDynamicIndividualSegments() {
		run("StaleDynamicIndividualSegmentsNanite");
	}

	public void executeOSBAsahTask(
		boolean force, JSONObject osbAsahTaskJSONObject) {

		String className = osbAsahTaskJSONObject.getString("className");

		if (className.equals("UpdateDynamicMembershipsNanite")) {
			_updateDynamicMembershipsNaniteThreadPoolTaskExecutor.execute(
				new OSBAsahTaskRunnable(false, osbAsahTaskJSONObject));
		}
		else {
			_threadPoolTaskExecutor.execute(
				new OSBAsahTaskRunnable(force, osbAsahTaskJSONObject));
		}
	}

	@PostConstruct
	public void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();

		for (Nanite nanite : _nanites) {
			Class<?> clazz = nanite.getClass();

			_nanitesMap.put(clazz.getSimpleName(), nanite);
		}

		BaseActivitiesNanite.setAnalyticsConfigured(
			_faroInfoDataSourceDog.isAnalyticsConfigured());
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		_elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("status", "STARTED"), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.status = 'INTERRUPTED'", Collections.emptyMap()),
			"run-logs");

		run("AssetEngagementScoresNanite");

		run("DataControlNanite");

		run("DataExportNanite");

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

		run("ExperimentNanite");

		_executeOSBAsahTasks();

		_scheduleOSBAsahTasks();
	}

	public void run(String... naniteClassNames) {
		_threadPoolTaskExecutor.execute(new NanitesRunnable(naniteClassNames));
	}

	@Scheduled(cron = "0 */5 * * * ?")
	public void runDataControlTasks() {
		run("DataControlNanite");
	}

	@Scheduled(cron = "0 */5 * * * ?")
	public void runDataExportTasks() {
		run("DataExportNanite");
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void runDataRetentionNanite() {
		run("DataRetentionNanite");
	}

	@Scheduled(cron = "0 0 0 * * ?")
	public void runDeleteTempFilesNanite() {
		run("DeleteTempFilesNanite");
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

		_threadPoolTaskScheduler.schedule(
			new OSBAsahScheduledTaskRunnable(nanite, osbAsahTaskJSONObject),
			new CronTrigger(cronExpression));
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
				BoolQueryBuilderUtil.must(
					QueryBuilders.existsQuery("cronExpression"))
			).iterate();
		}
		catch (Exception e) {
			_log.error("Unable to schedule existing tasks on startup", e);
		}
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahBatchCuratorBot.class);

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private List<Nanite> _nanites;

	private final Map<String, Nanite> _nanitesMap = new HashMap<>();

	@Autowired
	private RunLogger _runLogger;

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

		public NanitesRunnable(String... naniteClassNames) {
			_naniteClassNames = naniteClassNames;
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

				if (_checkNanite(naniteClassName)) {
					continue;
				}

				_runLogger.log(null, nanite, "STARTED", _elasticsearchInvoker);

				try {
					nanite.run(null);

					_runLogger.log(
						null, nanite, "COMPLETED", _elasticsearchInvoker);
				}
				catch (Exception e) {
					_log.error(e, e);

					_runLogger.log(
						null, nanite, "FAILED", _elasticsearchInvoker);
				}
			}
		}

		private final String[] _naniteClassNames;

	}

	private class OSBAsahScheduledTaskRunnable implements Runnable {

		public OSBAsahScheduledTaskRunnable(
			Nanite nanite, JSONObject osbAsahTaskJSONObject) {

			_nanite = nanite;
			_osbAsahTaskJSONObject = osbAsahTaskJSONObject;
		}

		@Override
		public void run() {
			try {
				_nanite.run(_osbAsahTaskJSONObject.optJSONObject("context"));

				_logCompleted();
			}
			catch (Exception e) {
				_log.error(e, e);

				_logFailed(e);
			}
		}

		private void _logCompleted() {
			_runLogger.log(
				null, _nanite, false, "COMPLETED", _elasticsearchInvoker,
				"OSBAsahTaskId", _osbAsahTaskJSONObject.getString("id"));
		}

		private void _logFailed(Throwable throwable) {
			_runLogger.log(
				null, _nanite, false, "FAILED", _elasticsearchInvoker,
				"OSBAsahTaskId", _osbAsahTaskJSONObject.getString("id"),
				"failureReason", ExceptionUtils.getStackTrace(throwable));
		}

		private final Nanite _nanite;
		private final JSONObject _osbAsahTaskJSONObject;

	}

	private class OSBAsahTaskRunnable implements Runnable {

		public OSBAsahTaskRunnable(
			boolean force, JSONObject osbAsahTaskJSONObject) {

			_force = force;
			_osbAsahTaskJSONObject = osbAsahTaskJSONObject;
		}

		@Override
		public void run() {
			Nanite nanite = _nanitesMap.get(
				_osbAsahTaskJSONObject.getString("className"));

			if (nanite == null) {
				_log.error(
					"Unable to get nanite with class name " +
						_osbAsahTaskJSONObject.getString("className"));

				return;
			}

			if (nanite instanceof BaseActivitiesNanite) {
				if (!_force &&
					_checkNanite(
						_osbAsahTaskJSONObject.getString("className"))) {

					return;
				}

				_runLogger.log(null, nanite, "STARTED", _elasticsearchInvoker);
			}

			try {
				nanite.run(_osbAsahTaskJSONObject.optJSONObject("context"));

				_runLogger.log(
					null, nanite, "COMPLETED", _elasticsearchInvoker);

				_elasticsearchInvoker.delete(
					"OSBAsahTasks", _osbAsahTaskJSONObject);
			}
			catch (Exception e) {
				_log.error(e, e);

				_runLogger.log(null, nanite, "FAILED", _elasticsearchInvoker);
			}
		}

		private final boolean _force;
		private final JSONObject _osbAsahTaskJSONObject;

	}

}