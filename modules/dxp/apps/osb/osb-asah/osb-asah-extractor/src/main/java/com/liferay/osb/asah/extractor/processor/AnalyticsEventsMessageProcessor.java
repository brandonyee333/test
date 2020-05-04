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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageListener;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.AnalyticsEventsMessage;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.extractor.fiftyonedegrees.FiftyOneDegreesDevice;
import com.liferay.osb.asah.extractor.fiftyonedegrees.FiftyOneDegreesEngine;
import com.liferay.osb.asah.extractor.ip.geocoder.IPGeocoder;
import com.liferay.osb.asah.extractor.ip.geocoder.IPInfo;

import io.prometheus.client.Counter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Eddie Olson
 * @author Marcellus Tavares
 */
@Component
public class AnalyticsEventsMessageProcessor implements MessageListener {

	@Override
	public void onMessage(String message) {
		JSONObject jsonObject = new JSONObject(message);

		String dataSourceId = jsonObject.getString("dataSourceId");

		if (Objects.equals(jsonObject.getString("event"), "add")) {
			_activeDataSourceIds.add(dataSourceId);

			String channelId = jsonObject.optString("channelId");

			if (StringUtils.isNotBlank(channelId)) {
				_channelIds.put(dataSourceId, channelId);
			}
		}
		else {
			_activeDataSourceIds.remove(dataSourceId);
			_channelIds.remove(dataSourceId);
		}
	}

	public void processQueuedMessages() throws Exception {
		while (true) {
			List<AnalyticsEventsMessage> analyticsEventsMessages =
				_messageSubscriber.pullMessages(
					50, AnalyticsEventsMessage::toAnalyticsEventsMessage);

			if (analyticsEventsMessages.isEmpty()) {
				break;
			}

			JSONArray analyticsEventJSONArray = _getAnalyticsEventJSONArray(
				analyticsEventsMessages);

			_cerebroRawElasticsearchInvoker.add(
				"analytics-events", analyticsEventJSONArray);

			_analyticsEventsCounter.inc(analyticsEventJSONArray.length());
		}
	}

	private void _addIndividual(
		AnalyticsEventsMessage analyticsEventsMessage, String channelId,
		String dataSourceId) {

		String userId = analyticsEventsMessage.getUserId();

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.individualPKs", userId)
				),
				ScoreMode.None));

		if (individualJSONObject == null) {
			_faroInfoIndividualDog.addIndividual(
				_getAnalyticsDataJSONObject(analyticsEventsMessage), channelId,
				_faroInfoElasticsearchInvoker.get("data-sources", dataSourceId),
				null, userId);
		}
		else {
			Set<String> channelIds = JSONUtil.toStringSet(
				individualJSONObject.optJSONArray("channelIds"));

			if (channelIds.add(channelId)) {
				_faroInfoElasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					JSONUtil.put("channelIds", channelIds));
			}
		}
	}

	private void _cacheActiveDataSourceIds() {
		_activeDataSourceIds = new HashSet<>();

		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"data-sources", QueryBuilders.matchAllQuery());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_activeDataSourceIds.add(jsonObject.getString("id"));
		}
	}

	private void _cacheChannelIds() {
		_channelIds = new HashMap<>();

		// Only data sources created before 2.5.0 contain channel ID

		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"data-sources", QueryBuilders.existsQuery("channelId"));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_channelIds.put(
				jsonObject.getString("id"), jsonObject.getString("channelId"));
		}
	}

	@PreDestroy
	private void _destroy() {
		_messageBus.unregisterMessageListener(this);
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

	private JSONArray _getAnalyticsEventJSONArray(
		List<AnalyticsEventsMessage> analyticsEventsMessages) {

		JSONArray analyticsEventJSONArray = new JSONArray();

		for (AnalyticsEventsMessage analyticsEventsMessage :
				analyticsEventsMessages) {

			if (!_isDataSourceActive(analyticsEventsMessage)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Discarding message because data source is not " +
							"active: " + analyticsEventsMessage.toJSON());
				}

				continue;
			}

			Map<String, Object> context = _getContext(analyticsEventsMessage);

			if (_isCrawler(context)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Discarding message from crawler: " +
							analyticsEventsMessage.toJSON());
				}

				continue;
			}

			if (!_isValidURL(context)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Discarding message from invalid host: " +
							analyticsEventsMessage.toJSON());
				}

				continue;
			}

			String channelId = analyticsEventsMessage.getChannelId();
			String dataSourceId = analyticsEventsMessage.getDataSourceId();

			if (StringUtils.isBlank(channelId) &&
				StringUtils.isBlank(
					channelId = _channelIds.get(dataSourceId))) {

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Discarding message because channel ID is invalid: " +
							analyticsEventsMessage.toJSON());
				}

				continue;
			}

			List<AnalyticsEventsMessage.Event> events =
				analyticsEventsMessage.getEvents();

			for (AnalyticsEventsMessage.Event event : events) {
				AnalyticsEvent analyticsEvent = new AnalyticsEvent();

				analyticsEvent.setApplicationId(event.getApplicationId());
				analyticsEvent.setChannelId(channelId);
				analyticsEvent.setClientIP(
					analyticsEventsMessage.getClientIP());
				analyticsEvent.setContext(context);
				analyticsEvent.setCreateDate(
					analyticsEventsMessage.getCreateDate());
				analyticsEvent.setDataSourceId(dataSourceId);
				analyticsEvent.setEventDate(event.getEventDate());
				analyticsEvent.setEventId(event.getEventId());

				Map<String, String> eventProperties = event.getProperties();

				analyticsEvent.setEventProperties(eventProperties);

				analyticsEvent.setUserId(analyticsEventsMessage.getUserId());

				for (Channel channel :
						_analyticsEventsChannels.getChannels(analyticsEvent)) {

					_messageBus.sendMessage(channel, analyticsEvent.toJSON());
				}

				analyticsEventJSONArray.put(
					new JSONObject(analyticsEvent.toJSON()));
			}

			_addIndividual(analyticsEventsMessage, channelId, dataSourceId);
		}

		return analyticsEventJSONArray;
	}

	private Map<String, Object> _getContext(
			AnalyticsEventsMessage analyticsEventsMessage)
		throws IllegalArgumentException {

		Map<String, Object> context = analyticsEventsMessage.getContext();

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

	@PostConstruct
	private void _init() {
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_cacheActiveDataSourceIds();
		_cacheChannelIds();

		_messageBus.registerMessageListener(Channel.DATA_SOURCES, this);
	}

	private boolean _isCrawler(Map<String, Object> context) {
		if (Boolean.parseBoolean(
				(String)context.getOrDefault("crawler", null))) {

			return true;
		}

		return false;
	}

	private boolean _isDataSourceActive(
		AnalyticsEventsMessage analyticsEventsMessage) {

		if (_activeDataSourceIds.contains(
				analyticsEventsMessage.getDataSourceId())) {

			return true;
		}

		return false;
	}

	private boolean _isValidURL(Map<String, Object> context) {
		String url = (String)context.get("url");

		if ((url == null) || url.startsWith("file://") ||
			url.startsWith("http://127.0.0.1") ||
			url.startsWith("http://localhost")) {

			return false;
		}

		return true;
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

	private Set<String> _activeDataSourceIds;

	@Autowired
	private AnalyticsEventsChannels _analyticsEventsChannels;

	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;
	private Map<String, String> _channelIds;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

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

}