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

package com.liferay.osb.asah.stream.curator.bot.nanite.activity;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualActivityFieldsNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "individual-activity-fields";
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

	private Map<Long, Date> _convertActivityDatesSetToMap(
		Set<Individual.ActivityDate> activityDates) {

		Map<Long, Date> activityDatesMap = new HashMap<>();
		activityDates = Optional.ofNullable(
			activityDates
		).orElse(
			new HashSet<>()
		);

		if (!activityDates.isEmpty()) {
			for (Individual.ActivityDate activityDate : activityDates) {
				if ((activityDate.getChannelId() != null) &&
					(activityDate.getActivityDate() != null)) {

					activityDatesMap.put(
						activityDate.getChannelId(),
						activityDate.getActivityDate());
				}
			}
		}

		return activityDatesMap;
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

	private Set<Individual.ActivitiesCount> _getActivitiesCounts(
		Map<String, Long> channelIdsCounts, Individual individual) {

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		for (Map.Entry<String, Long> channelIdEntry :
				channelIdsCounts.entrySet()) {

			String channelId = channelIdEntry.getKey();
			long count = channelIdEntry.getValue();

			Stream<Individual.ActivitiesCount> stream =
				activitiesCounts.stream();

			Individual.ActivitiesCount individualActivitiesCount =
				stream.filter(
					activitiesCount -> Objects.equals(
						Long.valueOf(channelId), activitiesCount.getChannelId())
				).findFirst(
				).orElse(
					null
				);

			if (individualActivitiesCount == null) {
				individualActivitiesCount = new Individual.ActivitiesCount();

				individualActivitiesCount.setChannelId(Long.valueOf(channelId));

				activitiesCounts.add(individualActivitiesCount);
			}

			if (Objects.isNull(
					individualActivitiesCount.getActivitiesCount())) {

				individualActivitiesCount.setActivitiesCount(0L);
			}

			count += individualActivitiesCount.getActivitiesCount();

			individualActivitiesCount.setActivitiesCount(count);
		}

		return activitiesCounts;
	}

	private Set<Individual.ActivityDate> _getLastActivityDates(
		Map<String, Long> channelIdsCounts, Individual individual) {

		Set<Individual.ActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		for (String channelId : channelIdsCounts.keySet()) {
			JSONObject activityJSONObject =
				_faroInfoActivityDog.fetchLatestActivityJSONObject(
					Long.valueOf(channelId), individual.getId());

			if (activityJSONObject == null) {
				continue;
			}

			Stream<Individual.ActivityDate> stream = lastActivityDates.stream();

			Individual.ActivityDate individualLastActivityDate = stream.filter(
				lastActivityDate -> Objects.equals(
					Long.valueOf(channelId), lastActivityDate.getChannelId())
			).findFirst(
			).orElse(
				null
			);

			if (individualLastActivityDate == null) {
				individualLastActivityDate = new Individual.ActivityDate();

				individualLastActivityDate.setChannelId(
					Long.valueOf(channelId));

				lastActivityDates.add(individualLastActivityDate);
			}

			individualLastActivityDate.setActivityDate(
				DateUtil.toUTCDate(activityJSONObject.getString("endTime")));
		}

		return lastActivityDates;
	}

	private Set<Individual.ActivityDate> _getPreviousActivityDates(
		Individual individual, Map<Long, Date> oldLastActivityDatesMap) {

		Map<Long, Individual.ActivityDate> previousActivityDatesMap =
			new HashMap<>();
		Set<Individual.ActivityDate> previousActivityDates =
			Optional.ofNullable(
				individual.getPreviousActivityDates()
			).orElse(
				new HashSet<>()
			);

		if (!previousActivityDates.isEmpty()) {
			for (Individual.ActivityDate previousActivityDate :
					previousActivityDates) {

				if ((previousActivityDate.getChannelId() != null) &&
					(previousActivityDate.getActivityDate() != null)) {

					previousActivityDatesMap.put(
						previousActivityDate.getChannelId(),
						previousActivityDate);
				}
			}
		}

		previousActivityDates = new HashSet<>(
			previousActivityDatesMap.values());
		Map<Long, Date> lastActivityDatesMap = _convertActivityDatesSetToMap(
			individual.getLastActivityDates());

		for (Map.Entry<Long, Date> entrySet :
				oldLastActivityDatesMap.entrySet()) {

			if (DateUtils.isSameDay(entrySet.getValue(), DateUtil.newDate()) ||
				DateUtils.isSameInstant(
					entrySet.getValue(),
					lastActivityDatesMap.get(entrySet.getKey()))) {

				continue;
			}

			Individual.ActivityDate previousActivityDate =
				previousActivityDatesMap.get(entrySet.getKey());

			if (previousActivityDate == null) {
				previousActivityDates.add(
					new Individual.ActivityDate(
						entrySet.getValue(), entrySet.getKey()));
			}
			else {
				previousActivityDate.setActivityDate(entrySet.getValue());
			}
		}

		return previousActivityDates;
	}

	private void _run() {
		while (true) {
			try {
				_reentrantLock.lock();

				long start = System.currentTimeMillis();

				List<String> messages = _messageSubscriber.pullMessages(100);

				if (messages.isEmpty()) {
					break;
				}

				Stream<String> stream = messages.stream();

				stream.map(
					JSONObject::new
				).collect(
					Collectors.groupingBy(
						jsonObject -> jsonObject.getString("projectId"))
				).forEach(
					this::_run
				);

				if (_log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					_log.info(
						String.format(
							"%s dispatched %d messages in %d ms",
							clazz.getSimpleName(), messages.size(),
							System.currentTimeMillis() - start));
				}
			}
			finally {
				_reentrantLock.unlock();
			}
		}
	}

	private void _run(String projectId, List<JSONObject> messages) {
		try {
			_semaphore.acquire();

			CompletableFuture.runAsync(
				() -> {
					long start = System.currentTimeMillis();

					try {
						ProjectIdThreadLocal.setProjectId(projectId);

						Stream<JSONObject> messagesStream = messages.stream();

						Map<String, Map<String, Long>> ownerIdCounts =
							messagesStream.collect(
								Collectors.groupingBy(
									jsonObject -> String.valueOf(
										jsonObject.get("ownerId")),
									Collectors.groupingBy(
										jsonObject -> String.valueOf(
											jsonObject.get("channelId")),
										Collectors.counting())));

						for (Map.Entry<String, Map<String, Long>> ownerIdEntry :
								ownerIdCounts.entrySet()) {

							String ownerId = ownerIdEntry.getKey();

							ReentrantLock reentrantLock =
								KeyReentrantLock.getReentrantLock(
									getClass(), ownerId);

							try {
								reentrantLock.lock();

								Individual individual =
									_individualDog.fetchIndividual(
										Long.valueOf(ownerId));

								if (individual == null) {
									return;
								}

								individual.setActivitiesCounts(
									_getActivitiesCounts(
										ownerIdEntry.getValue(), individual));

								Map<Long, Date> lastActivityDatesMap =
									_convertActivityDatesSetToMap(
										individual.getLastActivityDates());

								individual.setLastActivityDates(
									_getLastActivityDates(
										ownerIdEntry.getValue(), individual));

								if (!lastActivityDatesMap.isEmpty()) {
									individual.setPreviousActivityDates(
										_getPreviousActivityDates(
											individual, lastActivityDatesMap));
								}

								_individualDog.updateIndividual(individual);
							}
							finally {
								reentrantLock.unlock();
							}
						}
					}
					catch (Exception exception) {
						_log.error(exception.getMessage(), exception);
					}
					finally {
						_semaphore.release();
					}

					if (_log.isInfoEnabled()) {
						Class<?> clazz = getClass();

						_log.info(
							String.format(
								"%s processed %d messages in %d ms",
								clazz.getSimpleName(), messages.size(),
								System.currentTimeMillis() - start));
					}
				},
				_executorService);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
	}

	private static final Log _log = LogFactory.getLog(
		IndividualActivityFieldsNanite.class);

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(2);

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.ACTIVE_INDIVIDUAL_IDS)
	private MessageSubscriber _messageSubscriber;

	private final ReentrantLock _reentrantLock = new ReentrantLock();
	private final Semaphore _semaphore = new Semaphore(4, true);

}