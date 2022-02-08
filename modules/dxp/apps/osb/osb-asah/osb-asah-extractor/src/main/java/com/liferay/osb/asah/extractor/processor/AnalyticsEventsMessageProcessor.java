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

package com.liferay.osb.asah.extractor.processor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.AnalyticsEventsMessage;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.util.AnalyticsEventUtil;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.extractor.browscap.BrowscapDevice;
import com.liferay.osb.asah.extractor.browscap.BrowscapEngine;
import com.liferay.osb.asah.extractor.ip.geocoder.IPGeocoder;
import com.liferay.osb.asah.extractor.ip.geocoder.IPInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 * @author Eddie Olson
 * @author Marcellus Tavares
 */
@Component
public class AnalyticsEventsMessageProcessor {

	public void processQueuedMessages() throws Exception {
		try {
			_processQueuedMessages();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_boundedExecutor.awaitPendingTasks();
	}

	private Individual _addIndividual(
		AnalyticsEventsMessage analyticsEventsMessage, Long channelId,
		Long dataSourceId) {

		String userId = analyticsEventsMessage.getUserId();

		Individual individual = _individualDog.fetchIndividual(
			dataSourceId, userId);

		if (individual == null) {
			individual = _individualDog.addIndividual(
				channelId, dataSourceId, null, userId);
		}
		else {
			Set<Long> channelIds = individual.getChannelIds();

			if (CollectionUtils.isEmpty(channelIds)) {
				channelIds = new HashSet<>();
			}

			if (channelIds.add(channelId)) {
				individual.setChannelIds(channelIds);

				individual = _individualDog.updateIndividual(individual);
			}
		}

		return individual;
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
	}

	private Map<String, String> _getContext(
		AnalyticsEventsMessage analyticsEventsMessage) {

		Map<String, String> context = new HashMap<>();

		Map<String, String> analyticsEventsMessageContext =
			analyticsEventsMessage.getContext();

		for (Map.Entry<String, String> entry :
				analyticsEventsMessageContext.entrySet()) {

			context.put(entry.getKey(), entry.getValue());
		}

		BrowscapDevice browscapDevice = _browscapEngine.getDevice(
			MapUtil.getString(context, "userAgent"));

		if (browscapDevice != null) {
			Map<String, String> convertedValues = _objectMapper.convertValue(
				browscapDevice, Map.class);

			context.putAll(convertedValues);
		}

		IPInfo ipInfo = _ipGeocoder.getIPInfo(
			analyticsEventsMessage.getClientIP());

		if (ipInfo != null) {
			Map<String, String> convertedValues = _objectMapper.convertValue(
				ipInfo, Map.class);

			context.putAll(convertedValues);
		}

		if (StringUtils.isBlank(MapUtil.getString(context, "canonicalUrl"))) {
			context.put("canonicalUrl", MapUtil.getString(context, "url"));
		}

		String screenHeight = MapUtil.getString(context, "screenHeight");

		if (screenHeight != null) {
			context.put("screenHeightSize", screenHeight);
		}

		String screenWidth = MapUtil.getString(context, "screenWidth");

		if (screenWidth != null) {
			context.put("screenWidthSize", screenWidth);
		}

		return context;
	}

	private Map<String, String> _getSafeEventProperties(
		Map<String, String> eventProperties) {

		Map<String, String> safeEventProperties = new HashMap<>();

		for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
			safeEventProperties.put(
				entry.getKey(), StringUtil.get(entry.getValue()));
		}

		return safeEventProperties;
	}

	private Set<String> _getSegmentNames(
		Long channelId, Individual individual) {

		return new HashSet<>(
			_segmentDog.getSegmentNames(channelId, individual.getSegmentIds()));
	}

	private boolean _isCrawler(Map<String, String> context) {
		if (Boolean.parseBoolean(context.getOrDefault("crawler", null))) {
			return true;
		}

		return false;
	}

