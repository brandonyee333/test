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

import com.liferay.osb.asah.common.dog.AnalyticsEventStorageDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.AnalyticsEventsMessage;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.storage.Storage;
import com.liferay.osb.asah.common.storage.StorageConfiguration;
import com.liferay.osb.asah.common.storage.StorageFactory;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.extractor.fiftyonedegrees.FiftyOneDegreesDevice;
import com.liferay.osb.asah.extractor.fiftyonedegrees.FiftyOneDegreesEngine;
import com.liferay.osb.asah.extractor.ip.geocoder.IPGeocoder;
import com.liferay.osb.asah.extractor.ip.geocoder.IPInfo;

import io.prometheus.client.Counter;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.avro.Schema;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Eddie Olson
 * @author Marcellus Tavares
 */
@Component
public class AnalyticsEventsMessageProcessor {

	public void processQueuedMessages() throws Exception {
		while (true) {
			List<AnalyticsEventsMessage> analyticsEventsMessages =
				_messageSubscriber.pullMessages(
					50, AnalyticsEventsMessage::toAnalyticsEventsMessage);

			if (analyticsEventsMessages.isEmpty()) {
				break;
			}

			for (AnalyticsEventsMessage analyticsEventsMessage :
					analyticsEventsMessages) {

				ProjectIdThreadLocal.forProject(
					analyticsEventsMessage.getProjectId(),
					() -> _processMessage(analyticsEventsMessage));
			}
		}
	}

	private JSONObject _addIndividual(
		AnalyticsEventsMessage analyticsEventsMessage, Long channelId,
		Long dataSourceId) {

		String userId = analyticsEventsMessage.getUserId();

		JSONObject individualJSONObject =
			_faroInfoIndividualDog.getIndividualJSONObject(
				dataSourceId, userId);

		if (individualJSONObject == null) {
			individualJSONObject = _faroInfoIndividualDog.addIndividual(
				_getAnalyticsDataJSONObject(analyticsEventsMessage), channelId,
				_dataSourceDog.getDataSource(dataSourceId), null, userId);
		}
		else {
			Set<String> channelIds = JSONUtil.toStringSet(
				individualJSONObject.optJSONArray("channelIds"));

			if (channelIds.add(String.valueOf(channelId))) {
				individualJSONObject = _faroInfoElasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					JSONUtil.put("channelIds", channelIds));
			}
		}

