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

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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
		try {
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_boundedExecutor.awaitPendingTasks();
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
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

	private void _run() {
		try {
			while (true) {
				long start = System.currentTimeMillis();

				List<Message<JSONObject>> messages =
					_messageSubscriber.pullMessages(
						_individualActivityFieldsNanitePullMessagesSize,
						JSONObject::new);

				if (messages.isEmpty()) {
					break;
				}

				Stream<Message<JSONObject>> stream = messages.stream();

				stream.collect(
					Collectors.groupingBy(
						message -> {
							JSONObject jsonObject = message.getObject();

							return Tuples.of(
								jsonObject.getString("projectId"),
								String.valueOf(jsonObject.get("ownerId")));
						})
				).forEach(
					this::_runAsync
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
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _runAsync(
		Tuple2<String, String> tuple2, List<Message<JSONObject>> messages) {

		_boundedExecutor.runAsync(
			() -> {
				try {
					long start = System.currentTimeMillis();

					ProjectIdThreadLocal.setProjectId(tuple2.getT1());

					Individual individual = _individualDog.fetchIndividual(
						Long.valueOf(tuple2.getT2()));

					if (individual != null) {
						Stream<Message<JSONObject>> messagesStream =
							messages.stream();

						individual.setActivitiesCounts(
							_getActivitiesCounts(
								messagesStream.map(
									Message::getObject
								).collect(
									Collectors.groupingBy(
										jsonObject -> String.valueOf(
											jsonObject.get("channelId")),
										Collectors.counting())
								),
								individual));

						// TODO Set Individual last activity dates

						_individualDog.updateIndividual(individual);
					}

					_messageSubscriber.sendAckIds(
						ListUtil.map(messages, Message::getAckId));

					if (_log.isDebugEnabled()) {
						Class<?> clazz = getClass();

						_log.debug(
							String.format(
								"%s processed %d messages in %d ms",
								clazz.getSimpleName(), messages.size(),
								System.currentTimeMillis() - start));
					}
				}
				catch (Exception exception) {
					messages.forEach(
						message -> _messageSubscriber.registerException(
							exception, message));

					_log.error(
						"Unable to process messages " +
							ListUtil.map(messages, Message::getObject),
						exception);
				}
			},
			KeyReentrantLock.getReentrantLock(getClass(), tuple2));
	}

	private static final Log _log = LogFactory.getLog(
		IndividualActivityFieldsNanite.class);

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@Value(
		"${osb.asah.individual.activity.fields.nanite.pull.messages.size:100}"
	)
	private int _individualActivityFieldsNanitePullMessagesSize;

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.ACTIVE_INDIVIDUAL_IDS)
	private MessageSubscriber _messageSubscriber;

}