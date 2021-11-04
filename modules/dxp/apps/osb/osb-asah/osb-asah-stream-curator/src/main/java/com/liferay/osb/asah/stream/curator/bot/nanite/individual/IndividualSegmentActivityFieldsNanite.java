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

package com.liferay.osb.asah.stream.curator.bot.nanite.individual;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualSegmentActivityFieldsNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "individual-segments";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
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

	private long _getActivitiesCount(
		Long channelId, boolean includeAnonymousUsers, Long segmentId) {

		List<Individual.ActivitiesCount> activitiesCounts =
			_individualDog.getActivitiesCounts(
				includeAnonymousUsers, segmentId);

		Stream<Individual.ActivitiesCount> stream = activitiesCounts.stream();

		Individual.ActivitiesCount individualActivitiesCount = stream.filter(
			activitiesCount -> Objects.equals(
				channelId, activitiesCount.getChannelId())
		).findFirst(
		).orElse(
			null
		);

		if ((individualActivitiesCount == null) ||
			(individualActivitiesCount.getActivitiesCount() == null)) {

			return 0;
		}

		return individualActivitiesCount.getActivitiesCount();
	}

	private Date _getLastActivityDate(
		Long channelId, boolean includeAnonymousUsers, Long segmentId) {

		List<Individual> searchIndividuals = _individualDog.searchIndividuals(
			channelId, includeAnonymousUsers, segmentId, 0, 1,
			new String[] {"lastActivityDate", "desc"});

		if (searchIndividuals.isEmpty()) {
			return null;
		}

		Individual individual = searchIndividuals.get(0);

		for (Individual.ActivityDate lastActivityDate :
				individual.getLastActivityDates()) {

			if (Objects.equals(lastActivityDate.getChannelId(), channelId)) {
				return lastActivityDate.getActivityDate();
			}
		}

		return null;
	}

	private boolean _isAnalyticsConfigured() {
		return _analyticsConfigured.getOrDefault(
			ProjectIdThreadLocal.getProjectId(), false);
	}

	private void _process(Segment segment) {
		if (segment.getActivitiesCount() == null) {
			segment.setActivitiesCount(0L);

			segment = _segmentDog.updateSegment(segment, segment.getId());
		}

		boolean includeAnonymousUsers = BooleanUtils.toBoolean(
			segment.getIncludeAnonymousUsers());

		long activitiesCount = _getActivitiesCount(
			segment.getChannelId(), includeAnonymousUsers, segment.getId());
		Date lastActivityDate = _getLastActivityDate(
			segment.getChannelId(), includeAnonymousUsers, segment.getId());

		if ((activitiesCount == segment.getActivitiesCount()) &&
			Objects.nonNull(segment.getLastActivityDate()) &&
			Objects.equals(lastActivityDate, segment.getLastActivityDate())) {

			return;
		}

		segment.setActivitiesCount(activitiesCount);

		if (Objects.nonNull(lastActivityDate)) {
			segment.setLastActivityDate(lastActivityDate);
		}

		_segmentDog.replaceSegment(segment);
	}

	private void _run() {
		for (Project project : _projectDog.getProjects()) {
			try {
				_reentrantLock.lock();

				_semaphore.acquire();

				CompletableFuture.runAsync(
					() -> {
						try {
							ProjectIdThreadLocal.setProjectId(project.getId());

							if (!_dataSourceDog.isAnalyticsConfigured()) {
								if (_isAnalyticsConfigured()) {
									_segmentDog.updateSegments(0L);
								}

								_setAnalyticsConfigured(false);

								return;
							}

							_setAnalyticsConfigured(true);

							int page = 0;

							List<Segment> segments = _segmentDog.getSegments(
								"Account: ", page, 500);

							while (!segments.isEmpty()) {
								for (Segment segment : segments) {
									try {
										_process(segment);
									}
									catch (Exception exception) {
										_log.error(exception, exception);
									}
								}

								segments = _segmentDog.getSegments(
									"Account: ", ++page, 500);
							}
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

	private void _setAnalyticsConfigured(boolean analyticsConfigured) {
		_analyticsConfigured.put(
			ProjectIdThreadLocal.getProjectId(), analyticsConfigured);
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentActivityFieldsNanite.class);

	private final Map<String, Boolean> _analyticsConfigured = new HashMap<>();

	@Autowired
	private DataSourceDog _dataSourceDog;

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(2);

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ProjectDog _projectDog;

	private final ReentrantLock _reentrantLock = new ReentrantLock();

	@Autowired
	private SegmentDog _segmentDog;

	private final Semaphore _semaphore = new Semaphore(4, true);

}