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

package com.liferay.osb.asah.stream.curator.bot.nanite.session;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.CountdownTimer;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.arm.FinalizeUserSessionArm;

import java.time.temporal.ChronoUnit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class UserSessionFinalizerNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "user-session";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE * 5;
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		try {
			_semaphore.acquire(4);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
		finally {
			_semaphore.release(4);
		}
	}

	public void run(boolean force) throws Exception {
		AsahMarker asahMarker = _getAsahMarker();

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		String lastSuccessfulSessionFinalizerDate =
			asahMarkerContextJSONObject.optString(
				"lastSuccessfulSessionFinalizerDate", "now-30m");

		String dateString = DateUtil.newDateString();

		JSONArrayIterator.of(
			"user-sessions", _cerebroInfoElasticsearchInvoker,
			userSessionJSONObject -> {
				_finalizeUserSessionArm.updateActivitiesAndAssets(
					_objectMapper.readValue(
						userSessionJSONObject.toString(), UserSession.class));

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("completed", true)
			).filter(
				QueryBuilders.rangeQuery(
					"completeDate"
				).gt(
					lastSuccessfulSessionFinalizerDate
				)
			).filter(
				QueryBuilders.termQuery("finalized", true)
			)
		).iterate();

		CountdownTimer countdownTimer = new CountdownTimer(
			ChronoUnit.MINUTES, 5);

		while (countdownTimer.isRunning()) {
			long start = System.currentTimeMillis();

			String userSessionsJSON = _cerebroInfoElasticsearchInvoker.get(
				"user-sessions",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						_getQueryBuilder(
							dateString, force,
							lastSuccessfulSessionFinalizerDate));
					searchSourceBuilder.size(50);
				});

			UserSession[] userSessions = _objectMapper.readValue(
				userSessionsJSON, UserSession[].class);

			if (userSessions.length == 0) {
				break;
			}

			_finalizeUserSessionArm.processSessions(userSessions);

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info(
					String.format(
						"%s processed %d sessions in %d ms",
						clazz.getSimpleName(), userSessions.length,
						System.currentTimeMillis() - start));
			}
		}

		asahMarkerContextJSONObject.put(
			"lastSuccessfulSessionFinalizerDate", dateString);

		_asahMarkerDog.updateAsahMarker(
			asahMarker, WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
	}

	@PreDestroy
	private void _destroy() {
		_reentrantLock.lock();

		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
	}

	private AsahMarker _getAsahMarker() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"SessionNanite", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);

		if (asahMarker == null) {
			asahMarker = _asahMarkerDog.addAsahMarker(
				new AsahMarker("SessionNanite"),
				WeDeployDataService.OSB_ASAH_CEREBRO_INFO);

			asahMarker.setIsNew(Boolean.FALSE);
		}

		return asahMarker;
	}

	private QueryBuilder _getQueryBuilder(
		String currentDateString, boolean force,
		String lastSuccessfulSessionFinalizerDateString) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("completed", false));

		if (!force) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.rangeQuery(
						"lastEventDate"
					).lt(
						"now-30m"
					)
				).should(
					QueryBuilders.rangeQuery(
						"lastEventDate"
					).lt(
						"now/d"
					).timeZone(
						_timeZoneDog.getTimeZoneId()
					)
				));
		}

		return BoolQueryBuilderUtil.should(
			boolQueryBuilder
		).should(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("completed", true)
			).filter(
				BoolQueryBuilderUtil.should(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.existsQuery("finalized"))
				).should(
					QueryBuilders.termQuery("finalized", false)
				)
			).filter(
				QueryBuilders.rangeQuery(
					"modifiedDate"
				).gte(
					lastSuccessfulSessionFinalizerDateString
				).lt(
					currentDateString
				)
			)
		);
	}

	private void _run() throws Exception {
		for (Project project : _projectDog.getProjects()) {
			try {
				_reentrantLock.lock();

				_semaphore.acquire();

				CompletableFuture.runAsync(
					() -> {
						try {
							ProjectIdThreadLocal.setProjectId(project.getId());

							run(false);
						}
						catch (Exception exception) {
							_log.error(exception.getMessage(), exception);
						}
						finally {
							_semaphore.release();
						}
					},
					_executorService);
			}
			catch (InterruptedException interruptedException) {
				_log.error(interruptedException, interruptedException);
			}
			finally {
				_reentrantLock.unlock();
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		UserSessionFinalizerNanite.class);

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(2);

	@Autowired
	private FinalizeUserSessionArm _finalizeUserSessionArm;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private ProjectDog _projectDog;

	private final ReentrantLock _reentrantLock = new ReentrantLock();
	private final Semaphore _semaphore = new Semaphore(4, true);

	@Autowired
	private TimeZoneDog _timeZoneDog;

}