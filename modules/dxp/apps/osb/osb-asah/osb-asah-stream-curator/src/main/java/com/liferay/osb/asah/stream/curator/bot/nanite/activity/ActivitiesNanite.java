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
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.ActivityGroup;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.nlp.NLPUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class ActivitiesNanite implements Nanite {

	public void addActivityJSONObject(
			JSONObject activityJSONObject, String applicationId, String eventId,
			Long ownerId)
		throws Exception {

		_faroInfoActivityDog.addActivity(activityJSONObject);

		if (_faroInfoActivityDog.isActivity(applicationId, eventId)) {
			_messageBus.sendMessage(
				Channel.ACTIVE_INDIVIDUAL_IDS,
				JSONUtil.put(
					"channelId", activityJSONObject.get("channelId")
				).put(
					"ownerId", String.valueOf(ownerId)
				).put(
					"projectId", ProjectIdThreadLocal.getProjectId()
				).toString());
		}
	}

	@Override
	public String getCollectionName() {
		return "activities";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@Override
	public void run() {
		while (true) {
			long start = System.currentTimeMillis();

			List<AnalyticsEvent> analyticsEvents = null;

			try {
				analyticsEvents = _messageSubscriber.pullMessages(
					50, AnalyticsEvent::toAnalyticsEvent);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			if ((analyticsEvents == null) || analyticsEvents.isEmpty()) {
				break;
			}

			for (AnalyticsEvent analyticsEvent : analyticsEvents) {
				process(analyticsEvent);
			}

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info(
					String.format(
						"%s processed %d events in %d ms",
						clazz.getSimpleName(), analyticsEvents.size(),
						System.currentTimeMillis() - start));
			}
		}
	}

	protected void process(AnalyticsEvent analyticsEvent) {
		try {
			ProjectIdThreadLocal.setProjectId(analyticsEvent.getProjectId());

			String applicationId = analyticsEvent.getApplicationId();

			_addActivityJSONObject(
				_addActivityGroup(analyticsEvent), analyticsEvent,
				applicationId, _dataSourceAssetPKFieldNames.get(applicationId));
		}
		catch (Exception e) {
			_log.error(
				"Unable to process the analytics event " +
					analyticsEvent.toJSON(),
				e);
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	protected ElasticsearchInvoker faroInfoElasticsearchInvoker;

	private ActivityGroup _addActivityGroup(AnalyticsEvent analyticsEvent) {
		String channelId = analyticsEvent.getChannelId();
		String dataSourceId = analyticsEvent.getDataSourceId();

		LocalDateTime eventLocalDateTime = DateUtil.toLocalDateTime(
			analyticsEvent.getEventDate(), _timeZoneDog.getZoneId());

		Date dayDate = DateUtil.toUTCDate(
			eventLocalDateTime.with(LocalTime.MIDNIGHT));

		String userId = analyticsEvent.getUserId();

		ActivityGroup activityGroup = _activityGroupDog.fetchActivityGroup(
			"BROWSE", Long.valueOf(channelId), Long.valueOf(dataSourceId),
			dayDate, userId);

		if (activityGroup != null) {
			return _activityGroupDog.updatedActivityGroup(
				activityGroup.getId(), analyticsEvent.getEventDate(),
				DateUtil.toUTCDate(eventLocalDateTime));
		}

		return _activityGroupDog.addActivityGroup(
			"BROWSE", Long.valueOf(channelId), Long.valueOf(dataSourceId),
			dayDate, analyticsEvent.getEventDate(),
			DateUtil.toUTCDate(eventLocalDateTime), _getOwnerId(analyticsEvent),
			analyticsEvent.getEventDate(),
			DateUtil.toUTCDate(eventLocalDateTime), userId);
	}

	private void _addActivityJSONObject(
			ActivityGroup activityGroup, AnalyticsEvent analyticsEvent,
			String applicationId, String dataSourceAssetPKFieldName)
		throws Exception {

		String eventId = analyticsEvent.getEventId();

		JSONObject objectJSONObject = new JSONObject();

		Map<String, String> context = analyticsEvent.getContext();

		objectJSONObject.put(
			"canonicalUrl", MapUtil.getString(context, "canonicalUrl"));

		String dataSourceAssetPK = null;

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (eventProperties != null) {
			dataSourceAssetPK = eventProperties.get(dataSourceAssetPKFieldName);
		}

		if (dataSourceAssetPK == null) {
			dataSourceAssetPK = MapUtil.getString(
				context, dataSourceAssetPKFieldName);

			if (dataSourceAssetPK == null) {
				throw new IllegalArgumentException(
					"No data source asset PK for analytics event " +
						analyticsEvent.toJSON());
			}
		}

		objectJSONObject.put("dataSourceAssetPK", dataSourceAssetPK);

		JSONObject assetJSONObject = _getAssetJSONObject(
			analyticsEvent, dataSourceAssetPK);

		if (assetJSONObject == null) {
			return;
		}

		String assetId = assetJSONObject.getString("id");

		objectJSONObject.put("id", assetId);

		objectJSONObject.put("name", _getName(analyticsEvent));
		objectJSONObject.put(
			"objectType", assetJSONObject.getString("assetType"));
		objectJSONObject.put("url", MapUtil.getString(context, "url"));

		if (applicationId.equals("Comment")) {
			applicationId = objectJSONObject.getString("objectType");

			eventId = "comment" + StringUtils.capitalize(eventId);
		}

		String eventDateString = DateUtil.toUTCString(
			analyticsEvent.getEventDate());

		LocalDateTime eventLocalDateTime = DateUtil.toLocalDateTime(
			analyticsEvent.getEventDate(), _timeZoneDog.getZoneId());

		String dayUTCString = DateUtil.toUTCString(
			eventLocalDateTime.with(LocalTime.MIDNIGHT));

		JSONObject activityJSONObject = JSONUtil.put(
			"activityKey", applicationId + "#" + eventId + "#" + assetId
		).put(
			"activityType", "BROWSE"
		).put(
			"applicationId", applicationId
		).put(
			"channelId", analyticsEvent.getChannelId()
		).put(
			"dataSourceId", analyticsEvent.getDataSourceId()
		).put(
			"day", dayUTCString
		).put(
			"endTime", eventDateString
		).put(
			"endTimeLocal", eventLocalDateTime
		).put(
			"eventContext", new JSONObject(analyticsEvent.getContext())
		).put(
			"eventId", eventId
		).put(
			"eventProperties", _getEventPropertiesJSONObject(analyticsEvent)
		).put(
			"groupId", String.valueOf(activityGroup.getId())
		).put(
			"object", objectJSONObject
		).put(
			"ownerId", String.valueOf(activityGroup.getOwnerId())
		).put(
			"startTime", eventDateString
		).put(
			"startTimeLocal", eventLocalDateTime
		).put(
			"userId", analyticsEvent.getUserId()
		);

		String sessionId = _getSessionId(
			analyticsEvent, activityGroup.getOwnerId());

		if (sessionId != null) {
			activityJSONObject.put("sessionId", sessionId);
		}

		addActivityJSONObject(
			activityJSONObject, applicationId, eventId,
			activityGroup.getOwnerId());
	}

	private JSONObject _getAssetJSONObject(
			AnalyticsEvent analyticsEvent, String dataSourceAssetPK)
		throws Exception {

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.fetch(
			"assets",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dataSourceAssetPK", dataSourceAssetPK)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", analyticsEvent.getDataSourceId())
			));

		if (assetJSONObject != null) {
			JSONArray channelIdsJSONArray = assetJSONObject.getJSONArray(
				"channelIds");

			if (!JSONUtil.hasValue(
					channelIdsJSONArray, analyticsEvent.getChannelId())) {

				channelIdsJSONArray.put(analyticsEvent.getChannelId());

				assetJSONObject = faroInfoElasticsearchInvoker.update(
					"assets", assetJSONObject);
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
				return assetJSONObject;
			}

			String name = _getEventPropertiesTitle(analyticsEvent);

			if (!Objects.equals(assetJSONObject.getString("name"), name)) {
				assetJSONObject.put("name", name);

				return faroInfoElasticsearchInvoker.update(
					"assets", assetJSONObject);
			}

			return assetJSONObject;
		}

		if (Objects.equals(analyticsEvent.getApplicationId(), "Comment")) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get asset for comment analytics event with " +
						"data source PK " + dataSourceAssetPK + " and data " +
							"source ID " + analyticsEvent.getDataSourceId());
			}

			return null;
		}

		Map<String, String> context = analyticsEvent.getContext();

		assetJSONObject = JSONUtil.put(
			"assetType", analyticsEvent.getApplicationId()
		).put(
			"canonicalUrl", MapUtil.getString(context, "canonicalUrl")
		).put(
			"channelIds", JSONUtil.put(analyticsEvent.getChannelId())
		).put(
			"dataSourceAssetPK", dataSourceAssetPK
		).put(
			"dataSourceId", analyticsEvent.getDataSourceId()
		).put(
			"description", MapUtil.getString(context, "description")
		).put(
			"name", _getName(analyticsEvent)
		).put(
			"url", MapUtil.getString(context, "url")
		);

		if (Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
			assetJSONObject.put(
				"keywords",
				JSONUtil.toJSONArray(
					new ArrayList<>(_getKeywordPairs(context)),
					keywordPair -> JSONUtil.put(
						"keyword", keywordPair.getLeft()
					).put(
						"type", keywordPair.getRight()
					)));
		}

		return faroInfoElasticsearchInvoker.add("assets", assetJSONObject);
	}

	private JSONObject _getEventPropertiesJSONObject(
			AnalyticsEvent analyticsEvent)
		throws Exception {

		JSONObject eventPropertiesJSONObject = new JSONObject();

		for (String eventPropertyKey : _EVENT_PROPERTY_KEYS) {
			String value = MapUtil.getString(
				analyticsEvent.getEventProperties(), eventPropertyKey);

			if (value != null) {
				eventPropertiesJSONObject.put(eventPropertyKey, value);
			}
		}

		String pageViewActivityId = _getPageViewActivityId(analyticsEvent);

		if (pageViewActivityId != null) {
			eventPropertiesJSONObject.put(
				"pageViewActivityId", pageViewActivityId);
		}

		if (Objects.equals(analyticsEvent.getEventId(), "formSubmitted")) {
			JSONArray formViewedActivityJSONArray = new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					"activities",
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.rangeQuery(
									"endTime"
								).lt(
									DateUtil.toUTCString(
										analyticsEvent.getEventDate())
								)
							).filter(
								QueryBuilders.termQuery("eventId", "formViewed")
							).filter(
								QueryBuilders.termQuery(
									"userId", analyticsEvent.getUserId())
							));

						searchSourceBuilder.size(1);
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								"endTime", SortOrder.DESC));
					}));

			if (formViewedActivityJSONArray.length() > 0) {
				JSONObject formViewedActivityJSONObject =
					formViewedActivityJSONArray.getJSONObject(0);

				JSONObject formViewedActivityEventPropertiesJSONObject =
					formViewedActivityJSONObject.getJSONObject(
						"eventProperties");

				eventPropertiesJSONObject.put(
					"duration",
					String.valueOf(
						DateUtil.getDeltaMilliseconds(
							formViewedActivityJSONObject.getString("endTime"),
							DateUtil.toUTCString(
								analyticsEvent.getEventDate())))
				).put(
					"title",
					formViewedActivityEventPropertiesJSONObject.getString(
						"title")
				);
			}
		}

		return eventPropertiesJSONObject;
	}

	private String _getEventPropertiesTitle(AnalyticsEvent analyticsEvent)
		throws Exception {

		JSONObject eventPropertiesJSONObject = _getEventPropertiesJSONObject(
			analyticsEvent);

		String title = eventPropertiesJSONObject.optString("title");

		if (!StringUtils.isEmpty(title)) {
			return title;
		}

		return null;
	}

	private Set<Pair<String, String>> _getKeywordPairs(
		Map<String, String> context) {

		Set<Pair<String, String>> keywords = new HashSet<>(
			_getKeywordPairs(
				MapUtil.getString(context, "description"), "description"));

		keywords.addAll(
			_getKeywordPairs(
				MapUtil.getString(context, "keywords"), "keyword"));
		keywords.addAll(
			_getKeywordPairs(MapUtil.getString(context, "title"), "title"));
		keywords.addAll(
			_getKeywordPairs(
				MapUtil.getString(context, "og:description"), "description"));
		keywords.addAll(
			_getKeywordPairs(MapUtil.getString(context, "og:title"), "title"));

		return keywords;
	}

	private Set<Pair<String, String>> _getKeywordPairs(
		String text, String type) {

		if (StringUtils.isEmpty(text)) {
			return Collections.emptySet();
		}

		Stream<String> stream = Stream.of(text.split(","));

		if (type.equals("keyword")) {
			return stream.map(
				String::toLowerCase
			).map(
				keyword -> Pair.of(keyword, type)
			).collect(
				Collectors.toSet()
			);
		}

		return stream.filter(
			NLPUtil::isEnglish
		).map(
			NLPUtil::getKeywords
		).flatMap(
			Collection::stream
		).map(
			String::toLowerCase
		).map(
			keyword -> Pair.of(keyword, type)
		).collect(
			Collectors.toSet()
		);
	}

	private String _getName(AnalyticsEvent analyticsEvent) throws Exception {
		if (!Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
			String name = _getEventPropertiesTitle(analyticsEvent);

			if (name != null) {
				return name;
			}
		}

		return MapUtil.getString(analyticsEvent.getContext(), "title");
	}

	private Long _getOwnerId(AnalyticsEvent analyticsEvent) {
		String ownerId = analyticsEvent.getIndividualId();

		if ((ownerId != null) &&
			_faroInfoElasticsearchInvoker.exists("individuals", ownerId)) {

			return Long.valueOf(ownerId);
		}

		JSONObject individualJSONObject =
			_faroInfoIndividualDog.getIndividualJSONObject(
				Long.valueOf(analyticsEvent.getDataSourceId()),
				analyticsEvent.getUserId());

		if (individualJSONObject != null) {
			return individualJSONObject.getLong("id");
		}

		throw new IllegalStateException(
			"Unable to find individual for owner ID " + ownerId +
				" or user ID " + analyticsEvent.getUserId());
	}

	private String _getPageViewActivityId(AnalyticsEvent analyticsEvent) {
		if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
			Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {

			return String.valueOf(UUID.randomUUID());
		}

		JSONArray mostRecentPageViewActivitiesJSONArray = new JSONArray(
			faroInfoElasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Page")
						).filter(
							QueryBuilders.termQuery("eventId", "pageViewed")
						).filter(
							QueryBuilders.termQuery(
								"userId", analyticsEvent.getUserId())
						));
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("startTime", SortOrder.DESC));
				}));

		if (mostRecentPageViewActivitiesJSONArray.length() == 0) {
			return null;
		}

		JSONObject mostRecentPageViewActivityJSONObject =
			mostRecentPageViewActivitiesJSONArray.getJSONObject(0);

		JSONObject eventPropertiesJSONObject =
			mostRecentPageViewActivityJSONObject.getJSONObject(
				"eventProperties");

		return eventPropertiesJSONObject.optString("pageViewActivityId", null);
	}

	private String _getSessionId(AnalyticsEvent analyticsEvent, Long ownerId) {
		JSONObject userSessionJSONObject =
			_cerebroInfoElasticsearchInvoker.fetch(
				"user-sessions",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"channelId", analyticsEvent.getChannelId())
				).filter(
					QueryBuilders.termQuery(
						"dataSourceId", analyticsEvent.getDataSourceId())
				).filter(
					QueryBuilders.rangeQuery(
						"firstEventDate"
					).lte(
						DateUtil.toUTCString(analyticsEvent.getEventDate())
					)
				).filter(
					QueryBuilders.termQuery(
						"individualId", String.valueOf(ownerId))
				).filter(
					QueryBuilders.rangeQuery(
						"lastEventDate"
					).gte(
						DateUtil.toUTCString(analyticsEvent.getEventDate())
					)
				).filter(
					QueryBuilders.termQuery(
						"userId", analyticsEvent.getUserId())
				),
				null, null, "id");

		if (userSessionJSONObject == null) {
			return null;
		}

		return userSessionJSONObject.getString("id");
	}

	private static final String[] _EVENT_PROPERTY_KEYS = {
		"depth", "title", "viewDuration"
	};

	private static final Log _log = LogFactory.getLog(ActivitiesNanite.class);

	private static final Map<String, String> _dataSourceAssetPKFieldNames =
		new HashMap<String, String>() {
			{
				put("Blog", "entryId");
				put("Comment", "classPK");
				put("Custom", "assetId");
				put("Document", "fileEntryId");
				put("Form", "formId");
				put("Page", "canonicalUrl");
				put("WebContent", "articleId");
			}
		};

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private MessageBus _messageBus;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_ACTIVITY)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}