	private boolean _isDataSourceActive(
		AnalyticsEventsMessage analyticsEventsMessage) {

		DataSource dataSource = _dataSourceDog.fetchDataSource(
			Long.valueOf(analyticsEventsMessage.getDataSourceId()));

		if ((dataSource != null) &&
			Objects.equals(dataSource.getStatus(), "ACTIVE")) {

			return true;
		}

		return false;
	}

	private boolean _isDuplicate(AnalyticsEvent analyticsEvent) {
		Date date = DateUtil.newDate();

		long deltaMilliseconds = DateUtil.getDeltaMilliseconds(
			analyticsEvent.getEventDate(), date);

		if (deltaMilliseconds <= (DateUtil.DAY * 7)) {
			return false;
		}

		if (_eventDog.existsEvent(analyticsEvent.getId())) {
			return true;
		}

		return false;
	}

	private boolean _isKnownIndividual(Individual individual) {
		if (_fieldRepository.existsByNameAndOwnerId(
				"email", individual.getId())) {

			return true;
		}

		return false;
	}

	private boolean _isValidURL(Map<String, String> context) {
		String url = context.get("url");

		if ((url == null) || url.startsWith("file://")) {
			return false;
		}

		return true;
	}

	private void _processMessage(AnalyticsEventsMessage analyticsEventsMessage)
		throws Exception {

		if (!_isDataSourceActive(analyticsEventsMessage)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Discarding message because data source is not active: " +
						analyticsEventsMessage.toJSON());
			}