		return individualJSONObject;
	}

	@PreDestroy
	private void _destroy() {
		_storage.close();
	}

	private String _generateAnalyticsEventId(
		String dataSourceId, AnalyticsEventsMessage.Event event,
		String projectId, String userId) {

		Date eventDate = event.getEventDate();

		Map<String, String> eventProperties = event.getProperties();

		return DigestUtils.sha256Hex(
			String.join(
				"#", projectId, dataSourceId, userId, event.getApplicationId(),
				event.getEventId(), String.valueOf(eventProperties.hashCode()),
				String.valueOf(eventDate.getTime())));
	}

	private JSONObject _getAnalyticsDataJSONObject(
		AnalyticsEventsMessage analyticsEventsMessage) {

		JSONObject analyticsDataJSONObject = JSONUtil.put(
			"clientIP", analyticsEventsMessage.getClientIP());

		Map<String, Object> context = analyticsEventsMessage.getContext();

		for (String analyticsDataFieldName :
				_ANALYTICS_DATA_ANALYTICS_CONTEXT_FIELD_NAMES) {

			analyticsDataJSONObject.put(
				analyticsDataFieldName, context.get(analyticsDataFieldName));
		}

		return analyticsDataJSONObject;
	}

	private Map<String, String> _getContext(
			AnalyticsEventsMessage analyticsEventsMessage)
		throws IllegalArgumentException {

		Map<String, String> context = new HashMap<>();

		Map<String, Object> analyticsEventsMessageContext =
			analyticsEventsMessage.getContext();

		for (Map.Entry<String, Object> entry :
				analyticsEventsMessageContext.entrySet()) {

			context.put(entry.getKey(), StringUtil.get(entry.getValue()));
		}

		FiftyOneDegreesDevice fiftyOneDegreesEngineDevice =
			_fiftyOneDegreesEngine.getDevice(
				MapUtil.getString(context, "userAgent"));

		if (fiftyOneDegreesEngineDevice != null) {
			Map<String, String> convertedValues = _objectMapper.convertValue(
				fiftyOneDegreesEngineDevice, Map.class);

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
		Long channelId, JSONObject individualJSONObject) {

		return new HashSet<>(
			_segmentDog.getSegmentNames(
				channelId,
				JSONUtil.toLongSet(
					individualJSONObject.getJSONArray(
						"individualSegmentIds"))));
	}

	@PostConstruct
	private void _init() throws Exception {
		StorageConfiguration.Builder builder = StorageConfiguration.builder(
			_analyticsEventsStoragePath);

		builder.fileFormat(StorageConfiguration.FileFormat.SNAPPY_PARQUET);

		Schema.Parser parser = new Schema.Parser();

		builder.fileSchema(
			parser.parse(
				ResourceUtil.readResourceToString(
					"dependencies/analytics_event.avsc", getClass())));

		builder.googleBucket(_analyticsEventsBucket);

		_storage = _storageFactory.getStorage(builder.build());
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

	private boolean _isKnownIndividual(JSONObject individualJSONObject) {
		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		if ((demographicsJSONObject != null) &&
			demographicsJSONObject.has("email")) {

			return true;
		}

		return false;
	}

	private boolean _isValidURL(Map<String, String> context) {
		String url = context.get("url");

		if ((url == null) || url.startsWith("file://") ||
			url.startsWith("http://127.0.0.1") ||
			url.startsWith("http://localhost")) {

			return false;
		}

		return true;
	}

	private void _processMessage(
		AnalyticsEventsMessage analyticsEventsMessage) {

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
			if (_log.isInfoEnabled()) {
				_log.info(
					"Discarding message from invalid host: " +
						analyticsEventsMessage.toJSON());
			}

			return;
		}

		String channelId = analyticsEventsMessage.getChannelId();
		String dataSourceId = analyticsEventsMessage.getDataSourceId();

		if (StringUtil.isNull(channelId) || StringUtils.isBlank(channelId)) {
			channelId = String.valueOf(
				_dataSourceDog.getChannelId(Long.valueOf(dataSourceId)));

			if (StringUtils.isBlank(channelId)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Discarding message because channel ID is invalid: " +
							analyticsEventsMessage.toJSON());
				}

				return;
			}
		}

		JSONObject individualJSONObject = _addIndividual(
			analyticsEventsMessage, Long.valueOf(channelId),
			Long.valueOf(dataSourceId));

		boolean knownIndividual = _isKnownIndividual(individualJSONObject);
		Set<String> segmentNames = _getSegmentNames(
			Long.valueOf(channelId), individualJSONObject);

		Set<AnalyticsEvent> analyticsEvents = new TreeSet<>(
			Comparator.comparing(AnalyticsEvent::getId));

		for (AnalyticsEventsMessage.Event event :
				analyticsEventsMessage.getEvents()) {

			AnalyticsEvent analyticsEvent = new AnalyticsEvent();

			analyticsEvent.setApplicationId(event.getApplicationId());
			analyticsEvent.setChannelId(channelId);
			analyticsEvent.setClientIP(analyticsEventsMessage.getClientIP());
			analyticsEvent.setContext(context);
			analyticsEvent.setCreateDate(
				analyticsEventsMessage.getCreateDate());
			analyticsEvent.setDataSourceId(dataSourceId);
			analyticsEvent.setEventDate(event.getEventDate());
			analyticsEvent.setEventId(event.getEventId());
			analyticsEvent.setEventProperties(
				_getSafeEventProperties(event.getProperties()));
			analyticsEvent.setId(
				_generateAnalyticsEventId(
					dataSourceId, event, analyticsEventsMessage.getProjectId(),
					analyticsEventsMessage.getUserId()));
			analyticsEvent.setIndividualId(
				individualJSONObject.getString("id"));
			analyticsEvent.setKnownIndividual(knownIndividual);
			analyticsEvent.setSegmentNames(segmentNames);
			analyticsEvent.setProjectId(analyticsEventsMessage.getProjectId());
			analyticsEvent.setUserId(analyticsEventsMessage.getUserId());

			analyticsEvents.add(analyticsEvent);
		}

		analyticsEvents.forEach(this::_sendAndStoreAnalyticsEvent);

		_analyticsEventsCounter.inc(analyticsEvents.size());
	}

	private void _sendAndStoreAnalyticsEvent(AnalyticsEvent analyticsEvent) {
		for (Channel channel :
				_analyticsEventsChannels.getChannels(analyticsEvent)) {

			_messageBus.sendMessage(channel, analyticsEvent.toJSON());

			_storage.write(analyticsEvent.toJSON());
		}

		_analyticsEventStorageDog.store(analyticsEvent);
	}

	private static final String[]
		_ANALYTICS_DATA_ANALYTICS_CONTEXT_FIELD_NAMES = {
			"browserName", "crawler", "devicePixelRatio", "deviceType",
			"languageId", "platformName", "screenHeight", "screenWidth",
			"userAgent"
		};

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventsMessageProcessor.class);

	private static final Counter _analyticsEventsCounter =
		PrometheusUtil.counter(
			"extractor_analytics_events_count",
			"The number of extracted analytics events");
	private static final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};

	@Value(
		"${osb.asah.analytics.events.google.bucket:analytics-cloud-analytics-events}"
	)
	private String _analyticsEventsBucket;

	@Autowired
	private AnalyticsEventsChannels _analyticsEventsChannels;

	@Value(
		"${osb.asah.analytics.events.storage.path:/tmp/analytics_events.snappy.parquet}"
	)
	private String _analyticsEventsStoragePath;

	@Autowired
	private AnalyticsEventStorageDog _analyticsEventStorageDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FiftyOneDegreesEngine _fiftyOneDegreesEngine;

	@Autowired
	private IPGeocoder _ipGeocoder;

	@Autowired
	private MessageBus _messageBus;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_MESSAGE)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private SegmentDog _segmentDog;

	private Storage _storage;

	@Autowired
	private StorageFactory _storageFactory;

}