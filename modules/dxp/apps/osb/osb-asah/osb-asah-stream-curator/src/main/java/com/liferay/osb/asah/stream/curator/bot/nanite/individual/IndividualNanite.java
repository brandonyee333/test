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

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 * @author André Miranda
 */
@Component
public class IndividualNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "individuals";
	}

	@Override
	public long getInterval() {
		return DateUtil.SECOND;
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

	@PostConstruct
	private void _init() {

		// TODO Get collections

	}

	private void _run() throws Exception {
		while (true) {
			long start = System.currentTimeMillis();

			List<Message<JSONObject>> messages =
				_messageSubscriber.pullMessages(
					_individualNanitePullMessagesSize, JSONObject::new);

			if (messages.isEmpty()) {
				return;
			}

			Stream<Message<JSONObject>> stream = messages.stream();

			stream.collect(
				Collectors.groupingBy(
					message -> {
						JSONObject jsonObject = message.getObject();

						return Tuples.of(
							jsonObject.getString("projectId"),
							jsonObject.getString("userId"));
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

	private void _runAsync(
		Tuple2<String, String> tuple2, List<Message<JSONObject>> messages) {

		_boundedExecutor.runAsync(
			() -> {
				List<String> ackIds = new ArrayList<>();

				try {
					long start = System.currentTimeMillis();

					ProjectIdThreadLocal.setProjectId(tuple2.getT1());

					for (Message<JSONObject> message : messages) {
						try {
							JSONObject messageJSONObject = message.getObject();

							String emailAddressHashed =
								messageJSONObject.getString(
									"emailAddressHashed");

							if ((emailAddressHashed == null) ||
								MessageDigest.isEqual(
									emailAddressHashed.getBytes(
										StandardCharsets.UTF_8),
									_BLANK_EMAIL_HASH.getBytes(
										StandardCharsets.UTF_8))) {

								continue;
							}

							if (!_suppressionDog.isSuppressed(
									null, emailAddressHashed)) {

								_updatePagesAndAssets();

								_updateUserSessions();
							}

							ackIds.add(message.getAckId());
						}
						catch (Exception exception) {
							_messageSubscriber.registerException(
								exception, message);

							_log.error(
								"Unable to process message " +
									message.getObject(),
								exception);
						}
					}

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
					Stream<Message<JSONObject>> stream = messages.stream();

					List<String> messagesString = stream.filter(
						message -> !ackIds.contains(message.getAckId())
					).map(
						message -> {
							_messageSubscriber.registerException(
								exception, message);

							JSONObject messageJSONObject = message.getObject();

							return messageJSONObject.toString();
						}
					).collect(
						Collectors.toList()
					);

					_log.error(
						"Unable to process messages " + messagesString,
						exception);
				}
				finally {
					_messageSubscriber.sendAckIds(ackIds);
				}
			},
			KeyReentrantLock.getReentrantLock(getClass(), tuple2));
	}

	private void _updatePagesAndAssets() {

		// TODO Update pages and assets

	}

	private void _updateUserSessions() {

		// Update user sessions

	}

	private static final String _BLANK_EMAIL_HASH = DigestUtils.sha256Hex("");

	private static final Log _log = LogFactory.getLog(IndividualNanite.class);

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@Value("${osb.asah.individual.nanite.pull.messages.size:50}")
	private int _individualNanitePullMessagesSize;

	@MessageSubscriber.Autowired(channel = Channel.IDENTITY_MESSAGE)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private SuppressionDog _suppressionDog;

}