			return;
		}

		Map<String, String> context = _getContext(analyticsEventsMessage);

		if (_isCrawler(context)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Discarding message from crawler: " +
						analyticsEventsMessage.toJSON());
			}

			return;
		}

		if (!_isValidURL(context)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Discarding message from invalid host: " +
						analyticsEventsMessage.toJSON());
			}

			return;
		}

		String channelId = analyticsEventsMessage.getChannelId();
		String dataSourceId = analyticsEventsMessage.getDataSourceId();

		if (StringUtil.isNull(channelId) || StringUtils.isBlank(channelId) ||
			!NumberUtils.isCreatable(channelId)) {

			channelId = String.valueOf(
				_dataSourceDog.getDefaultChannelId(Long.valueOf(dataSourceId)));

			if (StringUtil.isNull(channelId) ||
				StringUtils.isBlank(channelId)) {

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Discarding message because channel ID is invalid: " +
							analyticsEventsMessage.toJSON());
				}

				return;
			}
		}

		Set<AnalyticsEvent> analyticsEvents = new TreeSet<>(
			Comparator.comparing(AnalyticsEvent::getId));

		Individual individual = _addIndividual(
			analyticsEventsMessage, Long.valueOf(channelId),
			Long.valueOf(dataSourceId));

		boolean knownIndividual = _isKnownIndividual(individual);

		String projectTimeZoneId = _timeZoneDog.getTimeZoneId();
		Set<String> segmentNames = _getSegmentNames(
			Long.valueOf(channelId), individual);

		for (AnalyticsEventsMessage.Event event :
				analyticsEventsMessage.getEvents()) {

			AnalyticsEvent analyticsEvent = new AnalyticsEvent();

			analyticsEvent.setApplicationId(event.getApplicationId());
			analyticsEvent.setChannelId(channelId);
			analyticsEvent.setContext(context);
			analyticsEvent.setCreateDate(
				analyticsEventsMessage.getCreateDate());
			analyticsEvent.setDataSourceId(dataSourceId);
			analyticsEvent.setEventDate(event.getEventDate());
			analyticsEvent.setEventId(event.getEventId());
			analyticsEvent.setEventProperties(
				_getSafeEventProperties(event.getProperties()));

			String id = event.getId();

			if (id == null) {
				id = AnalyticsEventUtil.generateAnalyticsEventId(
					dataSourceId, event, analyticsEventsMessage.getProjectId(),
					analyticsEventsMessage.getUserId());
			}

			analyticsEvent.setId(id);
			analyticsEvent.setIndividualId(String.valueOf(individual.getId()));
			analyticsEvent.setKnownIndividual(knownIndividual);
			analyticsEvent.setProjectId(analyticsEventsMessage.getProjectId());
			analyticsEvent.setProjectTimeZoneId(projectTimeZoneId);
			analyticsEvent.setSegmentNames(segmentNames);
			analyticsEvent.setUserId(analyticsEventsMessage.getUserId());

			if (_isDuplicate(analyticsEvent)) {
				if (_log.isDebugEnabled()) {
					_log.debug("Discarding duplicate event: " + id);
				}

				continue;
			}

			analyticsEvents.add(analyticsEvent);
		}

		for (AnalyticsEvent analyticsEvent : analyticsEvents) {
			_sendAnalyticsEvent(analyticsEvent);
		}
	}

	private void _processQueuedMessages() throws Exception {
		while (true) {
			long start = System.currentTimeMillis();

			List<Message<AnalyticsEventsMessage>> messages =
				_messageSubscriber.pullMessages(
					_analyticsEventsMessageProcessorPullMessagesSize,
					AnalyticsEventsMessage::toAnalyticsEventsMessage);

			if (messages.isEmpty()) {
				break;
			}

			Stream<Message<AnalyticsEventsMessage>> stream = messages.stream();

			stream.collect(
				Collectors.groupingBy(
					message -> {
						AnalyticsEventsMessage analyticsEventsMessage =
							message.getObject();

						return Tuples.of(
							analyticsEventsMessage.getProjectId(),
							analyticsEventsMessage.getUserId());
					})
			).forEach(
				(tuple2, messagesList) -> _processQueuedMessagesAsync(
					messagesList, tuple2)
			);

			if (_log.isDebugEnabled()) {
				Class<?> clazz = getClass();

				_log.debug(
					String.format(
						"%s dispatched %d analytics events messages in %d ms",
						clazz.getSimpleName(), messages.size(),
						System.currentTimeMillis() - start));
			}
		}
	}

	private void _processQueuedMessagesAsync(
		List<Message<AnalyticsEventsMessage>> messages,
		Tuple2<String, String> tuple2) {

		_boundedExecutor.runAsync(
			() -> {
				List<String> ackIds = new ArrayList<>();

				long start = System.currentTimeMillis();

				ProjectIdThreadLocal.setProjectId(tuple2.getT1());

				for (Message<AnalyticsEventsMessage> message : messages) {
					try {
						_processMessage(message.getObject());

						ackIds.add(message.getAckId());
					}
					catch (Exception exception) {
						_messageSubscriber.registerException(
							exception, message);

						AnalyticsEventsMessage analyticsEventsMessage =
							message.getObject();

						_log.error(
							"Unable to process analytics events message " +
								analyticsEventsMessage.toJSON(),
							exception);
					}
				}

				_messageSubscriber.sendAckIds(ackIds);

				if (_log.isDebugEnabled()) {
					Class<?> clazz = getClass();

					_log.debug(
						String.format(
							"%s processed %d analytics events messages in %d " +
								"ms",
							clazz.getSimpleName(), messages.size(),
							System.currentTimeMillis() - start));
				}
			},
			KeyReentrantLock.getReentrantLock(getClass(), tuple2));
	}

	private void _sendAnalyticsEvent(AnalyticsEvent analyticsEvent) {
		String analyticsEventJSON = analyticsEvent.toJSON();

		Map<String, String> messageAttributes = new HashMap<>();

		messageAttributes.put(
			"eventDate", DateUtil.toUTCString(analyticsEvent.getEventDate()));

		for (Channel channel :
				_analyticsEventsChannels.getChannels(analyticsEvent)) {

			_messageBus.sendMessage(
				channel, analyticsEventJSON, messageAttributes);
		}
	}

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventsMessageProcessor.class);

	private static final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};

	@Autowired
	private AnalyticsEventsChannels _analyticsEventsChannels;

	@Value(
		"${osb.asah.analytics.events.message.processor.pull.messages.size:50}"
	)
	private int _analyticsEventsMessageProcessorPullMessagesSize;

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@Autowired
	private BrowscapEngine _browscapEngine;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private EventDog _eventDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private IPGeocoder _ipGeocoder;

	@Autowired
	private MessageBus _messageBus;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_MESSAGE)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}