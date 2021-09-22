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
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.DateUtils;

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
		return DateUtil.SECOND * 5;
	}

	@Override
	public void run() {
		while (true) {
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

	private Set<Individual.LastActivityDate> _getLastActivityDates(
		Map<String, Long> channelIdsCounts, Individual individual) {

		Set<Individual.LastActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		for (String channelId : channelIdsCounts.keySet()) {
			JSONObject activityJSONObject =
				_faroInfoActivityDog.fetchLatestActivityJSONObject(
					Long.valueOf(channelId), individual.getId());

			if (activityJSONObject == null) {
				continue;
			}

			Stream<Individual.LastActivityDate> stream =
				lastActivityDates.stream();

			Individual.LastActivityDate individualLastActivityDate =
				stream.filter(
					lastActivityDate -> Objects.equals(
						Long.valueOf(channelId),
						lastActivityDate.getChannelId())
				).findFirst(
				).orElse(
					null
				);

			if (individualLastActivityDate == null) {
				individualLastActivityDate = new Individual.LastActivityDate();

				individualLastActivityDate.setChannelId(
					Long.valueOf(channelId));

				lastActivityDates.add(individualLastActivityDate);
			}

			individualLastActivityDate.setLastActivityDate(
				DateUtil.toUTCDate(activityJSONObject.getString("endTime")));
		}

		return lastActivityDates;
	}

	private Set<Individual.LastActivityDate> _getPreviousActivityDates(
		Individual individual, Map<Long, Date> oldLastActivityDatesMap) {

		Set<Individual.LastActivityDate> oldPreviousActivityDates =
			individual.getPreviousActivityDates();

		Stream<Individual.LastActivityDate> oldPreviousActivityDatesStream =
			oldPreviousActivityDates.stream();

		Map<Long, Individual.LastActivityDate> oldPreviousActivityDatesMap =
			oldPreviousActivityDatesStream.collect(
				Collectors.toMap(
					Individual.LastActivityDate::getChannelId,
					Individual.LastActivityDate::new));

		Set<Individual.LastActivityDate> previousActivityDates = new HashSet<>(
			oldPreviousActivityDatesMap.values());

		Set<Individual.LastActivityDate> newLastActivityDates =
			individual.getLastActivityDates();

		Stream<Individual.LastActivityDate> newLastActivityDatesStream =
			newLastActivityDates.stream();

		Map<Long, Date> newLastActivityDatesMap =
			newLastActivityDatesStream.collect(
				Collectors.toMap(
					Individual.LastActivityDate::getChannelId,
					Individual.LastActivityDate::getLastActivityDate));

		for (Map.Entry<Long, Date> entrySet :
				oldLastActivityDatesMap.entrySet()) {

			if ((entrySet.getValue() == null) ||
				DateUtils.isSameDay(entrySet.getValue(), DateUtil.newDate()) ||
				DateUtils.isSameInstant(
					entrySet.getValue(),
					newLastActivityDatesMap.get(entrySet.getKey()))) {

				continue;
			}

			Individual.LastActivityDate previousActivityDate =
				oldPreviousActivityDatesMap.get(entrySet.getKey());

			if (previousActivityDate == null) {
				previousActivityDates.add(
					new Individual.LastActivityDate(
						entrySet.getKey(), entrySet.getValue()));
			}
			else {
				previousActivityDate.setLastActivityDate(entrySet.getValue());
			}
		}

		return previousActivityDates;
	}

	private void _run(String projectId, List<JSONObject> messages) {
		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			Stream<JSONObject> messagesStream = messages.stream();

			Map<String, Map<String, Long>> ownerIdCounts =
				messagesStream.collect(
					Collectors.groupingBy(
						jsonObject -> String.valueOf(jsonObject.get("ownerId")),
						Collectors.groupingBy(
							jsonObject -> String.valueOf(
								jsonObject.get("channelId")),
							Collectors.counting())));

			for (Map.Entry<String, Map<String, Long>> ownerIdEntry :
					ownerIdCounts.entrySet()) {

				String ownerId = ownerIdEntry.getKey();

				Individual individual = _individualDog.fetchIndividual(
					Long.valueOf(ownerId));

				if (individual == null) {
					continue;
				}

				individual.setActivitiesCounts(
					_getActivitiesCounts(ownerIdEntry.getValue(), individual));

				Set<Individual.LastActivityDate> lastActivityDatesBeforeChange =
					individual.getLastActivityDates();

				Stream<Individual.LastActivityDate>
					lastActivityDatesBeforeChangeStream =
						lastActivityDatesBeforeChange.stream();

				Map<Long, Date> lastActivityDatesMapBeforeChange =
					lastActivityDatesBeforeChangeStream.collect(
						Collectors.toMap(
							Individual.LastActivityDate::getChannelId,
							Individual.LastActivityDate::getLastActivityDate));

				individual.setLastActivityDates(
					_getLastActivityDates(ownerIdEntry.getValue(), individual));
				individual.setPreviousActivityDates(
					_getPreviousActivityDates(
						individual, lastActivityDatesMapBeforeChange));

				_individualDog.updateIndividual(individual);
			}
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.ACTIVE_INDIVIDUAL_IDS)
	private MessageSubscriber _messageSubscriber;

}